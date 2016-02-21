package com.rja.etaThetaTau.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rja.etaThetaTau.R;

import java.util.ArrayList;

/**
 * Created by rjaylward on 2/12/16
 */
public class ThreeByTwoTextView extends LinearLayout {

    private ArrayList<TextView> mTitles = new ArrayList<>(3);
    private ArrayList<TextView> mSubtitles = new ArrayList<>(3);

    private static final int LEFT_INDEX = 0;
    private static final int CENTER_INDEX = 1;
    private static final int RIGHT_INDEX = 2;

    public ThreeByTwoTextView(Context context) {
        super(context);
        setUp();
    }

    public ThreeByTwoTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUp();
    }

    public ThreeByTwoTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUp();
    }

    private void setUp() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_snacks, this, true);

        TextView leftTitle = (TextView) findViewById(R.id.vs_left_title);
        TextView leftSubtitle = (TextView) findViewById(R.id.vs_left_subtitle);
        TextView centerTitle = (TextView) findViewById(R.id.vs_center_title);
        TextView centerSubtitle = (TextView) findViewById(R.id.vs_center_subtitle);
        TextView rightTitle = (TextView) findViewById(R.id.vs_right_title);
        TextView rightSubtitle = (TextView) findViewById(R.id.vs_right_subtitle);

        mTitles.add(LEFT_INDEX, leftTitle);
        mTitles.add(CENTER_INDEX, centerTitle);
        mTitles.add(RIGHT_INDEX, rightTitle);

        mSubtitles.add(LEFT_INDEX, leftSubtitle);
        mSubtitles.add(CENTER_INDEX, centerSubtitle);
        mSubtitles.add(RIGHT_INDEX, rightSubtitle);
    }

    public void setLeftText(String title, String subtitle) {
        setTextAtIndex(title, subtitle, LEFT_INDEX);
    }

    public void setCenterText(String title, String subtitle) {
        setTextAtIndex(title, subtitle, CENTER_INDEX);
    }

    public void setRightText(String title, String subtitle) {
        setTextAtIndex(title, subtitle, RIGHT_INDEX);
    }

    private void setTextAtIndex(String title, String subtitle, int index) {
        mTitles.get(index).setText(title);
        mSubtitles.get(index).setText(subtitle);
    }
}
