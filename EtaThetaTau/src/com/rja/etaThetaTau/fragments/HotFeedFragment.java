package com.rja.etaThetaTau.fragments;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.android.volley.VolleyError;
import com.rja.etaThetaTau.R;
import com.rja.etaThetaTau.activities.TonightDetailsActivity;
import com.rja.etaThetaTau.adapters.HotFeedAdapter;
import com.rja.etaThetaTau.api.ApiHelper;
import com.rja.etaThetaTau.base.BaseFragment;
import com.rja.etaThetaTau.interfaces.OnHotFeedItemClickListener;
import com.rja.etaThetaTau.objects.HotFeedItem;
import com.rja.etaThetaTau.objects.HotFeedItemResponse;
import com.rja.etaThetaTau.views.LoadMoreRecyclerView;
import com.rja.etaThetaTau.volley.VolleyRequestListener;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by rjaylward on 2/11/16
 */
public class HotFeedFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, OnHotFeedItemClickListener {

    @Bind(R.id.recycler_view)
    LoadMoreRecyclerView mRecyclerView;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout mRefreshLayout;

    HotFeedAdapter mFeedAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.basic_swipe_refresh_recyclerview;
    }

    @Override
    protected void prepareFragment(Bundle bundle) {
        mFeedAdapter = new HotFeedAdapter(mActivity, this);
    }

    @Override
    protected void initLayout(Bundle bundle) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mFeedAdapter);
        mRefreshLayout.setOnRefreshListener(this);
        onRefresh();
    }

    private void getFeedItems() {
        ApiHelper helper = new ApiHelper(this);
        helper.getFeedItems(new VolleyRequestListener<HotFeedItemResponse>() {

            @Override
            public void onResponse(HotFeedItemResponse response) {
                mRefreshLayout.setRefreshing(false);
                loadAdapter(response.getHotFeedItems());
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    private void loadAdapter(ArrayList<HotFeedItem> items) {
        mFeedAdapter.loadItems(items);
    }

    @Override
    public void onRefresh() {
        getFeedItems();
    }

    @Override
    public void onHotFeedItemClick(HotFeedItem item) {
//        mActivity.getSupportFragmentManager().beginTransaction()
//                .add(R.id.root, TonightDetailsFragment.create(item))
//                .addToBackStack(TonightDetailsFragment.class.getSimpleName())
//                .commit();

        mActivity.startActivity(TonightDetailsActivity.createIntent(getContext(), item));
    }
}
