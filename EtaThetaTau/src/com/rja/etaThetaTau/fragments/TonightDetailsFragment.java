package com.rja.etaThetaTau.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.rja.etaThetaTau.R;
import com.rja.etaThetaTau.adapters.ImagesAdapter;
import com.rja.etaThetaTau.base.BaseFragment;
import com.rja.etaThetaTau.objects.HotFeedItem;
import com.rja.etaThetaTau.objects.Location;
import com.rja.etaThetaTau.views.ThreeByTwoTextView;

import butterknife.Bind;

/**
 * Created by rjaylward on 2/12/16
 */
public class TonightDetailsFragment extends BaseFragment {

    @Bind(R.id.backdrop)
    ImageView mToolbarBackdrop;

    @Bind(R.id.ft_snacks_3x2_layout)
    ThreeByTwoTextView mThreeByTwoTextView;
    @Bind(R.id.ft_message_text)
    TextView mMessageView;
    @Bind(R.id.ft_reminder_text)
    TextView mReminderView;
    @Bind(R.id.ft_theme_text)
    TextView mThemeView;
    @Bind(R.id.ft_location_text)
    TextView mLocationView;
    @Bind(R.id.ft_recycler_view)
    RecyclerView mRecyclerView;

    @Bind(R.id.ft_map)
    MapView mMapView;

    ImagesAdapter mImagesAdapter;

    public static Bundle createBundle(HotFeedItem hotFeedItem) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(HotFeedItem.class.getCanonicalName(), hotFeedItem);

        return bundle;
    }

    public static TonightDetailsFragment create(HotFeedItem hotFeedItem) {
        TonightDetailsFragment fragment = new TonightDetailsFragment();
        fragment.setArguments(createBundle(hotFeedItem));

        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_tonight_layout;
    }

    @Override
    protected void prepareFragment(Bundle bundle) {

    }

    @Override
    protected void initLayout(Bundle bundle) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mImagesAdapter = new ImagesAdapter(getContext());
        mRecyclerView.setAdapter(mImagesAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);

        HotFeedItem hotFeedItem = getArguments().getParcelable(HotFeedItem.class.getCanonicalName());

        if(hotFeedItem != null)
            loadView(hotFeedItem);
    }

    private void loadView(HotFeedItem hotFeedItem) {
        mToolbar.setTitle(hotFeedItem.getTitle());
        Glide.with(getContext()).load(hotFeedItem.getMainImage()).into(mToolbarBackdrop);

        mMessageView.setText(hotFeedItem.getMessage());
        mReminderView.setText(hotFeedItem.getReminder());

        loadTheme(hotFeedItem);
        loadLocation(hotFeedItem.getLocation());
        loadSnacksView(hotFeedItem);
    }

    private void loadSnacksView(HotFeedItem hotFeedItem) {
        mThreeByTwoTextView.setLeftText("Sweet", hotFeedItem.getSnacks().getSweet());
        mThreeByTwoTextView.setCenterText("Salty", hotFeedItem.getSnacks().getSalty());
        mThreeByTwoTextView.setRightText("Drinks", hotFeedItem.getSnacks().getDrinks());
    }

    private void loadLocation(final Location location) {
        mLocationView.setText(location.getTitle());
        mMapView.getMapAsync(new OnMapReadyCallback() {

            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMap.addMarker(
                        new MarkerOptions()
                                .position(new LatLng(location.getLatitude(), location.getLongitude()))
                                .title(location.getTitle())
                );
            }

        });
    }

    private void loadTheme(HotFeedItem hotFeedItem) {
        mThemeView.setText(hotFeedItem.getTheme());
        mImagesAdapter.load(hotFeedItem.getImages());
    }
}
