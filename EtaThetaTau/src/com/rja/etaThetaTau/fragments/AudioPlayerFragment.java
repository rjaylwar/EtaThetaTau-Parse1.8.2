package com.rja.etaThetaTau.fragments;

import android.os.Bundle;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.rja.etaThetaTau.R;
import com.rja.etaThetaTau.base.BaseFragment;
import com.rja.etaThetaTau.objects.MediaItem;
import com.rja.etaThetaTau.service.AudioQueue;
import com.rja.etaThetaTau.service.MediaButtonSet;
import com.rja.etaThetaTau.service.MediaControllerManager;
import com.rja.etaThetaTau.service.MusicProvider;
import com.rja.etaThetaTau.service.OnCompleteListener;
import com.rja.etaThetaTau.util.Print;

import java.util.List;

import butterknife.Bind;

/**
 * Created by rjaylward on 3/8/16
 */
public class AudioPlayerFragment extends BaseFragment implements MediaControllerManager.OnQueueChangedListener, OnCompleteListener<MediaItem>,MediaControllerManager.OnMediaControllerConnected {

    @Bind(R.id.fap_search)
    EditText mSearch;
    @Bind(R.id.fap_image)
    ImageView mImage;
    @Bind(R.id.fap_title)
    TextView mTitle;
    @Bind(R.id.fap_subtitle)
    TextView mSubtitle;
    @Bind(R.id.fap_rewind)
    Button mRewind;
    @Bind(R.id.fap_play)
    Button mPlay;
    @Bind(R.id.fap_fast_forward)
    Button mFastForward;
    @Bind(R.id.fap_search_button)
    Button mSearchButton;

    private MusicProvider mMusicProvider;
    private MediaControllerManager mControllerManager;

    boolean mHasLoaded;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_audio_player;
    }

    @Override
    protected void prepareFragment(Bundle bundle) {
        mMusicProvider = new MusicProvider(this);
    }

    @Override
    protected void initLayout(Bundle bundle) {
        MediaButtonSet buttonSet = new MediaButtonSet.Builder(mActivity.getSupportMediaController())
                .setPlayPause(mPlay)
                .setFF(mFastForward)
                .setRW(mRewind)
                .build();

        mControllerManager = new MediaControllerManager(mActivity, buttonSet, this, this);

        mSearch.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        mSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch(v.getText().toString());
                }
                return false;
            }
        });

        mSearchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                performSearch(mSearch.getText().toString());
            }

        });
    }

    @Override
    public void onStart() {
        super.onStart();

        try {
            if(mControllerManager != null  && !mControllerManager.getMediaBrowser().isConnected())
                mControllerManager.connect();
        }
        catch(Exception e) {

        }
    }

    @Override
    public void onStop() {
        super.onStop();

        mControllerManager.disconnect();
    }

    private void performSearch(String artistName) {
        if(artistName != null && artistName.length() > 1)
            mMusicProvider.getMediaItem(artistName, this);
    }

    @Override
    public void onQueueChanged(List<MediaSessionCompat.QueueItem> queue) {

    }

    @Override
    public void onQueueViewShouldUpdate(List<MediaSessionCompat.QueueItem> queue) {

    }

    @Override
    public void onComplete(MediaItem object, Exception e) {
        if(e != null)
            Print.exception(e);
        else {
            long id = AudioQueue.getInstance().add(object);
//            if(mHasLoaded)
                mControllerManager.onItemSelected(id);
//            else
//                Toast.makeText(getContext(), "Error, media browser not connected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onMediaControllerConnected(MediaControllerCompat mediaControllerCompat) {
        //Not sure what needs to happen here, but I am sure something does
        Print.log("on media controller connected");
        mHasLoaded = true;
    }

    @Override
    public void onMediaControllerDisconected() {
        //Not sure what needs to happen here, but I am sure something does
        Print.log("on media controller disconnected");
        mHasLoaded = false;
    }
}
