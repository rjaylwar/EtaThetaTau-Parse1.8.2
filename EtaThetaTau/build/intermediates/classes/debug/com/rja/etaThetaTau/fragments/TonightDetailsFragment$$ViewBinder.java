// Generated code from Butter Knife. Do not modify!
package com.rja.etaThetaTau.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class TonightDetailsFragment$$ViewBinder<T extends com.rja.etaThetaTau.fragments.TonightDetailsFragment> extends com.rja.etaThetaTau.base.BaseFragment$$ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    super.bind(finder, target, source);

    View view;
    view = finder.findRequiredView(source, 2131493171, "field 'mToolbarBackdrop'");
    target.mToolbarBackdrop = finder.castView(view, 2131493171, "field 'mToolbarBackdrop'");
    view = finder.findRequiredView(source, 2131493107, "field 'mThreeByTwoTextView'");
    target.mThreeByTwoTextView = finder.castView(view, 2131493107, "field 'mThreeByTwoTextView'");
    view = finder.findRequiredView(source, 2131493109, "field 'mMessageView'");
    target.mMessageView = finder.castView(view, 2131493109, "field 'mMessageView'");
    view = finder.findRequiredView(source, 2131493111, "field 'mReminderView'");
    target.mReminderView = finder.castView(view, 2131493111, "field 'mReminderView'");
    view = finder.findRequiredView(source, 2131493113, "field 'mThemeView'");
    target.mThemeView = finder.castView(view, 2131493113, "field 'mThemeView'");
    view = finder.findRequiredView(source, 2131493116, "field 'mLocationView'");
    target.mLocationView = finder.castView(view, 2131493116, "field 'mLocationView'");
    view = finder.findRequiredView(source, 2131493114, "field 'mRecyclerView'");
    target.mRecyclerView = finder.castView(view, 2131493114, "field 'mRecyclerView'");
    view = finder.findRequiredView(source, 2131493117, "field 'mMapView'");
    target.mMapView = finder.castView(view, 2131493117, "field 'mMapView'");
  }

  @Override public void unbind(T target) {
    super.unbind(target);

    target.mToolbarBackdrop = null;
    target.mThreeByTwoTextView = null;
    target.mMessageView = null;
    target.mReminderView = null;
    target.mThemeView = null;
    target.mLocationView = null;
    target.mRecyclerView = null;
    target.mMapView = null;
  }
}
