package com.rja.etaThetaTau.viewholders;

import android.content.Context;
import android.support.v7.widget.CardView;
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
 * Created by rjaylward on 2/11/16
 */
public class LinkItemViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.lvh_top_title_text)
    TextView mTitle;
    @Bind(R.id.lvh_date_text)
    TextView mDate;
    @Bind(R.id.lvh_description_text)
    TextView mDescription;

    @Bind(R.id.lvh_link_text)
    TextView mLinkTitle;
    @Bind(R.id.lvh_link_image)
    ImageView mLinkImage;
    @Bind(R.id.lvh_link_card_layout)
    CardView mCardLayout;

    HotFeedItem mItem;
    OnLinkClickedListener mLinkClickedListener;

    public LinkItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void load(HotFeedItem item, Context context, OnLinkClickedListener listener) {
        mItem = item;
        mLinkClickedListener = listener;

        try {
            loadHeading(item);
            loadLink(item, context);
            loadDescription(item);
        }
        catch (Exception e) {

        }
    }

    private void loadDescription(HotFeedItem item) {
        if(item.getMessage() != null)
            mDescription.setText(item.getMessage());
        else
            mDescription.setVisibility(View.GONE);
    }

    private void loadLink(HotFeedItem item, Context context) {
        if(item.getLink().getImage() != null)
            Glide.with(context).load(item.getLink().getImage()).into(mLinkImage);
        else
            Glide.with(context).load(R.drawable.default_hottie).into(mLinkImage); //TODO change default

        mLinkTitle.setText(item.getLink().getTitle());
    }

    private void loadHeading(HotFeedItem item) {
        mTitle.setText(item.getTitle());
        mDate.setText(item.getDate());
    }

    @OnClick(R.id.lvh_link_card_layout)
    void onLinkClicked() {
        if(mLinkClickedListener != null)
            mLinkClickedListener.onLinkClicked(mItem.getLink().getUrl());
    }


}
