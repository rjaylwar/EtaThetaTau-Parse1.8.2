// Generated code from Butter Knife. Do not modify!
package com.rja.etaThetaTau.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class HotFeedFragment$$ViewBinder<T extends com.rja.etaThetaTau.fragments.HotFeedFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493025, "field 'mRecyclerView'");
    target.mRecyclerView = finder.castView(view, 2131493025, "field 'mRecyclerView'");
    view = finder.findRequiredView(source, 2131493024, "field 'mRefreshLayout'");
    target.mRefreshLayout = finder.castView(view, 2131493024, "field 'mRefreshLayout'");
  }

  @Override public void unbind(T target) {
    target.mRecyclerView = null;
    target.mRefreshLayout = null;
  }
}
