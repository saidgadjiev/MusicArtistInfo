package com.yandex.said.musicinfo.network;

import android.app.Application;

import com.octo.android.robospice.SpiceService;
import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.exception.CacheCreationException;
import com.octo.android.robospice.persistence.string.InFileStringObjectPersister;

/**
 * Created by said on 29.03.16.
 */
public class MusicInfoService extends SpiceService {
    @Override
    public CacheManager createCacheManager(Application application) throws CacheCreationException {
        CacheManager cacheManager = new CacheManager();
        InFileStringObjectPersister tedPersister = new InFileStringObjectPersister(application);
        cacheManager.addPersister(tedPersister);

        return cacheManager;
    }

    @Override
    public int getThreadCount() {
        return 3;
    }
}
