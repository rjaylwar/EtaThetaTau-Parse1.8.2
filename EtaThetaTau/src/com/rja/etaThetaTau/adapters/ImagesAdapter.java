package com.rja.etaThetaTau.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rja.etaThetaTau.R;
import com.rja.etaThetaTau.interfaces.OnClickAtIndexListener;
import com.rja.etaThetaTau.util.Print;
import com.rja.etaThetaTau.viewholders.ImageViewHolder;

import java.util.ArrayList;

/**
 * Created by rjaylward on 2/20/16
 */
public class ImagesAdapter extends RecyclerView.Adapter implements OnClickAtIndexListener {

    ArrayList<String> mUrls = new ArrayList<>();
    Context mContext;

    public ImagesAdapter(Context context) {
        mContext = context;
    }

    public void load(ArrayList<String> imageUrls) {
        mUrls = imageUrls;
        Print.log(imageUrls);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item_image, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ImageViewHolder) {
            ((ImageViewHolder) holder).load(mContext, mUrls.get(position), this);
        }
    }

    @Override
    public int getItemCount() {
        return mUrls.size();
    }

    @Override
    public void onClickAtIndex(int index) {
        //TODO something
    }
}
