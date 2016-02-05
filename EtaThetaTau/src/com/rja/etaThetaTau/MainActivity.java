package com.rja.etaThetaTau;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;

import com.rja.etaThetaTau.adapters.MainPagerAdapter;
import com.rja.etaThetaTau.base.ToolbarActivity;
import com.rja.etaThetaTau.helpers.SlidingPaneHelper;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by rjaylward on 2/4/16
 */
public class MainActivity extends ToolbarActivity {

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.navigation_view)
    NavigationView mNavigationView;
    @Bind(R.id.tabs)
    TabLayout mTabLayout;
    @Bind(R.id.view_pager)
    ViewPager mViewPager;

    private SlidingPaneHelper mSlidingPaneHelper;

    public static Intent createIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        mSlidingPaneHelper = new SlidingPaneHelper(this, mToolbar, mNavigationView, mDrawerLayout);
        mSlidingPaneHelper.loadView();

        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.addTab(mTabLayout.newTab().setText("Tonight"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Songs"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Script"));

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());

        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main_view_pager;
    }
}
