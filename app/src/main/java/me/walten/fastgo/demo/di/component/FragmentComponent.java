package me.walten.fastgo.demo.di.component;

import android.app.Activity;

import dagger.Component;
import me.walten.fastgo.demo.business.main.BannerFragment;
import me.walten.fastgo.demo.di.module.FragmentModule;
import me.walten.fastgo.di.scope.FragmentScope;
import me.walten.fastgo.di.component.AppComponent;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(BannerFragment bannerFragment);

}