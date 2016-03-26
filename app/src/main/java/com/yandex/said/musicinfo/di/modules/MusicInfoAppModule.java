package com.yandex.said.musicinfo.di.modules;

import android.app.Application;

import com.yandex.said.musicinfo.app.MusicInfoApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by said on 26.03.16.
 */

@Module
public class MusicInfoAppModule {

    private final MusicInfoApp musicApp;

    public MusicInfoAppModule(MusicInfoApp app) {
        this.musicApp = app;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return musicApp;
    }
}
