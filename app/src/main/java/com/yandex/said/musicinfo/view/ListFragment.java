package com.yandex.said.musicinfo.view;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.yandex.said.musicinfo.R;
import com.yandex.said.musicinfo.common.BaseFragment;
import com.yandex.said.musicinfo.common.MusicInfoListAdapter;
import com.yandex.said.musicinfo.model.ItemArtist;
import com.yandex.said.musicinfo.presenter.ListFragmentPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by said on 26.03.16.
 */
public class ListFragment extends BaseFragment implements IListFragmentView {

    @Inject
    ListFragmentPresenter presenter;

    MusicInfoListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void setMusicInfoListAdapter(List<ItemArtist> itemArtists, int totalArtists) {
        if (adapter == null) {
            adapter = new MusicInfoListAdapter(getActivity(), itemArtists, totalArtists);
        }
    }
}
