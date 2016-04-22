package com.yandex.said.musicinfo.logic.di.modules;

import com.yandex.said.musicinfo.presenter.ListFragmentPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by said on 26.03.16.
 */

@Module
public class ViewModule {

    @Provides
    public ListFragmentPresenterImpl provideListFragmentPresenterImpl() {
        return new ListFragmentPresenterImpl();
    }
}
