package com.yandex.said.musicinfo.logic.di.components;

import com.yandex.said.musicinfo.logic.di.modules.ViewDynamicModule;
import com.yandex.said.musicinfo.view.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by said on 22.04.16.
 */

@Singleton
@Component(
        modules = ViewDynamicModule.class
)
public interface ViewDynamicComponent {
    void inject(MainActivity mainActivity);
}
