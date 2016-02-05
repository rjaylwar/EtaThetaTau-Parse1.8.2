package com.rja.etaThetaTau.base;

import android.support.v4.app.Fragment;

/**
 * Created by rjaylward on 10/7/15
 */
public class BaseFragment extends Fragment implements BaseFragActivity.OnActivityBackPressedListener {

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onActivityBackPressed() { }
}