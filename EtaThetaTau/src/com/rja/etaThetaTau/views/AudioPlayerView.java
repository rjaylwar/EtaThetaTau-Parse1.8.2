package com.rja.etaThetaTau.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rja.etaThetaTau.R;
import com.rja.etaThetaTau.objects.AudioPlayer;

/**
 * Created by rjaylward on 10/15/15
 **/
public class AudioPlayerView extends RelativeLayout {

    private ImageView mImageView;
    private Button mPlayButton;
    private SeekBar mSeekBar;
    private ProgressBar mLoadingBar;
    private TextView mTrackTitleView;
    private AudioPlayer mAudioPlayer;
    private TextView mCountDown;
    private TextView mCountUp;
    private TextView mSubtitleView;

    public AudioPlayerView(Context context) {
        super(context);
        inflateLayout();
    }

    public AudioPlayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateLayout();
    }

    public AudioPlayerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflateLayout();
    }

    private void inflateLayout() {
        LayoutInflater.from(getContext()).inflate(R.layout.new_audio_player_layout, this, true);
//        int height = Math.round(getResources().getDimension(R.dimen.media_player_height));
//        setLayoutParams(new RelativeLayout.LayoutParams(height, height));

        mImageView = (ImageView) findViewById(R.id.media_player_image);
        mPlayButton = (Button) findViewById(R.id.media_player_play_button);
        mSeekBar = (SeekBar) findViewById(R.id.media_player_seek_bar);
        mLoadingBar = (ProgressBar) findViewById(R.id.media_player_progress_bar);
        mTrackTitleView = (TextView) findViewById(R.id.media_player_title_text_view);
        mCountDown = (TextView) findViewById(R.id.media_player_count_down);
        mCountUp = (TextView) findViewById(R.id.media_player_count_up);
        mSubtitleView = (TextView) findViewById(R.id.media_player_subtitle);

        mAudioPlayer = new AudioPlayer();
    }

    public void changeSourceUrl(String url) {
        mAudioPlayer.changeSourceLink(url);
    }

    public void setUpAudioPlayer(String audioUrl, String trackTitle, String trackSubtitle, String imageUrl) {
        if(imageUrl != null && mImageView != null)
            Glide.with(getContext()).load(imageUrl).into(mImageView);

        mAudioPlayer.setUpPlayer(mPlayButton, mSeekBar, mTrackTitleView, trackTitle, mLoadingBar,
                mCountDown, mCountUp);

        if(audioUrl != null)
            mAudioPlayer.setSourceLink(audioUrl);

        if(trackTitle != null)
            mTrackTitleView.setText(trackTitle);

        if(mSubtitleView != null)
            mSubtitleView.setText(trackSubtitle);

//            mSubtitleView.setText(Html.fromHtml(talk.getSubtitle()));
    }

    public void addLongClickListener(OnLongClickListener longClickListener) {
        mSubtitleView.setOnLongClickListener(longClickListener);
    }

    public void play() {
        mAudioPlayer.play();
    }

    public void pause() {
        mAudioPlayer.pause();
    }

    public void release() {
        if(mAudioPlayer != null)
            mAudioPlayer.releasePlayer();
    }
}
