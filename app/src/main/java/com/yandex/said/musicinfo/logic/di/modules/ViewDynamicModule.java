package com.yandex.said.musicinfo.logic.di.modules;

import com.yandex.said.musicinfo.presenter.MainActivityPresenterImpl;
import com.yandex.said.musicinfo.view.IMainActivityView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by said on 22.04.16.
 */

@Module
public class ViewDynamicModule {

    private IMainActivityView view;

    public ViewDynamicModule(IMainActivityView view) {
        this.view = view;
    }

    @Provides
    public MainActivityPresenterImpl provideMainActivityPresenterImpl() {
        return new MainActivityPresenterImpl(view);
    }
}
