package ${packageName};

import javax.inject.Inject;

import io.reactivex.functions.Consumer;
import me.walten.fastgo.di.scope.FragmentScope;

@FragmentScope
public class ${presenterClass} extends ${contractClass}.Presenter {

    ${contractClass}.View mView;

    @Inject
    APPModel mModel;

    @Inject
    public ${presenterClass}(){
    }

    @Override
    public void attachView(${contractClass}.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
        mModel.destroy();
    }
}