package com.yandex.said.musicinfo.app;

import android.app.Application;
import android.content.Context;

import com.yandex.said.musicinfo.di.components.DaggerIMusicInfoAppComponent;
import com.yandex.said.musicinfo.di.components.IMusicInfoAppComponent;
import com.yandex.said.musicinfo.di.modules.MusicInfoAppModule;

/**
 * Created by said on 26.03.16.
 */
public class MusicInfoApp extends Application {

    protected IMusicInfoAppComponent appComponent;

    public static MusicInfoApp get(Context context) {
        return (MusicInfoApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        buildGraphAndInject();
    }

    public IMusicInfoAppComponent getAppComponent() {
        return appComponent;
    }

    public void buildGraphAndInject() {
        appComponent = DaggerIMusicInfoAppComponent.builder()
                .musicInfoAppModule(new MusicInfoAppModule(this))
                .build();
        appComponent.inject(this);
    }

}
