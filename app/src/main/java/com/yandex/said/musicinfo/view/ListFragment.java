package com.yandex.said.musicinfo.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.ViewGroup;

import com.octo.android.robospice.SpiceManager;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.yandex.said.musicinfo.R;
import com.yandex.said.musicinfo.common.BaseFragment;
import com.yandex.said.musicinfo.common.MusicInfoListAdapter;
import com.yandex.said.musicinfo.di.components.IMainActivityComponent;
import com.yandex.said.musicinfo.model.ItemArtist;
import com.yandex.said.musicinfo.network.MusicInfoService;
import com.yandex.said.musicinfo.presenter.ListFragmentPresenterImpl;

import java.util.List;

import javax.inject.Inject;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.FadeInDownAnimator;

/**
 * Created by said on 26.03.16.
 */
public class ListFragment extends BaseFragment implements IListFragmentView {

    @Inject
    ListFragmentPresenterImpl presenter;

    protected SpiceManager spiceManager = new SpiceManager(MusicInfoService.class);

    AppCompatActivity activity;
    RecyclerView recyclerView;
    MusicInfoListAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    View rootView;
    ProgressWheel progressBar;

    public ListFragment() {

    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (AppCompatActivity) activity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {
            activity = (AppCompatActivity) context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_list, container, false);
            recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
            progressBar = (ProgressWheel) activity.findViewById(R.id.progress_wheel);

            layoutManager = new LinearLayoutManager(activity);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new FadeInDownAnimator());
            recyclerView.addOnItemTouchListener(new RecyclerTouchListener(activity.getApplicationContext(), recyclerView, new ClickListener() {
                @Override
                public void onClick(View view, int position) {
                    rootView.playSoundEffect(SoundEffectConstants.CLICK);
                    presenter.onItemClick(position);
                }

                @Override
                public void onLongClick(View view, int position) {

                }
            }));
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.getComponent(IMainActivityComponent.class).inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.init(this);
        presenter.onResume(spiceManager);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        activity.getSupportActionBar().setTitle("Исполнители");
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void showProgressDialog() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressDialog() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void startService() {
        if (!spiceManager.isStarted()) {
            spiceManager.start(activity);
        }
    }

    @Override
    public void stopService() {
        if (spiceManager.isStarted()) {
            spiceManager.shouldStop();
        }
    }

    @Override
    public void setMusicInfoListAdapter(List<ItemArtist> itemArtists, int totalArtists) {
        if (adapter == null) {
            adapter = new MusicInfoListAdapter(getActivity(), itemArtists, totalArtists);
            AlphaInAnimationAdapter animationAdapter = new AlphaInAnimationAdapter(adapter);

            animationAdapter.setFirstOnly(false);
            animationAdapter.setDuration(500);
            recyclerView.setAdapter(animationAdapter);
        }
    }

    @Override
    public void replaceToDetailFragment(int position) {
        FragmentManager fragmentManager = activity.getFragmentManager();
        DetailFragment detailFragment = DetailFragment.newInstance(adapter.getItem(position));

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, detailFragment)
                .addToBackStack(null)
                .commit();
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ListFragment.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ListFragment.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildAdapterPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}
