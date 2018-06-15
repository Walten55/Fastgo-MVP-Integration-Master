package ${packageName}.di.component;

import android.app.Activity;

import dagger.Component;
import ${packageName}.di.module.ActivityModule;
import me.walten.fastgo.di.component.AppComponent;
import me.walten.fastgo.di.scope.ActivityScope;


@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

}