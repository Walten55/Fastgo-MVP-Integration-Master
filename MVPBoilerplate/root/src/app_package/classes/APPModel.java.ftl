package ${packageName}.model;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import me.walten.fastgo.base.mvp.BaseModel;
import me.walten.fastgo.base.mvp.BaseView;
import me.walten.fastgo.base.mvp.IModel;
import me.walten.fastgo.integration.IRepositoryManager;
import me.walten.fastgo.utils.RxLifecycleUtil;
import me.walten.fastgo.utils.RxUtil;

public class APPModel extends BaseModel implements IModel{

    @Inject
    public APPModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

}