package me.walten.fastgo.demo.di.component;

import android.app.Activity;

import dagger.Component;
import me.walten.fastgo.demo.business.main.MainActivity;
import me.walten.fastgo.demo.di.module.ActivityModule;
import me.walten.fastgo.di.component.AppComponent;
import me.walten.fastgo.di.scope.ActivityScope;


@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(MainActivity mainActivity);
}