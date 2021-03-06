package com.rja.etaThetaTau.base;

import android.support.v4.app.Fragment;

import com.rja.etaThetaTau.R;

/**
 * Created by rjaylward on 9/22/15
 */
public abstract class BaseFragActivity extends BaseActivity {

    private Fragment mFragment;
    private static final String FRAGMENT_TAG = "frag_tag";
    private OnActivityBackPressedListener mListener;

    @Override
    public int getLayoutId() {
        return R.layout.activity_fragment;
    }

    @Override
    public void initLayout() {
        mFragment = getFragment();
//        this.setOnActivityBackPressedListener(mFragment);
        mFragment.setArguments(getIntent().getExtras());

        if(mFragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.root, mFragment, FRAGMENT_TAG)
                    .commit();
        }
    }

    protected abstract Fragment getFragment();

    public void setOnActivityBackPressedListener(OnActivityBackPressedListener listener) {
        mListener = listener;
    }

    public interface OnActivityBackPressedListener {
        void onActivityBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if(mListener != null)
            mListener.onActivityBackPressed();
    }
}
