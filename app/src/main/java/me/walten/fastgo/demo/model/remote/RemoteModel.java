package me.walten.fastgo.demo.model.remote;

import android.util.ArrayMap;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import me.walten.fastgo.base.mvp.BaseModel;
import me.walten.fastgo.base.mvp.IModel;
import me.walten.fastgo.demo.model.entity.Result;
import me.walten.fastgo.integration.IRepositoryManager;
import me.walten.fastgo.utils.XRxUtil;

public class RemoteModel extends BaseModel implements IModel {

    @Inject
    public RemoteModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    public void getWeather(String city,Consumer<Object> consumer){
        Map<String,Object> params = new ArrayMap<>();
        params.put("city",city);

        addSubscribe(mRepositoryManager.obtainRetrofitService(APIService.class)
                .weatherApi(params)
                .compose(XRxUtil.<Result<Object>>getHttpDefaultScheduler())
                .compose(XRxUtil.getHandleResultDefault(new Function<Result<Object>, Flowable<Object>>() {
                    @Override
                    public Flowable<Object> apply(Result<Object> objectResponse) throws Exception {
                        return XRxUtil.formatResult(objectResponse.getData());
                    }
                }))
                .subscribe(consumer));
    }
}