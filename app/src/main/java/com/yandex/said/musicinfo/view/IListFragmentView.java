package com.yandex.said.musicinfo.view;

import com.yandex.said.musicinfo.model.ItemArtist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by said on 26.03.16.
 */
public interface IListFragmentView {
    void showProgressDialog();
    void hideProgressDialog();
    void startService();
    void stopService();
    void setMusicInfoListAdapter(List<ItemArtist> itemArtists, int totalArtists);
    void replaceToDetailFragment(int position);
}
