package com.yandex.said.musicinfo.presenter;

import com.yandex.said.musicinfo.view.IListFragmentView;

/**
 * Created by said on 29.03.16.
 */
public interface IListFragmentPresenter {
    void onCreate(IListFragmentView view);
    void onResume();
    void onPause();
    void onItemClick(int position);
}
