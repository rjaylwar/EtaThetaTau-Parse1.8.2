// Generated code from Butter Knife. Do not modify!
package com.rja.etaThetaTau.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class AudioPlayerFragment$$ViewBinder<T extends com.rja.etaThetaTau.fragments.AudioPlayerFragment> extends com.rja.etaThetaTau.base.BaseFragment$$ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    super.bind(finder, target, source);

    View view;
    view = finder.findRequiredView(source, 2131493076, "field 'mSearch'");
    target.mSearch = finder.castView(view, 2131493076, "field 'mSearch'");
    view = finder.findRequiredView(source, 2131493078, "field 'mImage'");
    target.mImage = finder.castView(view, 2131493078, "field 'mImage'");
    view = finder.findRequiredView(source, 2131493079, "field 'mTitle'");
    target.mTitle = finder.castView(view, 2131493079, "field 'mTitle'");
    view = finder.findRequiredView(source, 2131493080, "field 'mSubtitle'");
    target.mSubtitle = finder.castView(view, 2131493080, "field 'mSubtitle'");
    view = finder.findRequiredView(source, 2131493081, "field 'mRewind'");
    target.mRewind = finder.castView(view, 2131493081, "field 'mRewind'");
    view = finder.findRequiredView(source, 2131493082, "field 'mPlay'");
    target.mPlay = finder.castView(view, 2131493082, "field 'mPlay'");
    view = finder.findRequiredView(source, 2131493083, "field 'mFastForward'");
    target.mFastForward = finder.castView(view, 2131493083, "field 'mFastForward'");
    view = finder.findRequiredView(source, 2131493077, "field 'mSearchButton'");
    target.mSearchButton = finder.castView(view, 2131493077, "field 'mSearchButton'");
  }

  @Override public void unbind(T target) {
    super.unbind(target);

    target.mSearch = null;
    target.mImage = null;
    target.mTitle = null;
    target.mSubtitle = null;
    target.mRewind = null;
    target.mPlay = null;
    target.mFastForward = null;
    target.mSearchButton = null;
  }
}
