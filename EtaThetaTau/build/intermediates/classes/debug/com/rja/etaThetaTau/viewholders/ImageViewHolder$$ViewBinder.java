// Generated code from Butter Knife. Do not modify!
package com.rja.etaThetaTau.viewholders;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ImageViewHolder$$ViewBinder<T extends com.rja.etaThetaTau.viewholders.ImageViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493118, "field 'mLinkImage' and method 'onImageClick'");
    target.mLinkImage = finder.castView(view, 2131493118, "field 'mLinkImage'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onImageClick();
        }
      });
  }

  @Override public void unbind(T target) {
    target.mLinkImage = null;
  }
}
