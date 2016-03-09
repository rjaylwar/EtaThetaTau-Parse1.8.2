package com.rja.etaThetaTau.fragments;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rja.etaThetaTau.R;
import com.rja.etaThetaTau.adapters.BigImagesAdapter;
import com.rja.etaThetaTau.base.BaseFragment;
import com.rja.etaThetaTau.util.Print;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by rjaylward on 2/23/16
 */
public class ImagesFragment extends BaseFragment {

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    BigImagesAdapter mAdapter;

    public static final String IMAGES = "images";
    public static final String INDEX = "index";

    public static Bundle createBundle(ArrayList<String> urls, int index) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(IMAGES, urls);
        bundle.putInt(INDEX, index);

        return bundle;
    }

    public static ImagesFragment create(ArrayList<String> urls, int index) {
        ImagesFragment fragment = new ImagesFragment();
        fragment.setArguments(createBundle(urls, index));

        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.basic_swipe_refresh_recyclerview;
    }

    @Override
    protected void prepareFragment(Bundle bundle) { }

    @Override
    protected void initLayout(Bundle bundle) {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(false);
            }

        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mAdapter = new BigImagesAdapter(getContext());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setBackgroundColor(ContextCompat.getColor(mActivity, R.color.cardview_light_background));

        mAdapter.load(getArguments().getStringArrayList(IMAGES));
        mRecyclerView.smoothScrollToPosition(getArguments().getInt(INDEX));

        Print.log("images", getArguments().getInt(INDEX), getArguments().getStringArrayList(IMAGES));
    }
}
