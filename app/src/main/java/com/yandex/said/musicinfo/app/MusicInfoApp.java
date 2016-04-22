package com.yandex.said.musicinfo.app;

import android.app.Application;

import com.yandex.said.musicinfo.logic.di.components.AppComponent;
import com.yandex.said.musicinfo.logic.di.components.DaggerAppComponent;
import com.yandex.said.musicinfo.logic.di.modules.AppModule;
import com.yandex.said.musicinfo.utils.ConnectivityHelper;

/**
 * Created by said on 26.03.16.
 */
public class MusicInfoApp extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        ConnectivityHelper.setContext(this);
        appComponent = buildComponent();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

}
