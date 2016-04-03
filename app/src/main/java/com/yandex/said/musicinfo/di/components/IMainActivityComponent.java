package com.yandex.said.musicinfo.di.components;

import com.yandex.said.musicinfo.di.ActivityScope;
import com.yandex.said.musicinfo.di.modules.MainActivityModule;
import com.yandex.said.musicinfo.presenter.ListFragmentPresenterImpl;
import com.yandex.said.musicinfo.view.DetailFragment;
import com.yandex.said.musicinfo.view.ListFragment;
import com.yandex.said.musicinfo.view.MainActivity;

import dagger.Component;

/**
 * Created by said on 26.03.16.
 */

@ActivityScope
@Component(
        dependencies = IMusicInfoAppComponent.class,
        modules = MainActivityModule.class
)
public interface IMainActivityComponent {
    void inject(MainActivity activity);
    void inject(ListFragment fragment);
    void inject(DetailFragment fragment);
}
