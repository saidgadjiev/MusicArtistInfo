package com.yandex.said.musicinfo.di.components;

import com.yandex.said.musicinfo.app.MusicInfoApp;
import com.yandex.said.musicinfo.di.modules.MusicInfoAppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by said on 26.03.16.
 */

@Singleton
@Component(
        modules = {
                MusicInfoAppModule.class
        }
)
public interface IMusicInfoAppComponent {
    void inject(MusicInfoApp app);
}
