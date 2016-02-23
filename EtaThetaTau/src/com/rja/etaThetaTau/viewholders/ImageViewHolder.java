package com.rja.etaThetaTau.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.rja.etaThetaTau.R;
import com.rja.etaThetaTau.interfaces.OnClickAtIndexListener;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by rjaylward on 2/20/16
 */
public class ImageViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.lii_image)
    ImageView mLinkImage;

    OnClickAtIndexListener mAtIndexListener;

    public ImageViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void load(Context context, String imageUrl, OnClickAtIndexListener onClickAtIndexListener) {
        mAtIndexListener = onClickAtIndexListener;
        Glide.with(context).load(imageUrl).into(mLinkImage);
    }

    @OnClick(R.id.lii_image)
    void onImageClick() {
        if(mAtIndexListener != null)
            mAtIndexListener.onClickAtIndex(getAdapterPosition());
    }
}
