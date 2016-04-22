package com.yandex.said.musicinfo.logic.network;

import android.support.v4.util.LruCache;

import com.jakewharton.disklrucache.DiskLruCache;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.yandex.said.musicinfo.model.ItemArtist;
import com.yandex.said.musicinfo.utils.ConnectivityHelper;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.http.GET;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by said on 20.04.16.
 */

public class NetworkService {
    private static final String BASE_URL =
            "http://cache-default01f.cdn.yandex.net/download.cdn.yandex.net/mobilization-2016/artists.json";
    private ArtistApi api;
    private static long SIZE_OF_CACHE = 3 * 1024 * 1024;
    private LruCache<Class<?>, Observable<?>> apiObservables;

    public NetworkService() {
        Cache cache = new Cache(android.os.Environment.getExternalStorageDirectory(), SIZE_OF_CACHE);
        OkHttpClient okHttpClient = new OkHttpClient();
        apiObservables = new LruCache<>(10);
        okHttpClient.setCache(cache);
        okHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);
        okHttpClient.setReadTimeout(30, TimeUnit.SECONDS);
        okHttpClient.networkInterceptors().add(cacheControlInterceptor);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

        api = builder.build().create(ArtistApi.class);
    }

    public ArtistApi getArtistApi() {
        return api;
    }

    public interface ArtistApi {
        @GET("artists.json")
        Observable<List<ItemArtist>> getArtistList();
    }

    public Observable<?> getPreparedObservable(Observable<?> unPreparedObservable, Class<?> clazz, boolean cacheObservable, boolean useCache) {
        Observable<?> preparedObservable = null;

        if (useCache) {
            preparedObservable = apiObservables.get(clazz);
        }
        if (preparedObservable != null)
            return preparedObservable;
        preparedObservable = unPreparedObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        if (cacheObservable) {
            preparedObservable = preparedObservable.cache();
            apiObservables.put(clazz, preparedObservable);
        }

        return preparedObservable;
    }

    private static final Interceptor cacheControlInterceptor = new Interceptor() {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            if (request.method().equals("GET")) {
                if (ConnectivityHelper.isConnected()) {
                    request.newBuilder()
                            .header("Cache-Control", "only-if-cached")
                            .build();
                } else {
                    request.newBuilder()
                            .header("Cache-Control", "public, max-stale=2419200")
                            .build();
                }
            }
            Response response = chain.proceed(request);

            return response.newBuilder()
                    .header("Cache-Control", "public, max-age=86400")
                    .build();
        }
    };
}

