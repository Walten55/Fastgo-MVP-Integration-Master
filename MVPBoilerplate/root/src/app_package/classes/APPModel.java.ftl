package ${packageName}.model;

import javax.inject.Inject;

import me.walten.fastgo.base.mvp.IModel;
import ${packageName}.model.local.LocalModel;
import ${packageName}.model.remote.RemoteModel;

public class APPModel implements IModel{

    private RemoteModel remoteModel;
    private LocalModel localModel;
    @Inject
    public APPModel(RemoteModel remoteModel, LocalModel localModel) {
        this.remoteModel = remoteModel;
        this.localModel = localModel;
    }

    public RemoteModel getRemoteModel() {
        return remoteModel;
    }

    public LocalModel getLocalModel() {
        return localModel;
    }

    @Override
    public void destroy() {
        if(remoteModel!=null)
            remoteModel.destroy();
        if(localModel!=null)
            localModel.destroy();
    }
}