// Generated code from Butter Knife. Do not modify!
package com.rja.etaThetaTau.activities;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class ScriptsFragment$$ViewBinder<T extends com.rja.etaThetaTau.activities.ScriptsFragment> extends com.rja.etaThetaTau.base.BaseFragment$$ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    super.bind(finder, target, source);

    View view;
    view = finder.findRequiredView(source, 2131493083, "field 'mWebView'");
    target.mWebView = finder.castView(view, 2131493083, "field 'mWebView'");
  }

  @Override public void unbind(T target) {
    super.unbind(target);

    target.mWebView = null;
  }
}
