package com.rja.etaThetaTau.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.rja.etaThetaTau.objects.Script;

import java.util.ArrayList;

/**
 * Created by rjaylward on 2/5/16
 */
public class ScriptsAdapter extends RecyclerView.Adapter {

    private ArrayList<Script> mScripts = new ArrayList<>();

    public ScriptsAdapter() {
    }

    public void load(ArrayList<Script> scripts) {
        mScripts = scripts;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mScripts.size();
    }

}
