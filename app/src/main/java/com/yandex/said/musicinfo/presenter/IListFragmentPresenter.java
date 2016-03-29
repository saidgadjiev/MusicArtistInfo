package com.yandex.said.musicinfo.presenter;

import com.octo.android.robospice.SpiceManager;
import com.yandex.said.musicinfo.common.BaseFragmentPresenter;
import com.yandex.said.musicinfo.view.IListFragmentView;

/**
 * Created by said on 29.03.16.
 */
public interface IListFragmentPresenter extends BaseFragmentPresenter<IListFragmentView> {
    void onResume(SpiceManager spiceManager);
    void onPause();
}
