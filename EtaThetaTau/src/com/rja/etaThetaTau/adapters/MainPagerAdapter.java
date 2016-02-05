package com.rja.etaThetaTau.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.rja.etaThetaTau.ScriptFragment;
import com.rja.etaThetaTau.SongsFragment;
import com.rja.etaThetaTau.TonightFragment;

import java.util.ArrayList;

/**
 * Created by rjaylward on 2/4/16
 */
public class MainPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private int mTabs;

    public MainPagerAdapter(FragmentManager fm, int tabs) {
        super(fm);
        mTabs = tabs;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;

        switch (position) {
            case 0:
                fragment = TonightFragment.newInstance(position + 1);
                break;
            case 1:
                fragment = SongsFragment.newInstance(position + 1);
                break;
            case 2:
                fragment = ScriptFragment.newInstance(position + 1);
                break;
            default:
                throw new IllegalArgumentException("tab ordinal not found");
        }

        mFragments.add(fragment);
        return fragment;
    }

    @Override
    public int getCount() {
        return mTabs;
    }

    public ArrayList<Fragment> getFragments() {
        return mFragments;
    }


}