package com.rja.etaThetaTau.service;

import android.content.ComponentName;
import android.os.RemoteException;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.AppCompatActivity;

import com.rja.etaThetaTau.util.Print;

import java.util.List;

/**
 * Created by rjaylward on 3/6/16
 */
public class MediaControllerManager {

    MediaBrowserCompat mMediaBrowser;
    MediaControllerCompat mMediaController;
    MediaControllerCompat.TransportControls mTransportControls;
    AppCompatActivity mActivity;
    PlaybackStateCompat mPlaybackState;
    MediaButtonSet mMediaButtonSet;

    OnQueueChangedListener mQueueChangedListener;

    public MediaControllerManager(AppCompatActivity activity, MediaButtonSet mediaButtonSet, OnQueueChangedListener queueChangedListener) {
        mMediaButtonSet = mediaButtonSet;
        mQueueChangedListener = queueChangedListener;

        mMediaBrowser = new MediaBrowserCompat(activity,
                new ComponentName(activity, MediaPlayerService.class),
                mConnectionCallback, null);
    }

    public void onItemSelected(MediaSessionCompat.QueueItem item) {
        mTransportControls.skipToQueueItem(item.getQueueId());
//        mTransportControls.playFromUri();
    }

    @SuppressWarnings("FieldCanBeLocal")
    private MediaBrowserCompat.ConnectionCallback mConnectionCallback =
            new MediaBrowserCompat.ConnectionCallback() {
                @Override
                public void onConnected() {
                    Print.log("onConnected: session token ", mMediaBrowser.getSessionToken());

                    if (mMediaBrowser.getSessionToken() == null) {
                        throw new IllegalArgumentException("No Session token");
                    }

                    try {
                        mMediaController = new MediaControllerCompat(mActivity, mMediaBrowser.getSessionToken());
                    } catch(RemoteException e) {
                        e.printStackTrace();
                    }

                    mTransportControls = mMediaController.getTransportControls();
                    mMediaController.registerCallback(mSessionCallback);

                    mActivity.setSupportMediaController(mMediaController);
                    mPlaybackState = mMediaController.getPlaybackState();

                    List<MediaSessionCompat.QueueItem> queue = mMediaController.getQueue();

                    if(mQueueChangedListener != null)
                        mQueueChangedListener.onQueueViewShouldUpdate(queue);

                    onThePlaybackStateChanged(mPlaybackState);
                }

                @Override
                public void onConnectionFailed() {
                    Print.log("onConnectionFailed");
                }

                @Override
                public void onConnectionSuspended() {
                    Print.log("onConnectionSuspended");
                    mMediaController.unregisterCallback(mSessionCallback);
                    mTransportControls = null;
                    mMediaController = null;
                    mActivity.setSupportMediaController(null);
                }
            };

    private MediaControllerCompat.Callback mSessionCallback = new MediaControllerCompat.Callback() {

        @Override
        public void onSessionDestroyed() {
            Print.log("Session destroyed. Need to fetch a new Media Session");
        }

        @Override
        public void onPlaybackStateChanged(PlaybackStateCompat state) {
            if (state == null) {
                return;
            }
            Print.log("Received playback state change to state ", state.getState());
            mPlaybackState = state;
            onThePlaybackStateChanged(state);
        }

        @Override
        public void onQueueChanged(List<MediaSessionCompat.QueueItem> queue) {
            Print.log("onQueueChanged ", queue);
            if(mQueueChangedListener != null)
                mQueueChangedListener.onQueueChanged(queue);
        }
    };

    private void onThePlaybackStateChanged(PlaybackStateCompat state) {
        Print.log("onPlaybackStateChanged ", state);

        mMediaButtonSet.updateState(state);
    }

    public interface OnQueueChangedListener {
        void onQueueChanged(List<MediaSessionCompat.QueueItem> queue);
        void onQueueViewShouldUpdate(List<MediaSessionCompat.QueueItem> queue);
    }

//    if (queue != null) {
//        mQueueAdapter.clear();
//        mQueueAdapter.notifyDataSetInvalidated();
//        mQueueAdapter.addAll(queue);
//        mQueueAdapter.notifyDataSetChanged();
//    }
}
