package com.yandex.said.musicinfo.presenter;

import android.util.Log;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.octo.android.robospice.request.simple.SimpleTextRequest;
import com.yandex.said.musicinfo.view.IListFragmentView;

/**
 * Created by said on 26.03.16.
 */
public class ListFragmentPresenterImpl implements IListFragmentPresenter {
    private SpiceManager spiceManager;
    private IListFragmentView view;
    private static final String URL_MUSICINFO_LIST_API =
            "http://cache-default01f.cdn.yandex.net/download.cdn.yandex.net/mobilization-2016/artists.json";
    private static final String CACHE_KEY = "MusicInfo";
    private static final String TAG = "ListFragmentPresenter";

    @Override
    public void init(IListFragmentView view) {
        this.view = view;
    }

    @Override
    public void onResume(SpiceManager spiceManager) {
        view.startService();
        this.spiceManager = spiceManager;
        sendRequest(URL_MUSICINFO_LIST_API, spiceManager);
    }

    @Override
    public void onPause() {
        view.stopService();
    }

    private void sendRequest(String url, SpiceManager spiceManager) {
        SimpleTextRequest textRequest = new SimpleTextRequest("http://brottys.ru/jsontest/");

        view.showProgressDialog();
        spiceManager.execute(textRequest, new MusicInfoApiJsonRequestListener());
    }

    private final class MusicInfoApiJsonRequestListener implements RequestListener<String> {

        @Override
        public void onRequestFailure(SpiceException e) {
            view.hideProgressDialog();
            Log.d(TAG, "Request failure");
        }

        @Override
        public void onRequestSuccess(String s) {
            view.hideProgressDialog();
            Log.d(TAG, "Request success");
        }
    }
}
