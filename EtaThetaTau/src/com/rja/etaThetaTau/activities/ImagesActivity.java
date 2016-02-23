package com.rja.etaThetaTau.activities;

import android.content.Context;
import android.content.Intent;

import com.rja.etaThetaTau.base.BaseFragActivity;
import com.rja.etaThetaTau.base.BaseFragment;
import com.rja.etaThetaTau.fragments.ImagesFragment;

import java.util.ArrayList;

/**
 * Created by rjaylward on 2/23/16
 */
public class ImagesActivity extends BaseFragActivity {

    public static Intent createIntent(Context context, ArrayList<String> urls, int index) {
        Intent intent = new Intent(context, ImagesActivity.class);
        intent.putExtra(ImagesFragment.IMAGES, urls);
        intent.putExtra(ImagesFragment.INDEX, index);

        return intent;
    }

    @Override
    protected BaseFragment getFragment() {
        return ImagesFragment.create(getIntent().getStringArrayListExtra(ImagesFragment.IMAGES), getIntent().getIntExtra(ImagesFragment.INDEX, 0));
    }

}
