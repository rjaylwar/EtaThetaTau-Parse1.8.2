// Generated code from Butter Knife. Do not modify!
package com.rja.etaThetaTau.viewholders;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class TonightViewHolder$$ViewBinder<T extends com.rja.etaThetaTau.viewholders.TonightViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427477, "field 'mTopTitle'");
    target.mTopTitle = finder.castView(view, 2131427477, "field 'mTopTitle'");
    view = finder.findRequiredView(source, 2131427478, "field 'mDate'");
    target.mDate = finder.castView(view, 2131427478, "field 'mDate'");
    view = finder.findRequiredView(source, 2131427480, "field 'mImage'");
    target.mImage = finder.castView(view, 2131427480, "field 'mImage'");
    view = finder.findRequiredView(source, 2131427481, "field 'mLocation'");
    target.mLocation = finder.castView(view, 2131427481, "field 'mLocation'");
    view = finder.findRequiredView(source, 2131427482, "field 'mDescription'");
    target.mDescription = finder.castView(view, 2131427482, "field 'mDescription'");
    view = finder.findRequiredView(source, 2131427483, "field 'mSaltyName'");
    target.mSaltyName = finder.castView(view, 2131427483, "field 'mSaltyName'");
    view = finder.findRequiredView(source, 2131427484, "field 'mSweetName'");
    target.mSweetName = finder.castView(view, 2131427484, "field 'mSweetName'");
    view = finder.findRequiredView(source, 2131427485, "field 'mDrinksName'");
    target.mDrinksName = finder.castView(view, 2131427485, "field 'mDrinksName'");
  }

  @Override public void unbind(T target) {
    target.mTopTitle = null;
    target.mDate = null;
    target.mImage = null;
    target.mLocation = null;
    target.mDescription = null;
    target.mSaltyName = null;
    target.mSweetName = null;
    target.mDrinksName = null;
  }
}
