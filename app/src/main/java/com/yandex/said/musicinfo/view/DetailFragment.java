package com.yandex.said.musicinfo.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yandex.said.musicinfo.R;
import com.yandex.said.musicinfo.common.BaseFragment;
import com.yandex.said.musicinfo.di.components.IMainActivityComponent;
import com.yandex.said.musicinfo.model.ItemArtist;

/**
 * Created by said on 30.03.16.
 */
public class DetailFragment extends BaseFragment implements IDetailFragmentView {

    public static final String BUNDLE_ID = "bundleID";

    private Activity activity;
    private ItemArtist itemArtist;

    public static DetailFragment newInstance(ItemArtist itemArtist) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();

        bundle.putSerializable(BUNDLE_ID, itemArtist);
        detailFragment.setArguments(bundle);

        return detailFragment;
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

        if (getArguments() != null && getArguments().containsKey(BUNDLE_ID)) {
            this.itemArtist = (ItemArtist) getArguments().getSerializable(BUNDLE_ID);
        } else {
            throw new IllegalArgumentException("Must be created through newInstance(int id)");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ImageView avatar = (ImageView) view.findViewById(R.id.detail_artist_avatar);
        TextView genres = (TextView) view.findViewById(R.id.detail_artist_genres);
        TextView albums = (TextView) view.findViewById(R.id.detail_artist_albums);
        TextView tracks = (TextView) view.findViewById(R.id.detail_artist_tracks);
        TextView biography = (TextView) view.findViewById(R.id.detail_artist_biography);

        Picasso.with(activity)
                .load(itemArtist.getBigAvatarUrl())
                .resize(300, 300)
                .into(avatar);
        StringBuffer allGenres = new StringBuffer();

        for (String genre : itemArtist.getGenres()) {
            allGenres.append(genre);
            if (!itemArtist.getGenres().get(itemArtist.getGenres().size() - 1).equals(genre)) {
                allGenres.append(", ");
            }
        }
        genres.setText(allGenres.toString());
        albums.setText(String.valueOf(itemArtist.getCountAlbums()));
        tracks.setText(String.valueOf(itemArtist.getCountTracks()));
        biography.setText(itemArtist.getDescription());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.getComponent(IMainActivityComponent.class).inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void startService() {

    }

    @Override
    public void stopService() {

    }
}
