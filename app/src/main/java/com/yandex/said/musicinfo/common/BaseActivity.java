package com.yandex.said.musicinfo.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yandex.said.musicinfo.app.MusicInfoApp;
import com.yandex.said.musicinfo.di.components.IMusicInfoAppComponent;

/**
 * Created by said on 26.03.16.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupComponent(MusicInfoApp.get(this).getAppComponent());
    }

    protected abstract void setupComponent(IMusicInfoAppComponent component);

}
