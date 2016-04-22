package com.yandex.said.musicinfo.logic.di.components;

import com.yandex.said.musicinfo.logic.di.modules.AppModule;
import com.yandex.said.musicinfo.logic.di.modules.PresenterModule;
import com.yandex.said.musicinfo.logic.di.modules.ViewModule;
import com.yandex.said.musicinfo.presenter.ListFragmentPresenterImpl;
import com.yandex.said.musicinfo.presenter.MainActivityPresenterImpl;
import com.yandex.said.musicinfo.view.DetailFragment;
import com.yandex.said.musicinfo.view.ListFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by said on 26.03.16.
 */

@Singleton
@Component(
        modules = {
                ViewModule.class,
                PresenterModule.class,
                AppModule.class
        }
)
public interface AppComponent {
    void inject(MainActivityPresenterImpl presenter);
    void inject(ListFragment fragment);
    void inject(ListFragmentPresenterImpl presenter);
    void inject(DetailFragment fragment);
}
