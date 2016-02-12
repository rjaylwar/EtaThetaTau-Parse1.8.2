// Generated code from Butter Knife. Do not modify!
package com.rja.etaThetaTau.viewholders;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class LinkItemViewHolder$$ViewBinder<T extends com.rja.etaThetaTau.viewholders.LinkItemViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427456, "field 'mTitle'");
    target.mTitle = finder.castView(view, 2131427456, "field 'mTitle'");
    view = finder.findRequiredView(source, 2131427457, "field 'mDate'");
    target.mDate = finder.castView(view, 2131427457, "field 'mDate'");
    view = finder.findRequiredView(source, 2131427462, "field 'mDescription'");
    target.mDescription = finder.castView(view, 2131427462, "field 'mDescription'");
    view = finder.findRequiredView(source, 2131427461, "field 'mLinkTitle'");
    target.mLinkTitle = finder.castView(view, 2131427461, "field 'mLinkTitle'");
    view = finder.findRequiredView(source, 2131427459, "field 'mLinkImage'");
    target.mLinkImage = finder.castView(view, 2131427459, "field 'mLinkImage'");
    view = finder.findRequiredView(source, 2131427458, "field 'mCardLayout' and method 'onLinkClicked'");
    target.mCardLayout = finder.castView(view, 2131427458, "field 'mCardLayout'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onLinkClicked();
        }
      });
  }

  @Override public void unbind(T target) {
    target.mTitle = null;
    target.mDate = null;
    target.mDescription = null;
    target.mLinkTitle = null;
    target.mLinkImage = null;
    target.mCardLayout = null;
  }
}
