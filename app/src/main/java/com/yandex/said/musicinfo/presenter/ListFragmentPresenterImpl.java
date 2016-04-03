package com.yandex.said.musicinfo.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.octo.android.robospice.request.simple.SimpleTextRequest;
import com.yandex.said.musicinfo.model.ItemArtist;
import com.yandex.said.musicinfo.view.IListFragmentView;

import java.util.ArrayList;
import java.util.List;

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
        sendRequest(spiceManager);
    }

    @Override
    public void onPause() {
        view.stopService();
    }

    @Override
    public void onItemClick(int position) {
        view.replaceToDetailFragment(position);
    }

    private void sendRequest(SpiceManager spiceManager) {
        SimpleTextRequest textRequest = new SimpleTextRequest(URL_MUSICINFO_LIST_API);

        view.showProgressDialog();
        spiceManager.getFromCacheAndLoadFromNetworkIfExpired(textRequest,
                CACHE_KEY,
                DurationInMillis.ONE_WEEK,
                new MusicInfoApiJsonRequestListener());
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
            List<ItemArtist> itemArtists = getArrayListFromJson(s);
            view.setMusicInfoListAdapter(itemArtists.subList(0, 15), 15);
        }

        private List<ItemArtist> getArrayListFromJson(String jsonString) {
            List<ItemArtist> itemArtistsList = new ArrayList<>();
            JsonArray jsonArrayArtists = new Gson().fromJson(jsonString, JsonArray.class);
            JsonObject jsonObject;

            for (JsonElement jsonElement: jsonArrayArtists) {
                jsonObject = jsonElement.getAsJsonObject();
                int id = jsonObject.get("id").getAsInt();
                String name = jsonObject.get("name").getAsString();
                JsonArray jsonArrayGenres = jsonObject.getAsJsonArray("genres");
                List<String> genres = new ArrayList<>();

                for (JsonElement jsonGenre: jsonArrayGenres) {
                    genres.add(jsonGenre.getAsString());
                }
                int tracks = jsonObject.get("tracks").getAsInt();
                int albums = jsonObject.get("albums").getAsInt();
                String link = "No link";
                if (jsonObject.has("link")) { //artist with id 1150 name=Keri Hilson doesn't have a link
                    link = jsonObject.get("link").getAsString();
                }
                String description = jsonObject.get("description").getAsString();
                String smallAvatarUrl = jsonObject.getAsJsonObject("cover").get("small").getAsString();
                String bigAvatrUrl = jsonObject.getAsJsonObject("cover").get("big").getAsString();
                ItemArtist itemArtist = new ItemArtist();

                itemArtist.setId(id);
                itemArtist.setName(name);
                itemArtist.setGenres(genres);
                itemArtist.setCountTracks(tracks);
                itemArtist.setCountAlbums(albums);
                itemArtist.setLink(link);
                itemArtist.setDescription(description);
                itemArtist.setSmallAvatarUrl(smallAvatarUrl);
                itemArtist.setBigAvatarUrl(bigAvatrUrl);
                itemArtistsList.add(itemArtist);
            }

            return itemArtistsList;
        }
    }
}
