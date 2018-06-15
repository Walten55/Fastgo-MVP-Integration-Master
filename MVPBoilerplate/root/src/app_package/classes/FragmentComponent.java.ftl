package ${packageName}.di.component;

import android.app.Activity;

import dagger.Component;
import ${packageName}.di.module.FragmentModule;
import me.walten.fastgo.di.scope.FragmentScope;
import me.walten.fastgo.di.component.AppComponent;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

}