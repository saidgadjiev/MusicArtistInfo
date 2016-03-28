package com.yandex.said.musicinfo.presenter;

import com.yandex.said.musicinfo.view.IMainActivityView;

import javax.inject.Inject;

/**
 * Created by said on 26.03.16.
 */
public class MainActivityPresenterImpl implements IMainActivityPresenter {

    private IMainActivityView view;

    @Inject
    public MainActivityPresenterImpl(IMainActivityView view) {
        this.view = view;
    }

    @Override
    public void onBackPressed() {

    }
}
