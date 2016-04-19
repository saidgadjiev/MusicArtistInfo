package com.yandex.said.musicinfo.di.modules;

import com.yandex.said.musicinfo.presenter.DetailFragmentPresenterImpl;
import com.yandex.said.musicinfo.presenter.ListFragmentPresenterImpl;
import com.yandex.said.musicinfo.presenter.MainActivityPresenterImpl;
import com.yandex.said.musicinfo.view.IMainActivityView;
import com.yandex.said.musicinfo.view.MainActivity;

import dagger.Provides;

import static org.mockito.Mockito.*;

/**
 * Created by said on 17.04.16.
 */
public class TestMainActivityModule {

    @Provides
    public IMainActivityView provideView() {
        return mock(MainActivity.class);
    }

    @Provides
    public MainActivityPresenterImpl provideMainActivityPresenterImpl(IMainActivityView view) {
        return mock(MainActivityPresenterImpl.class);
    }

    @Provides
    public ListFragmentPresenterImpl provideListFragmentPresenterImpl() {
        return mock(ListFragmentPresenterImpl.class);
    }

    @Provides
    public DetailFragmentPresenterImpl provideDetailFragmentPresenterImpl() {
        return mock(DetailFragmentPresenterImpl.class);
    }

}
