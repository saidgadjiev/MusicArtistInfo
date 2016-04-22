package com.yandex.said.musicinfo.view;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.yandex.said.musicinfo.R;
import com.yandex.said.musicinfo.logic.di.components.DaggerViewDynamicComponent;
import com.yandex.said.musicinfo.logic.di.components.ViewDynamicComponent;
import com.yandex.said.musicinfo.logic.di.modules.ViewDynamicModule;
import com.yandex.said.musicinfo.presenter.MainActivityPresenterImpl;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements IMainActivityView {

    @Inject
    MainActivityPresenterImpl presenter;

    private ViewDynamicComponent viewComponent;
    private FragmentManager fragmentManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

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
        setupComponent();
    }

    @Override
    public boolean onSupportNavigateUp() {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            presenter.onBackPressed();
        } else {
            super.onBackPressed();
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            presenter.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    protected void setupComponent() {
        if (viewComponent == null) {
            viewComponent = DaggerViewDynamicComponent.builder()
                    .viewDynamicModule(new ViewDynamicModule(this))
                    .build();
        }
        viewComponent.inject(this);
    }

    @Override
    public void popFragmentFromStack() {
        fragmentManager.popBackStack();
    }
}
