package com.rja.etaThetaTau;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.parse.ParseObject;

import java.util.ArrayList;


public class SongsFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    private LyricsAdapter lyricsListAdapter;
    private HotLyricsAdapter hotLyricsAdapter;
    // private ChoreoAdapter choreoListAdapter;
    private ListView listView;
    private Boolean loadVideos;
    String userType;

    public static SongsFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        SongsFragment fragment = new SongsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //setHasOptionsMenu(true);

        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
        //getActivity().invalidateOptionsMenu();
        loadVideos = true;
        loadSavedPreferences();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_songs, container, false);

/*        Switch mySwitch = (Switch) view.findViewById(R.id.songs_switch);
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (loadLyrics) {
                    loadLyrics = false;
                } else {
                    loadLyrics = true;
                }
            }
        });*/

        final Button videosButton = (Button) view.findViewById(R.id.songs_videos_button);
        final Button lyricsButton = (Button) view.findViewById(R.id.songs_showlyrics_button);
        lyricsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    loadVideos = false;
                    lyricsButton.setBackgroundResource(R.drawable.all_white_rounded_button_background);
                    videosButton.setBackgroundResource(R.drawable.white_and_blue_button_background);
                    videosButton.setTextColor(Color.parseColor("#ffffff"));
                    lyricsButton.setTextColor(Color.parseColor("#083551"));

            }
        });
        videosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadVideos = true;
                videosButton.setBackgroundResource(R.drawable.all_white_rounded_button_background);
                lyricsButton.setBackgroundResource(R.drawable.white_and_blue_button_background);
                lyricsButton.setTextColor(Color.parseColor("#ffffff"));
                videosButton.setTextColor(Color.parseColor("#083551"));

            }
        });
        lyricsListAdapter = new LyricsAdapter(getActivity());
        hotLyricsAdapter = new HotLyricsAdapter(getActivity());

        // Initialize ListView and set initial view to mainAdapter
        listView = (ListView) view.findViewById(R.id.list);

        if (userType.equals("guest")) {
            listView.setAdapter(lyricsListAdapter);
            lyricsListAdapter.loadObjects();}
        else {
            listView.setAdapter(hotLyricsAdapter);
            hotLyricsAdapter.loadObjects();}

        // 5. Set this activity to react to list items being pressed
        listView.setOnItemClickListener(this);

        final Button reloadButton =(Button) view.findViewById(R.id.songs_reload_button);
        reloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userType.equals("guest")) {
                    listView.setAdapter(lyricsListAdapter);
                    lyricsListAdapter.loadObjects();}
                else {
                    listView.setAdapter(hotLyricsAdapter);
                    hotLyricsAdapter.loadObjects();}

                reloadButton.animate().rotationBy(360).setDuration(500);
            }
        });

        return view;
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//
//        loadSavedPreferences();
//        if (userType.equals("guest")) {
//            listView.setAdapter(lyricsListAdapter);
//            lyricsListAdapter.loadObjects();}
//        else {
//            listView.setAdapter(hotLyricsAdapter);
//            hotLyricsAdapter.loadObjects();}
//    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        //menu.clear();
        inflater.inflate(R.menu.script_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.switch_for_actionbar) {
        //    return true;
        //}

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }

    private void loadSavedPreferences() {
        SharedPreferences settings = getActivity().getSharedPreferences("UserInfo", 0);
        userType = settings.getString("user", "");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        final ParseObject queryObject;

        if (userType.equals("guest")) {
            queryObject = lyricsListAdapter.getItem(position);}
        else {
            queryObject = hotLyricsAdapter.getItem(position);}

        ArrayList<String> videoArray = new ArrayList<String>();
        videoArray =(ArrayList) queryObject.getList("videoArray");
        String[] videoStringArray = videoArray.toArray(new String[videoArray.size()]);

        ArrayList<String> nameArray = new ArrayList<String>();
        nameArray =(ArrayList) queryObject.getList("nameArray");
        String[] nameStringArray = nameArray.toArray(new String[nameArray.size()]);

        String title = queryObject.getString("title");
        String objectId = queryObject.getObjectId();
        String lyrics = queryObject.getString("lyrics");
        String audioUrl = queryObject.getString("audioUrl");

        if (loadVideos) {

            // create an Intent to take you over to a new DetailActivity
            Intent detailIntent = new Intent(getActivity(), SongsDetailActivity.class);

            detailIntent.putExtra("title", title);
            //detailIntent.putExtra("objectId", objectId);
            detailIntent.putExtra("videoArray", videoStringArray);
            detailIntent.putExtra("nameArray", nameStringArray);

            startActivity(detailIntent);
        } else {

            Intent detailIntent = new Intent(getActivity(), LyricsDetailActivity.class);

            detailIntent.putExtra("title", title);
            detailIntent.putExtra("lyrics", lyrics);
            detailIntent.putExtra("audioUrl", audioUrl);

            startActivity(detailIntent);
        }

    }
}
