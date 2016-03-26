package com.yandex.said.musicinfo.presenter;

import com.yandex.said.musicinfo.view.IMainActivityView;

/**
 * Created by said on 26.03.16.
 */
public class MainActivityPresenterImpl implements IMainActivityPresenter {

    IMainActivityView view;

    public MainActivityPresenterImpl(IMainActivityView view) {
        this.view = view;
    }

    @Override
    public void onBackPressed() {

    }
}
