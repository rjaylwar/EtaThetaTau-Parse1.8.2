// Generated code from Butter Knife. Do not modify!
package com.rja.etaThetaTau.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class ImagesFragment$$ViewBinder<T extends com.rja.etaThetaTau.fragments.ImagesFragment> extends com.rja.etaThetaTau.base.BaseFragment$$ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    super.bind(finder, target, source);

    View view;
    view = finder.findRequiredView(source, 2131493025, "field 'mRecyclerView'");
    target.mRecyclerView = finder.castView(view, 2131493025, "field 'mRecyclerView'");
    view = finder.findRequiredView(source, 2131493024, "field 'mSwipeRefreshLayout'");
    target.mSwipeRefreshLayout = finder.castView(view, 2131493024, "field 'mSwipeRefreshLayout'");
  }

  @Override public void unbind(T target) {
    super.unbind(target);

    target.mRecyclerView = null;
    target.mSwipeRefreshLayout = null;
  }
}
