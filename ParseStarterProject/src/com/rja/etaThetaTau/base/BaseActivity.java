package com.rja.etaThetaTau.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.rja.etaThetaTau.helpers.ProgressDialogHelper;

/**
 * Created by rjaylward on 9/22/15
 */
public abstract class BaseActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());
        initLayout();
    }

    public abstract int getLayoutId();

    public abstract void initLayout();

    public void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public Point getScreenSize() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        return size;
    }

    public void showProgressDialog(int msgResId) {
        showProgressDialog(getString(msgResId));
    }

    public void showProgressDialog(String msg) {
        mProgressDialog = ProgressDialogHelper.buildDialog(this, msg);
        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null)
            mProgressDialog.dismiss();

    }
}
