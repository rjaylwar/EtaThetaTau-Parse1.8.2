// Generated code from Butter Knife. Do not modify!
package com.rja.etaThetaTau.base;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class BaseFragment$$ViewBinder<T extends com.rja.etaThetaTau.base.BaseFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findOptionalView(source, 2131493159, null);
    target.mToolbar = finder.castView(view, 2131493159, "field 'mToolbar'");
  }

  @Override public void unbind(T target) {
    target.mToolbar = null;
  }
}
