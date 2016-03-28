package com.yandex.said.musicinfo.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.yandex.said.musicinfo.R;
import com.yandex.said.musicinfo.common.BaseActivity;
import com.yandex.said.musicinfo.di.IHasComponent;
import com.yandex.said.musicinfo.di.components.DaggerIMainActivityComponent;
import com.yandex.said.musicinfo.di.components.IMainActivityComponent;
import com.yandex.said.musicinfo.di.components.IMusicInfoAppComponent;
import com.yandex.said.musicinfo.di.modules.MainActivityModule;
import com.yandex.said.musicinfo.presenter.MainActivityPresenterImpl;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements IMainActivityView, IHasComponent<IMainActivityComponent> {

    @Inject
    MainActivityPresenterImpl presenter;

    private IMainActivityComponent mainActivityComponent;
    private FragmentManager fragmentManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle("Исполнители");
        setSupportActionBar(toolbar);
        fragmentManager = getFragmentManager();
        ListFragment listFragment = (ListFragment) fragmentManager.findFragmentByTag("ListFragment");

        if (listFragment == null) {
            listFragment = new ListFragment();
        }
        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, listFragment)
                    .commit();
        }
    }

    @Override
    protected void setupComponent(IMusicInfoAppComponent component) {
        mainActivityComponent = DaggerIMainActivityComponent.builder()
                .iMusicInfoAppComponent(component)
                .mainActivityModule(new MainActivityModule(this))
                .build();
        mainActivityComponent.inject(this);
    }

    @Override
    public IMainActivityComponent getComponent() {
        return mainActivityComponent;
    }

    @Override
    public void popFragmentFromStack() {

    }
}
