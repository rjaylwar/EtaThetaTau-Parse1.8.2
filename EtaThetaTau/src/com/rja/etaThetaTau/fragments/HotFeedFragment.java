package com.rja.etaThetaTau.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.android.volley.VolleyError;
import com.rja.etaThetaTau.R;
import com.rja.etaThetaTau.adapters.HotFeedAdapter;
import com.rja.etaThetaTau.api.ApiHelper;
import com.rja.etaThetaTau.base.BaseFragment;
import com.rja.etaThetaTau.objects.HotFeedItem;
import com.rja.etaThetaTau.objects.HotFeedItemResponse;
import com.rja.etaThetaTau.views.LoadMoreRecyclerView;
import com.rja.etaThetaTau.volley.VolleyRequestListener;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by rjaylward on 2/11/16
 */
public class HotFeedFragment extends BaseFragment {

    @Bind(R.id.recycler_view)
    LoadMoreRecyclerView mRecyclerView;

    HotFeedAdapter mFeedAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.basic_swipe_refresh_recyclerview;
    }

    @Override
    protected void prepareFragment(Bundle bundle) {
        mFeedAdapter = new HotFeedAdapter(mActivity);
    }

    @Override
    protected void initLayout(Bundle bundle) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mFeedAdapter);
        getFeedItems();
    }

    private void getFeedItems() {
        ApiHelper helper = new ApiHelper(this);
        helper.getFeedItems(new VolleyRequestListener<HotFeedItemResponse>() {

            @Override
            public void onResponse(HotFeedItemResponse response) {
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

}
