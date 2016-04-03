package com.yandex.said.musicinfo.common;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.yandex.said.musicinfo.R;
import com.yandex.said.musicinfo.model.ItemArtist;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.callback.Callback;

/**
 * Created by said on 28.03.16.
 */
public class MusicInfoListAdapter extends RecyclerView.Adapter<MusicInfoListAdapter.ViewHolder> {

    private List<ItemArtist> itemArtistList;
    private LayoutInflater layoutInflater;
    private Activity activity;
    private ClickListener clickListener;
    protected int totalListSize;
    Picasso picasso;

    public static final int VIEW_TYPE_LOADING = 0;
    public static final int VIEW_TYPE_ACTIVITY = 1;

    public MusicInfoListAdapter(Activity activity, ClickListener clickListener, List<ItemArtist> itemArtistList, int totalListSize) {
        this.activity = activity;
        this.clickListener = clickListener;
        this.layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.itemArtistList = itemArtistList;
        this.totalListSize = totalListSize;
        picasso = Picasso.with(activity);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_musicinfo_list, null);

        return new ViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        StringBuffer allGenres = new StringBuffer();

        for (String genre : itemArtistList.get(position).getGenres()) {
            allGenres.append(genre);
            allGenres.append(", ");
        }
        holder.genres.setText(allGenres.substring(0, allGenres.length() - 2));
        picasso.load(itemArtistList.get(position).getSmallAvatarUrl())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.avatar);
        holder.name.setText(itemArtistList.get(position).getName());
        holder.albums.setText(String.valueOf(itemArtistList.get(position).getCountAlbums()));
        holder.tracks.setText(String.valueOf(itemArtistList.get(position).getCountTracks()));
    }

    @Override
    public int getItemViewType(int position) {
        return (position >= itemArtistList.size()) ? VIEW_TYPE_LOADING : VIEW_TYPE_ACTIVITY;
    }

    @Override
    public long getItemId(int position) {
        return (getItemViewType(position) == VIEW_TYPE_ACTIVITY) ? position : -1;
    }

    public ItemArtist getItem(int positoin) {
        return itemArtistList.get(positoin);
    }

    @Override
    public int getItemCount() {
        return itemArtistList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ClickListener clickListener;
        ImageView avatar;
        TextView name;
        TextView genres;
        TextView albums;
        TextView tracks;

        public ViewHolder(View itemView, ClickListener clickListener) {
            super(itemView);

            this.clickListener = clickListener;
            itemView.setOnClickListener(this);
            avatar = (ImageView) itemView.findViewById(R.id.avatar);
            name = (TextView) itemView.findViewById(R.id.name);
            genres = (TextView) itemView.findViewById(R.id.genres);
            albums = (TextView) itemView.findViewById(R.id.albums);
            tracks = (TextView) itemView.findViewById(R.id.tracks);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.onItemClicked(getAdapterPosition());
            }
        }
    }

    public interface ClickListener {
        void onItemClicked(int position);
    }
}
