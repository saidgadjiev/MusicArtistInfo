package com.yandex.said.musicinfo.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.octo.android.robospice.SpiceManager;
import com.yandex.said.musicinfo.R;
import com.yandex.said.musicinfo.common.BaseFragment;
import com.yandex.said.musicinfo.common.MusicInfoListAdapter;
import com.yandex.said.musicinfo.di.components.IMainActivityComponent;
import com.yandex.said.musicinfo.model.ItemArtist;
import com.yandex.said.musicinfo.network.MusicInfoService;
import com.yandex.said.musicinfo.presenter.ListFragmentPresenterImpl;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by said on 26.03.16.
 */
public class ListFragment extends BaseFragment implements IListFragmentView {

    @Inject
    ListFragmentPresenterImpl presenter;

    protected SpiceManager spiceManager = new SpiceManager(MusicInfoService.class);

    Activity activity;
    RecyclerView recyclerView;
    MusicInfoListAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    View rootView;

    public ListFragment() {

    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {
            activity = (Activity) context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_list, container, false);
            recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

            recyclerView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(activity);
            recyclerView.setLayoutManager(layoutManager);
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.getComponent(IMainActivityComponent.class).inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.init(this);
        presenter.onResume(spiceManager);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void showProgressDialog() {
        ProgressBar progressBar = (ProgressBar) activity.findViewById(R.id.toolbar_progressbar);

        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressDialog() {
        ProgressBar progressBar = (ProgressBar) activity.findViewById(R.id.toolbar_progressbar);

        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void startService() {
        if (!spiceManager.isStarted()) {
            spiceManager.start(activity);
        }
    }

    @Override
    public void stopService() {
        if (spiceManager.isStarted()) {
            spiceManager.shouldStop();
        }
    }

    @Override
    public void setMusicInfoListAdapter(List<ItemArtist> itemArtists, int totalArtists) {
        if (adapter == null) {
            adapter = new MusicInfoListAdapter(getActivity(), itemArtists, totalArtists);
            recyclerView.setAdapter(adapter);
        }
    }
}
