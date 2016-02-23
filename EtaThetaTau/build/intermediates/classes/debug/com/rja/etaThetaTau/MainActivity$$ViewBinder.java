// Generated code from Butter Knife. Do not modify!
package com.rja.etaThetaTau;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MainActivity$$ViewBinder<T extends com.rja.etaThetaTau.MainActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493014, "field 'mDrawerLayout'");
    target.mDrawerLayout = finder.castView(view, 2131493014, "field 'mDrawerLayout'");
    view = finder.findRequiredView(source, 2131493016, "field 'mNavigationView'");
    target.mNavigationView = finder.castView(view, 2131493016, "field 'mNavigationView'");
    view = finder.findRequiredView(source, 2131493159, "field 'mTabLayout'");
    target.mTabLayout = finder.castView(view, 2131493159, "field 'mTabLayout'");
    view = finder.findRequiredView(source, 2131493015, "field 'mViewPager'");
    target.mViewPager = finder.castView(view, 2131493015, "field 'mViewPager'");
  }

  @Override public void unbind(T target) {
    target.mDrawerLayout = null;
    target.mNavigationView = null;
    target.mTabLayout = null;
    target.mViewPager = null;
  }
}
