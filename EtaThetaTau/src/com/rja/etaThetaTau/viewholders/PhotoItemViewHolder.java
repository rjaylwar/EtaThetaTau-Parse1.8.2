package com.rja.etaThetaTau.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rja.etaThetaTau.R;
import com.rja.etaThetaTau.interfaces.OnLinkClickedListener;
import com.rja.etaThetaTau.objects.HotFeedItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by rjaylward on 2/7/16
 */
public class PhotoItemViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.pvh_top_title_text) TextView mTitle;
    @Bind(R.id.pvh_date_text) TextView mDate;
    @Bind(R.id.pvh_image) ImageView mImage;
    @Bind(R.id.pvh_description_text) TextView mDescription;

    HotFeedItem mItem;
    OnLinkClickedListener mListener;

    public PhotoItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void load(HotFeedItem item, Context context, OnLinkClickedListener linkClickedListener) {
        mItem = item;
        mListener = linkClickedListener;

        try {
            loadHeading(item);
            loadImage(item, context);
            loadDescription(item);
        }
        catch (Exception e) {

        }
    }

    private void loadDescription(HotFeedItem item) {
        mDescription.setText(item.getMessage());
    }

    private void loadImage(HotFeedItem item, Context context) {
        if(item.getMainImage() != null)
            Glide.with(context).load(item.getMainImage()).into(mImage);
        else
            mImage.setVisibility(View.GONE);
    }

    private void loadHeading(HotFeedItem item) {
        mTitle.setText(item.getTitle());
        mDate.setText(item.getDate());
    }

    @OnClick(R.id.pvh_image)
    void onLinkClicked() {
        if(mListener != null && mItem.getLink() != null)
            mListener.onLinkClicked(mItem.getLink().getUrl());
    }
}
