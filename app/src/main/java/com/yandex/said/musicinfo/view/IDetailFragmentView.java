package com.yandex.said.musicinfo.view;

import com.yandex.said.musicinfo.model.ItemArtist;

/**
 * Created by said on 30.03.16.
 */
public interface IDetailFragmentView {
    void showProgressDialog();
    void hideProgressDialog();
    void startService();
    void stopService();
}
