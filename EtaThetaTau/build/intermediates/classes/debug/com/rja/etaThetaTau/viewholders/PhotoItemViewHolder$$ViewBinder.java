// Generated code from Butter Knife. Do not modify!
package com.rja.etaThetaTau.viewholders;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class PhotoItemViewHolder$$ViewBinder<T extends com.rja.etaThetaTau.viewholders.PhotoItemViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493039, "field 'mTitle'");
    target.mTitle = finder.castView(view, 2131493039, "field 'mTitle'");
    view = finder.findRequiredView(source, 2131493040, "field 'mDate'");
    target.mDate = finder.castView(view, 2131493040, "field 'mDate'");
    view = finder.findRequiredView(source, 2131493041, "field 'mImage' and method 'onLinkClicked'");
    target.mImage = finder.castView(view, 2131493041, "field 'mImage'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onLinkClicked();
        }
      });
    view = finder.findRequiredView(source, 2131493042, "field 'mDescription'");
    target.mDescription = finder.castView(view, 2131493042, "field 'mDescription'");
  }

  @Override public void unbind(T target) {
    target.mTitle = null;
    target.mDate = null;
    target.mImage = null;
    target.mDescription = null;
  }
}
