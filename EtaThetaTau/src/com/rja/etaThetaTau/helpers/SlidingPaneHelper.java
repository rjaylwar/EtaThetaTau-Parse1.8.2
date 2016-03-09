package com.rja.etaThetaTau.helpers;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rja.etaThetaTau.LoginActivity;
import com.rja.etaThetaTau.R;
import com.rja.etaThetaTau.base.BaseActivity;

/**
 * Created by rjaylward on 2/4/16
 */
public class SlidingPaneHelper {

    private BaseActivity mActivity;
    private NavigationView mNavigationView;
    private TextView mName;
    private TextView mEmail;
    private ImageView mProfileImage;

    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private OnNavDrawerToggledListener mListener;

    private void createView() {
//        View v = LayoutInflater.from(mActivity).inflate(R.layout.header_layout, mNavigationView, false);
//
//        mProfileImage = (ImageView) v.findViewById(R.id.header_profile_image);
//        mEmail = (TextView) v.findViewById(R.id.header_email);
//        mName = (TextView) v.findViewById(R.id.header_username);
//
//        mNavigationView.addHeaderView(v);
    }

    public void loadView() {
//        Glide.with(mActivity).load(Preferences.getInstance().getImageUrl()).into(mProfileImage);
//        mEmail.setText(Preferences.getInstance().getEmail());
//        mName.setText(Preferences.getInstance().getName());
    }

    public SlidingPaneHelper(BaseActivity activity, Toolbar toolbar, NavigationView navigationView, DrawerLayout drawerLayout) {
        mActivity = activity;
        mToolbar = toolbar;
        mDrawerLayout = drawerLayout;
        mNavigationView = navigationView;

        setUp();
    }

    public void setUp() {
        mNavigationView.setBackgroundColor(ContextCompat.getColor(mActivity, R.color.um_dark_blue));
        mNavigationView.setItemIconTintList(ColorStateList.valueOf(ContextCompat.getColor(mActivity, R.color.white)));
        mNavigationView.setItemTextColor(ColorStateList.valueOf(ContextCompat.getColor(mActivity, R.color.white)));
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if (!menuItem.isChecked())
                    menuItem.setChecked(true);

                switch (menuItem.getItemId()) {
//                    case R.id.menu_item_rideshare:
//                        if (!(mActivity instanceof RideShareActivity))
//                            mActivity.startActivity(RideShareActivity.createIntent(mActivity));
//                        return true;
//                    case R.id.menu_item_um:
//                        if (!(mActivity instanceof MainActivity))
//                            mActivity.startActivity(MainActivity.createIntent(mActivity));
//                        return true;
//                    case R.id.menu_item_settings:
//                        if (!(mActivity instanceof LoginActivity))
//                            mActivity.startActivity(LoginActivity.createIntent(mActivity));
//                        return true;
                    case R.id.menu_item_feedback:
                        mDrawerLayout.closeDrawer(Gravity.LEFT);
                        mActivity.startActivity(new Intent(mActivity, LoginActivity.class));
                        return true;
                    default:
                        return true;
                }
            }
        });
        createView();
        initDrawer(mToolbar);
    }

    public void initDrawer(@NonNull Toolbar toolbar) {
        if(mDrawerLayout != null) {
            ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(mActivity, mDrawerLayout, toolbar, 0, 0) {
                @Override
                public void onDrawerClosed(View view) {
                    if(mListener != null)
                        mListener.onDrawerClosed(view);
                }

                @Override
                public void onDrawerOpened(View drawerView) {
                    loadView();

                    if(mListener != null)
                        mListener.onDrawerOpened(drawerView);

//                    if(mActivity instanceof MainActivity)
//                        mNavigationView.getMenu().getItem(0).setChecked(true);
//                    else if(mActivity instanceof RideShareActivity)
//                        mNavigationView.getMenu().getItem(1).setChecked(true);
//                    else if(mActivity instanceof LoginActivity)
//                        mNavigationView.getMenu().getItem(2).setChecked(true);
                }
            };

            mDrawerLayout.setDrawerListener(drawerToggle);
            drawerToggle.syncState();
        }
    }

    public void setOnNavDrawerToggledListener(OnNavDrawerToggledListener drawerToggledListener) {
        mListener = drawerToggledListener;
    }

    public interface OnNavDrawerToggledListener {

        void onDrawerOpened(View drawerView);
        void onDrawerClosed(View view);

    }

}
