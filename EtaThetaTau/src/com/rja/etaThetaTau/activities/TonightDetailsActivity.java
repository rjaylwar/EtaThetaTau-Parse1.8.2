package com.rja.etaThetaTau.activities;

import android.content.Context;
import android.content.Intent;

import com.rja.etaThetaTau.base.BaseFragActivity;
import com.rja.etaThetaTau.base.BaseFragment;
import com.rja.etaThetaTau.fragments.TonightDetailsFragment;
import com.rja.etaThetaTau.objects.HotFeedItem;

/**
 * Created by rjaylward on 2/20/16
 */
public class TonightDetailsActivity extends BaseFragActivity {

    public static Intent createIntent(Context context, HotFeedItem hotFeedItem) {
        Intent intent = new Intent(context, TonightDetailsActivity.class);
        intent.putExtra(HotFeedItem.class.getCanonicalName(), hotFeedItem);

        return intent;
    }

    @Override
    protected BaseFragment getFragment() {
        return TonightDetailsFragment.create(getIntent().<HotFeedItem>getParcelableExtra(HotFeedItem.class.getCanonicalName()));
    }
}
