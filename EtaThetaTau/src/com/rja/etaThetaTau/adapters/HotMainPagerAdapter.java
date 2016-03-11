package com.rja.etaThetaTau.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.rja.etaThetaTau.activities.ScriptsFragment;
import com.rja.etaThetaTau.fragments.AudioPlayerFragment;
import com.rja.etaThetaTau.fragments.HotFeedFragment;

import java.util.ArrayList;

/**
 * Created by rjaylward on 9/26/15
 */
public class HotMainPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private int mTabs;

    public HotMainPagerAdapter(FragmentManager fm, int tabs) {
        super(fm);
        mTabs = tabs;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;

        switch (position) {
            case 0:
                fragment = new HotFeedFragment();
                break;
            case 1:
                fragment = new AudioPlayerFragment();
                break;
            case 2:
                fragment = new ScriptsFragment();
                break;
            case 3:
                fragment = new HotFeedFragment();
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