package ${packageName}.model.remote;

import javax.inject.Inject;

import me.walten.fastgo.base.mvp.BaseModel;
import me.walten.fastgo.base.mvp.BaseView;
import me.walten.fastgo.base.mvp.IModel;
import me.walten.fastgo.integration.IRepositoryManager;

public class RemoteModel extends BaseModel implements IModel{

    @Inject
    public RemoteModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

}