package com.yandex.said.musicinfo.common;

import android.app.Fragment;

import com.yandex.said.musicinfo.di.IHasComponent;

/**
 * Created by said on 26.03.16.
 */

public abstract class BaseFragment extends Fragment {

    protected <T> T getComponent(Class<T> componentType) {
        return componentType.cast(((IHasComponent<T>)getActivity()).getComponent());
    }
}
