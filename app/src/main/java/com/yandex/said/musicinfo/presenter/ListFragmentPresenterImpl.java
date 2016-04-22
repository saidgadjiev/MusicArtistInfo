package com.yandex.said.musicinfo.presenter;

import com.yandex.said.musicinfo.app.MusicInfoApp;
import com.yandex.said.musicinfo.model.ItemArtist;
import com.yandex.said.musicinfo.logic.network.NetworkService;
import com.yandex.said.musicinfo.utils.MessageHelper;
import com.yandex.said.musicinfo.view.IListFragmentView;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by said on 26.03.16.
 */
public class ListFragmentPresenterImpl implements IListFragmentPresenter {

    private IListFragmentView view;
    private Subscription subscription;
    @Inject
    NetworkService service;
    private boolean isLoad = false;

    public ListFragmentPresenterImpl() {
        MusicInfoApp.getAppComponent().inject(this);
    }

    @Override
    public void onResume() {
        loadArtistList();
    }

    @Override
    public void onPause() {
        unSubscribe();
    }

    @Override
    public void onCreate(IListFragmentView view) {
        this.view = view;
    }

    @Override
    public void onItemClick(int position) {
        view.replaceToDetailFragment(position);
    }

    private void loadArtistList() {
        if (!isLoad) {
            view.showProgressBar();

            Observable<List<ItemArtist>> observer = (Observable<List<ItemArtist>>)
                    service.getPreparedObservable(service.getArtistApi().getArtistList(), ItemArtist.class, true, true);
            subscription = observer.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<List<ItemArtist>>() {

                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            view.showError(MessageHelper.NO_INTERNET_ERROR);
                            view.hideProgressBar();
                        }

                        @Override
                        public void onNext(List<ItemArtist> list) {
                            view.hideProgressBar();
                            view.setMusicInfoListAdapter(list, list.size());
                        }
                    });
            isLoad = true;
        }
    }

    private void unSubscribe() {
        if (subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
    }
}
