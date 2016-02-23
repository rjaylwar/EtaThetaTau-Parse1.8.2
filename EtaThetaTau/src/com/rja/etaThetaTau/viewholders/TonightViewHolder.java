package com.rja.etaThetaTau.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v8.renderscript.RenderScript;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rja.etaThetaTau.R;
import com.rja.etaThetaTau.interfaces.OnHotFeedItemClickListener;
import com.rja.etaThetaTau.objects.HotFeedItem;
import com.rja.etaThetaTau.util.Print;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by rjaylward on 2/11/16
 */
public class TonightViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @Bind(R.id.tvh_top_title_text) TextView mTopTitle;
    @Bind(R.id.tvh_date_text) TextView mDate;

    @Bind(R.id.tvh_image) ImageView mImage;
    @Bind(R.id.tvh_main_title_text) TextView mLocation;

    @Bind(R.id.tvh_description_text) TextView mDescription;

    @Bind(R.id.tvh_salty_text) TextView mSaltyName;
    @Bind(R.id.tvh_sweet_text) TextView mSweetName;
    @Bind(R.id.tvh_drinks_text) TextView mDrinksName;

    HotFeedItem mItem;
    RenderScript mRenderScript;
    OnHotFeedItemClickListener mClickListener;

    public TonightViewHolder(Context context, View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);

        try {
            mRenderScript = RenderScript.create(context.getApplicationContext());
        }
        catch(Exception e) {
            Print.log("Renderscript Exception");
            Print.exception(e);
        }
    }

    public void load(HotFeedItem item, Context context, OnHotFeedItemClickListener listener) {
        mItem = item;
        mClickListener = listener;

        try {
            loadHeading(item);
            loadImage(item, context, mRenderScript);
            loadDescription(item);
        }
        catch (Exception e) {

        }
    }

    private void loadDescription(HotFeedItem item) {
        mLocation.setText(item.getLocation().getTitle());

        mDescription.setText(item.getMessage());
        mSaltyName.setText(item.getSnacks().getSalty());
        mSweetName.setText(item.getSnacks().getSweet());
        mDrinksName.setText(item.getSnacks().getDrinks());
    }

    private void loadImage(HotFeedItem item, Context context, RenderScript renderScript) {
        if(renderScript != null)
            Glide.with(context)
                    .load(item.getMainImage())
                    .bitmapTransform(new BlurTransformation(context, 8))
                    .into(mImage);
        else
            Glide.with(context)
                    .load(item.getMainImage())
                    .into(mImage);

    }

    private void loadHeading(HotFeedItem item) {
        mTopTitle.setText(item.getTitle());
        mDate.setText(item.getDate());
    }

    @Override
    public void onClick(View v) {
        if(mClickListener != null)
            mClickListener.onHotFeedItemClick(mItem);
    }
}
