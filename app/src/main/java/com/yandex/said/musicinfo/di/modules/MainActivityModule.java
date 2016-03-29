package com.yandex.said.musicinfo.di.modules;

import com.yandex.said.musicinfo.presenter.IMainActivityPresenter;
import com.yandex.said.musicinfo.presenter.ListFragmentPresenterImpl;
import com.yandex.said.musicinfo.presenter.MainActivityPresenterImpl;
import com.yandex.said.musicinfo.view.IListFragmentView;
import com.yandex.said.musicinfo.view.IMainActivityView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by said on 26.03.16.
 */

@Module
public class MainActivityModule {

    private IMainActivityView view;

    public MainActivityModule(IMainActivityView view) {
        this.view = view;
    }

    @Provides
    public IMainActivityView provideView() {
        return view;
    }

    @Provides
    public MainActivityPresenterImpl provideMainActivityPresenterImpl(IMainActivityView view) {
        return new MainActivityPresenterImpl(view);
    }

    @Provides
    public ListFragmentPresenterImpl provideListFragmentPresenterImpl() {
        return new ListFragmentPresenterImpl();
    }
}
