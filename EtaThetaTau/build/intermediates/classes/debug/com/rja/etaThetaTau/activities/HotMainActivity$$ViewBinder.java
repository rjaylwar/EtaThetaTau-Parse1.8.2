// Generated code from Butter Knife. Do not modify!
package com.rja.etaThetaTau.activities;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class HotMainActivity$$ViewBinder<T extends com.rja.etaThetaTau.activities.HotMainActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427439, "field 'mDrawerLayout'");
    target.mDrawerLayout = finder.castView(view, 2131427439, "field 'mDrawerLayout'");
    view = finder.findRequiredView(source, 2131427441, "field 'mNavigationView'");
    target.mNavigationView = finder.castView(view, 2131427441, "field 'mNavigationView'");
    view = finder.findRequiredView(source, 2131427557, "field 'mTabLayout'");
    target.mTabLayout = finder.castView(view, 2131427557, "field 'mTabLayout'");
    view = finder.findRequiredView(source, 2131427440, "field 'mViewPager'");
    target.mViewPager = finder.castView(view, 2131427440, "field 'mViewPager'");
  }

  @Override public void unbind(T target) {
    target.mDrawerLayout = null;
    target.mNavigationView = null;
    target.mTabLayout = null;
    target.mViewPager = null;
  }
}