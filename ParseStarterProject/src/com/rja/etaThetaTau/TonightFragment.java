package com.rja.etaThetaTau;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.InputStream;
import java.util.ArrayList;


public class TonightFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    private String userType;
    private String saltyString = "Salty:Name";
    private String sweetString = "Sweet:Name";
    private String drinksString = "Drinks:Name";
    private String tonightDescriptionString = "Tap the image to see the Hottie of the Day!";
    private String tonightTitle = "Tonight";
    private String hotdDescriptionString = "Tap the image to see tonights theme!";
    private String hotdName = "Hottie";
    private TextView saltyTextView;
    private TextView sweetTextView;
    private TextView drinksTextView;
    private TextView descriptionTextView;
    private TextView tonightTitleView;
    private ImageView tonightImageView;
    private ImageView hotdImageView;
    private String imageWasClickedUrl = "instagram://user?username=etathetatau";
    private LinearLayout bottomBar;
    private TextView saltyBottom;
    private TextView sweetBottom;
    private TextView drinksBottom;


    public static TonightFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        TonightFragment fragment = new TonightFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
        //loadSavedPreferences();

    }

    private void loadSavedPreferences() {
        SharedPreferences settings = getActivity().getSharedPreferences("UserInfo", 0);
        userType = settings.getString("user", "");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tonight, container, false);
        tonightImageView = (ImageView) view.findViewById(R.id.tonight_image_view);
        hotdImageView = (ImageView) view.findViewById(R.id.hotd_image_view);
        saltyTextView = (TextView) view.findViewById(R.id.salty_text_view);
        sweetTextView = (TextView) view.findViewById(R.id.sweet_text_view);
        drinksTextView = (TextView) view.findViewById(R.id.drinks_text_view);
        tonightTitleView = (TextView) view.findViewById(R.id.tonight_title);
        descriptionTextView = (TextView) view.findViewById(R.id.tonight_text_view);
        TextView reloadTextView = (TextView) view.findViewById(R.id.tonight_eta_text_view);
        bottomBar = (LinearLayout) view.findViewById(R.id.tonight_3splitBar_bottom);
        saltyBottom = (TextView) view.findViewById(R.id.saltybottom_text_view);
        sweetBottom = (TextView) view.findViewById(R.id.sweetbottom_text_view);
        drinksBottom = (TextView) view.findViewById(R.id.drinksbottom_text_view);
        loadSavedPreferences();

        downloadTonightInfo();

        tonightImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                new CrossFader(tonightImageView, hotdImageView, 150).start();
                tonightTitleView.setText(hotdName);
                sweetTextView.setText("HOTTIE");
                saltyTextView.setText("OF THE");
                drinksTextView.setText("DAY");
                descriptionTextView.setText(hotdDescriptionString);
                sweetBottom.setText("So Hot!");
                saltyBottom.setText("So Sweaty!");
                drinksBottom.setText("So Sweet!");
                return false;
            }
        });

        hotdImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                new CrossFader(hotdImageView, tonightImageView, 150).start();
                tonightTitleView.setText(tonightTitle);
                saltyTextView.setText(saltyString);
                sweetTextView.setText(sweetString);
                drinksTextView.setText(drinksString);
                descriptionTextView.setText(tonightDescriptionString);
                sweetBottom.setText("~ Sweet ~");
                saltyBottom.setText("~ Salty ~");
                drinksBottom.setText("~ Drinks ~");
                return false;
            }
        });

        tonightImageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(imageWasClickedUrl));
                startActivity(browserIntent);
                return false;
            }
        });

        hotdImageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(imageWasClickedUrl));
                startActivity(browserIntent);
                return false;
            }
        });

        reloadTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                downloadTonightInfo();
                return false;
            }
        });

        sweetTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                animateAppear(sweetBottom);
                return false;
            }
        });

        saltyTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                animateAppear(saltyBottom);
                return false;
            }
        });

        drinksTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                animateAppear(drinksBottom);
                return false;
            }
        });

        return view;

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);


    }

    public void downloadTonightInfo() {
        ParseQuery query = ParseQuery.getQuery("tonight");
        query.orderByDescending("createdAt");
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (object == null) {
                    //Log.d("tonight", "the request failed");
                } else {
                    //Log.d("tonight","retrieved tonight object");
                    if (object.getString("image_url")!= null){
                        new DownloadImageTask(tonightImageView)
                                .execute(object.getString("image_url"));}
                    if (object.getString("hotd_image_url") != null) {
                        new DownloadImageTask(hotdImageView).execute(object.getString("hotd_image_url"));
                    }

                    hotdImageView.setVisibility(View.GONE);
                    tonightImageView.setVisibility(View.VISIBLE);

                    if (object.getString("salty_snack") != null) {
                        saltyTextView.setText(object.getString("salty_snack"));
                        saltyString = object.getString("salty_snack");
                    }
                    if (object.getString("sweet_snack") != null) {
                        sweetTextView.setText(object.getString("sweet_snack"));
                        sweetString = object.getString("sweet_snack");
                    }
                    if (object.getString("drinks") != null) {
                        drinksTextView.setText(object.getString("drinks"));
                        drinksString = object.getString("drinks");
                    }
                    if (object.getString("heading") != null) {
                        tonightTitleView.setText(object.getString("heading"));
                        tonightTitle = object.getString("heading");
                    }
                    if (object.getString("hotdName") != null) {
                        hotdName = object.getString("hotdName");
                    }

                    if (userType.equals("guest")) {

                        if (object.getList("guest_description_array") != null) {
                            ArrayList<String> descriptionArray = (ArrayList) object.getList("guest_description_array");
                            String[] descriptionStringArray = descriptionArray.toArray(new String[descriptionArray.size()]);
                            tonightDescriptionString = descriptionStringArray[0];
                            descriptionTextView.setText(tonightDescriptionString);
                        }
                        if (object.getList("hotd_description_array") != null) {
                            ArrayList<String> hotdDescriptionArray = (ArrayList) object.getList("hotd_description_array");
                            String[] hotdDescriptionStringArray = hotdDescriptionArray.toArray(new String[hotdDescriptionArray.size()]);
                            hotdDescriptionString = hotdDescriptionStringArray[0];
                        }

                    } else {

                        if (object.getList("description_array") != null) {
                            ArrayList<String> descriptionArray = (ArrayList) object.getList("description_array");
                            String[] descriptionStringArray = descriptionArray.toArray(new String[descriptionArray.size()]);
                            tonightDescriptionString = descriptionStringArray[0];
                            descriptionTextView.setText(tonightDescriptionString);
                        }
                        if (object.getList("hotd_description_array") != null) {
                            ArrayList<String> hotdDescriptionArray = (ArrayList) object.getList("hotd_description_array");
                            String[] hotdDescriptionStringArray = hotdDescriptionArray.toArray(new String[hotdDescriptionArray.size()]);
                            hotdDescriptionString = hotdDescriptionStringArray[0];
                        }
                    }

                    if (object.getString("image_was_clicked_url") != null) {
                        imageWasClickedUrl = object.getString("image_was_clicked_url");
                    }
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        bottomBar.setAlpha(0);

        saltyBottom.setAlpha(8/10f);
        sweetBottom.setAlpha(8/10f);
        drinksBottom.setAlpha(8/10f);

        bottomBar.animate()
                .alpha(1f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        bottomBar.animate()
                                .alpha(0f)
                                .setStartDelay(1000)
                                .setDuration(300)
                                .setListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        //super.onAnimationEnd(animation);
                                        saltyBottom.setAlpha(0f);
                                        sweetBottom.setAlpha(0f);
                                        drinksBottom.setAlpha(0f);
                                        bottomBar.setAlpha(1f);
                                    }
                                });

                    }
                });
    }

    private void animateAppear(View view1) {
        if (view1.getAlpha() == 0f) {
            view1.animate()
                    .alpha(8/10f)
                    .setDuration(300)
                    .setListener(null);
        } else {
            view1.animate()
                    .alpha(0f)
                    .setDuration(300)
                    .setListener(null);
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;

        }

        protected Bitmap doInBackground(String... urls) {
            //bmImage.setVisibility(ImageView.GONE);
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
            //bmImage.setVisibility(ImageView.VISIBLE);
        }
    }
}