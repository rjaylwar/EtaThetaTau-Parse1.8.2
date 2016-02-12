package com.rja.etaThetaTau.adapters;

import android.content.ActivityNotFoundException;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rja.etaThetaTau.R;
import com.rja.etaThetaTau.base.BaseActivity;
import com.rja.etaThetaTau.interfaces.OnHotFeedItemClickListener;
import com.rja.etaThetaTau.interfaces.OnLinkClickedListener;
import com.rja.etaThetaTau.objects.HotFeedItem;
import com.rja.etaThetaTau.util.Print;
import com.rja.etaThetaTau.viewholders.LinkItemViewHolder;
import com.rja.etaThetaTau.viewholders.PhotoItemViewHolder;
import com.rja.etaThetaTau.viewholders.TonightViewHolder;

import java.util.ArrayList;

/**
 * Created by rjaylward on 2/7/16
 */
public class HotFeedAdapter extends RecyclerView.Adapter implements OnHotFeedItemClickListener, OnLinkClickedListener {

    private ArrayList<HotFeedItem> mItems = new ArrayList<>();
    private BaseActivity mActivity;

    public HotFeedAdapter(BaseActivity activity) {
       mActivity = activity;
    }

    public void loadItems(ArrayList<HotFeedItem> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Print.log("View Type int", viewType);

        switch (viewType) {
            case TONIGHT:
                return new TonightViewHolder(LayoutInflater.from(mActivity).inflate(R.layout.card_tonight_holder, parent, false));
            case POST:
                return new PhotoItemViewHolder(LayoutInflater.from(mActivity).inflate(R.layout.card_photo_holder, parent, false));
            case LINK:
                return new LinkItemViewHolder(LayoutInflater.from(mActivity).inflate(R.layout.card_link_holder, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof TonightViewHolder)
            ((TonightViewHolder) holder).load(mItems.get(position), mActivity, this);
        if(holder instanceof PhotoItemViewHolder)
            ((PhotoItemViewHolder) holder).load(mItems.get(position), mActivity, this);
        if(holder instanceof LinkItemViewHolder)
            ((LinkItemViewHolder) holder).load(mItems.get(position), mActivity, this);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return typeToInt(mItems.get(position).getType().toLowerCase());
    }

    public static final int TONIGHT = 1;
    public static final int POST = 2;
    public static final int LINK = 3;

    private int typeToInt(String s) {
        if(s != null) {
            switch (s) {
                case "tonight":
                    return TONIGHT;
                case "link":
                    return LINK;
                default:
                    return POST;
            }
        }
        else
            return POST;
    }

    @Override
    public void onHotFeedItemClick(HotFeedItem item) {

    }

    @Override
    public void onLinkClicked(String url) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder(null);
        CustomTabsIntent intent = builder.setToolbarColor(ContextCompat.getColor(mActivity, R.color.hot_dark_blue))
//                .setStartAnimations(mActivity, R.anim.slide_from_bottom, R.anim.shrink_into_background)
//                .setExitAnimations(mActivity, R.anim.expand_into_foreground, R.anim.slide_to_bottom)
                .build();

        try {
            intent.launchUrl(mActivity, Uri.parse(url));
        }
        catch(ActivityNotFoundException e) {
            if(url.contains("HTTPS://")) { //can't parse it if the http is caps, don't know why
                try {
                    intent.launchUrl(mActivity, Uri.parse(url.replace("HTTPS://", "https://")));
                } catch(ActivityNotFoundException e1) {
                    Print.exception(e1);
                }
            }
            else if(url.contains("HTTP://")) { //can't parse it if the http is caps, don't know why
                try {
                    intent.launchUrl(mActivity, Uri.parse(url.replace("HTTP://", "http://")));
                } catch(ActivityNotFoundException e1) {
                    Print.exception(e1);
                }
            }
            else
                Print.exception(e);
        }
    }
}
