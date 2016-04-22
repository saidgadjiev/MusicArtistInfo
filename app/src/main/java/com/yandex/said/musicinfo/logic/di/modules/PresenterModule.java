package com.yandex.said.musicinfo.logic.di.modules;

import com.yandex.said.musicinfo.logic.network.NetworkService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by said on 22.04.16.
 */
@Module
public class PresenterModule {

    @Provides
    @Singleton
    public NetworkService provideNetworkService() {
        return new NetworkService();
    }
}
