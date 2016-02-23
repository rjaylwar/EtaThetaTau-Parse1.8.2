package com.rja.etaThetaTau.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.rja.etaThetaTau.ParseApplication;
import com.rja.etaThetaTau.R;
import com.rja.etaThetaTau.volley.VolleyContext;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by rjaylward on 10/7/15
 */
public abstract class BaseFragment extends Fragment implements BaseFragActivity.OnActivityBackPressedListener, VolleyContext {

    protected BaseActivity mActivity;
    protected View mRoot;

    @Nullable @Bind(R.id.toolbar)
    protected Toolbar mToolbar;

    protected boolean mFragmentClosed;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        mActivity = (BaseActivity) getActivity();
        setHasOptionsMenu(fragmentHasOptionsMenu());

        prepareFragment(bundle);
    }

    public boolean fragmentHasOptionsMenu() {
        return true;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        mRoot = inflater.inflate(getLayoutResId(), container, false);
        ButterKnife.bind(this, mRoot);

        if(mToolbar != null) {
            mActivity.setSupportActionBar(mToolbar);
            mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                if(isTopLevel())
                    mActivity.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_24dp);
                else if(getHomeAsUpIndicator() > 0)
                    mActivity.getSupportActionBar().setHomeAsUpIndicator(getHomeAsUpIndicator());

        }


        initLayout(bundle);
        return mRoot;
    }

    private boolean isTopLevel() {
        return false;
    }

    protected int getHomeAsUpIndicator() {
        return 0;
    }

    protected abstract int getLayoutResId();

    protected abstract void prepareFragment(Bundle bundle);

    protected abstract void initLayout(Bundle bundle);

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //avoid adding items to the menu when the activity is recreated
        menu.clear();

        if(getMenuResId() > 0)
            inflater.inflate(getMenuResId(), menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home :
                //Calls this class's onBackPressedCallback, which includes the listener and postFinish stuff
                mActivity.onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected int getMenuResId() {
        return 0;
    }

    @Override
    public void onActivityBackPressed() { }

    @Override
    public String getRequestFilter() {
        return null;
    }

    @Override
    public ParseApplication getHotApplication() {
        return (ParseApplication) getContext().getApplicationContext();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}