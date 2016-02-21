package com.rja.etaThetaTau.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.rja.etaThetaTau.R;
import com.rja.etaThetaTau.interfaces.OnClickAtIndexListener;

import butterknife.Bind;

/**
 * Created by rjaylward on 2/20/16
 */
public class ImageViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.lvh_link_image)
    ImageView mLinkImage;

    public ImageViewHolder(View itemView) {
        super(itemView);
    }

    public void load(String imageUrl, OnClickAtIndexListener onClickAtIndexListener) {

    }
}
