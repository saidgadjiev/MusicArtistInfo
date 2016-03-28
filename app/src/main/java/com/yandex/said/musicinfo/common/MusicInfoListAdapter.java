package com.yandex.said.musicinfo.common;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.yandex.said.musicinfo.R;
import com.yandex.said.musicinfo.model.ItemArtist;

import java.util.List;

/**
 * Created by said on 28.03.16.
 */
public class MusicInfoListAdapter extends BaseAdapter {

    List<ItemArtist> itemArtistList;
    private LayoutInflater layoutInflater;
    private Activity activity;
    protected int totalListSize;

    public static final int VIEW_TYPE_LOADING = 0;
    public static final int VIEW_TYPE_ACTIVITY = 1;

    public MusicInfoListAdapter(Activity activity, List<ItemArtist> itemArtistList, int totalListSize) {
        this.activity = activity;
        this.layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.itemArtistList = itemArtistList;
        this.totalListSize = totalListSize;
    }

    @Override
    public boolean isEnabled(int position) {
        return getItemViewType(position) == VIEW_TYPE_ACTIVITY;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return (position >= itemArtistList.size()) ? VIEW_TYPE_LOADING : VIEW_TYPE_ACTIVITY;
    }

    @Override
    public int getCount() {
        return itemArtistList.size() + 1;
    }

    @Override
    public ItemArtist getItem(int position) {
        return (getItemViewType(position) == VIEW_TYPE_ACTIVITY) ? itemArtistList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return (getItemViewType(position) == VIEW_TYPE_ACTIVITY) ? position : -1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_musicinfo_list, parent, false);
        }

        return view;
    }

    public void add(List<ItemArtist> itemArtists) {
        this.itemArtistList.addAll(itemArtists);
    }
}
