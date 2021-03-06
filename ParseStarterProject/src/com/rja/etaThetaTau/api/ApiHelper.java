package com.rja.etaThetaTau.api;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by rjaylward on 9/22/15
 **/
public class ApiHelper {

    AppCompatActivity mActivity;
//    public static final String PARSE_API_URL = "https://api.parse.com/1/classes";
//    public static final String YOUTUBE_KEY = "AIzaSyC8l_H5c5SxRYFwGsiV85kFJ9mgeprPkxA";
//    private String YOUTUBE_PLAYLIST_ITEMS_URL = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&playlistId=%s&key=" + YOUTUBE_KEY;
//    private String GOOGLE_MAPS_DIRECTIONS_URL = "https://maps.googleapis.com/maps/api/directions/json?origin=%f,%f&destination=%f,%f&key=" + YOUTUBE_KEY;
//    public static String TUMBLR_API_KEY = "NMhe3dJOgq9L4BKdOPNWA1cSTfMPW5TUgppBTkF3mmCQZ5BkYG";
//
//    public ApiHelper(AppCompatActivity activity) {
//        mActivity = activity;
//    }
//
//    public void getTalks(VolleyRequestListener<TalksResponse> uiListener) {
//        String url = PARSE_API_URL + "/sermons";
//        Log.d("get all sermons", url);
//
//        GsonVolleyRequester<TalksResponse> volleyRequester = new GsonVolleyRequester<>(mActivity, TalksResponse.class);
//        volleyRequester.makeGetRequest(mActivity, url, uiListener);
//    }
//
//    public void getTalksJson(VolleyRequestListener uiListener) {
//        String url = PARSE_API_URL + "/sermons";
//        Log.d("get all sermons", url);
//
//        JsonObjectVolleyRequester volleyRequester = new JsonObjectVolleyRequester(mActivity);
//        volleyRequester.makeGetRequest(mActivity, url, uiListener);
//    }
//
//    public void getUmberLocation(VolleyRequestListener uiListener) {
//        String url = PARSE_API_URL + "/uber";
//        Log.d("get umber location", url);
//
//        JsonObjectVolleyRequester volleyRequester = new JsonObjectVolleyRequester(mActivity);
//        volleyRequester.makeGetRequest(mActivity, url, uiListener);
//    }
//
//    public void getFrontpageTalks(int limit,VolleyRequestListener<TalksResponse> uiListener) {
//        String url = PARSE_API_URL + "/sermons" + "?where={\"source\":\"direct\"}&limit=" + limit;
//        Log.d("get all sermons", url);
//
//        GsonVolleyRequester<TalksResponse> volleyRequester = new GsonVolleyRequester<>(mActivity, TalksResponse.class);
//        volleyRequester.makeGetRequest(mActivity, url, uiListener);
//    }
//
//    public void getLocations(VolleyRequestListener<UmLocationsResponse> uiListener) {
//        String url = PARSE_API_URL + "/pick_up_spots";
//        Log.d("get all pick up spot", url);
//
//        GsonVolleyRequester<UmLocationsResponse> volleyRequester = new GsonVolleyRequester<>(mActivity, UmLocationsResponse.class);
//        volleyRequester.makeGetRequest(mActivity, url, uiListener);
//    }
//
//    public void getEvents(VolleyRequestListener<EventsResponse> uiListener) {
//        String url = PARSE_API_URL + "/event";
//        Log.d("get all events", url);
//
//        GsonVolleyRequester<TalksResponse> volleyRequester = new GsonVolleyRequester<>(mActivity, TalksResponse.class);
//        volleyRequester.makeGetRequest(mActivity, url, uiListener);
//    }
//
//    public void getNotifications(VolleyRequestListener uiListener) {
//        String url = PARSE_API_URL + "/notification";
//        Log.d("get all notifications", url);
//
//        GsonVolleyRequester<NotificationsResponse> volleyRequester = new GsonVolleyRequester<>(mActivity, NotificationsResponse.class);
//        volleyRequester.makeGetRequest(mActivity, url, uiListener);
//    }
//
//    public void getUmberRequest(String objectId, VolleyRequestListener<UmberRequest> uiListener) {
//        String url = PARSE_API_URL + "/uber_requests/" + objectId;
//        Log.d("getting umber request", url);
//
//        GsonVolleyRequester<UmberRequest> volleyRequester = new GsonVolleyRequester<>(mActivity, UmberRequest.class);
//        volleyRequester.makeGetRequest(mActivity, url, uiListener);
//    }
//
//    public void getUmberRequests(VolleyRequestListener<UmberRequestResponse> uiListener) {
//        String url = PARSE_API_URL + "/uber_requests";
//        Log.d("make umber request", url);
//
//        GsonVolleyRequester<UmberRequestResponse> volleyRequester = new GsonVolleyRequester<>(mActivity, UmberRequestResponse.class);
//        volleyRequester.makeGetRequest(mActivity, url, uiListener);
//    }
//
//    public void getMyActiveUmberRequests(VolleyRequestListener<MyUmberRequestResponse> uiListener) {
//        String email = Preferences.getInstance().getEmail();
//        String where = "?where={\"$or\":[{\"email\":\"" + email + "\", \"canceled\":false, \"isComplete\":false}," +
//                "{\"driverEmail\":\"" + email + "\", \"canceled\":false, \"isComplete\":false}]}";
//
//        String url = PARSE_API_URL + "/uber_requests" + where;
//        Log.d("getting umber request", url);
//
//        GsonVolleyRequester<MyUmberRequestResponse> volleyRequester = new GsonVolleyRequester<>(mActivity, MyUmberRequestResponse.class);
//        volleyRequester.makeLargeGetRequest(mActivity, url, uiListener);
//    }
//
//    public void makeUmberRequest(UmberRequest umberRequest, VolleyRequestListener<JsonObject> uiListener){
//        String url = PARSE_API_URL + "/uber_requests";
//        Log.d("make umber request", url);
////        Log.d("Request params", umberRequest.getAsJson(false).toString());
//
//        StringObjectVolleyRequester volleyRequester = new StringObjectVolleyRequester(mActivity);
//        volleyRequester.makePostRequest(mActivity, url, umberRequest.getAsJson(false), uiListener);
//    }
//
//    public void claimUmberRequest(UmberRequest request, VolleyRequestListener uiListener) {
//        String url = PARSE_API_URL + "/uber_requests/" + request.getObjectId();
//        Log.d("update umber request", url);
//
//        JsonObject requestParams = new JsonObject();
//
//        requestParams.addProperty(FieldNames.DRIVER_EMAIL, request.getDriverEmail());
//        requestParams.addProperty(FieldNames.CLAIMED, request.isClaimed());
//        requestParams.addProperty(FieldNames.ETA, request.getEta().getTime() / 1000);
//
//        StringObjectVolleyRequester volleyRequester = new StringObjectVolleyRequester(mActivity);
//        volleyRequester.makePutRequest(mActivity, url, requestParams, uiListener);
//    }
//
//    public void updateUmberRequestStatus(UmberRequest request, VolleyRequestListener uiListener) {
//        String url = PARSE_API_URL + "/uber_requests/" + request.getObjectId();
//        Log.d("update request status", url);
//
//        JsonObject requestParams = new JsonObject();
//
//        requestParams.addProperty(FieldNames.DRIVER_EMAIL, request.getDriverEmail());
//
//        requestParams.addProperty(FieldNames.CLAIMED, request.isClaimed());
//        requestParams.addProperty(FieldNames.STARTED, request.isStarted());
//        requestParams.addProperty(FieldNames.IS_PICKED_UP, request.isPickedUp());
//        requestParams.addProperty(FieldNames.IS_COMPLETE, request.isComplete());
//
//        requestParams.addProperty(FieldNames.CANCELED, request.isCanceled());
//
//        StringObjectVolleyRequester volleyRequester = new StringObjectVolleyRequester(mActivity);
//        volleyRequester.makePutRequest(mActivity, url, requestParams, uiListener);
//    }
//
//    public void createDriver(Driver driver, VolleyRequestListener uiListener){
//        String url = PARSE_API_URL + "/drivers";
//        Log.d("create driver request", url);
//
//        StringObjectVolleyRequester volleyRequester = new StringObjectVolleyRequester(mActivity);
//        volleyRequester.makePostRequest(mActivity, url, driver.getAsJson(false), uiListener);
//    }
//
//    public void updateDriver(Driver driver, VolleyRequestListener uiListener){
//        String url = PARSE_API_URL + "/drivers/" + driver.getObjectId();
//        if(driver.getObjectId().equals(""))
//            throw new InvalidParameterException("Driver must have an object ID");
//        Log.d("update driver request", url);
//
//        StringObjectVolleyRequester volleyRequester = new StringObjectVolleyRequester(mActivity);
//        volleyRequester.makePutRequest(mActivity, url, driver.getAsJson(false), uiListener);
//    }
//
//    public void getDriver(String email, VolleyRequestListener<DriverResponse> uiListener) {
//        String url = PARSE_API_URL + "/drivers?where={\"email\":\"" + email + "\"}";
//        Log.d("get driver", url);
//
//        GsonVolleyRequester<DriverResponse> volleyRequester = new GsonVolleyRequester<>(mActivity, DriverResponse.class);
//        volleyRequester.makeGetRequest(mActivity, url, uiListener);
//    }
//
//    public void getGoogleMapsData(UmberRequest request, VolleyRequestListener<MapsResponse> uiListener) {
//        String url = String.format(GOOGLE_MAPS_DIRECTIONS_URL, request.getPickupLat(), request.getPickupLong(),
//                request.getDestinationLat(), request.getDestinationLong());
//        Log.d("making maps request", url);
//
//        GsonVolleyRequester<MapsResponse> volleyRequester = new GsonVolleyRequester<>(mActivity, MapsResponse.class);
//        volleyRequester.makeGetRequest(mActivity, url, uiListener);
//    }
//
//    public void deleteRequest(String objectId, VolleyRequestListener uiListener) {
//        String url = PARSE_API_URL + "/uber_requests/" + objectId;
//        Log.d("delete umber request", url);
//
//        StringObjectVolleyRequester volleyRequester = new StringObjectVolleyRequester(mActivity);
//        volleyRequester.makeDeleteRequest(mActivity, url, uiListener);
//    }
//
//    /////////////////////////////////// Tumblr Api Requests ////////////////////////////////////////
//
//    public void getTumblrTalks(VolleyRequestListener<BaseTumblrResponse> uiListener) {
////        String url = "http://api.tumblr.com/v2/blog/ummalibu.com/posts/audio?api_key=" + TUMBLR_API_KEY;
//        String url = "http://api.tumblr.com/v2/blog/ummalibu.com/posts?api_key=" + TUMBLR_API_KEY + "&tag=talks";
//        Log.d("get tumblr talks", url);
//
//        GsonVolleyRequester<BaseTumblrResponse> volleyRequester = new GsonVolleyRequester<>(mActivity, BaseTumblrResponse.class);
//        volleyRequester.makeGetRequest(mActivity, url, uiListener);
//    }
//
//    /////////////////////////////////// Youtube Api Requests ///////////////////////////////////////
//
//    public void getYoutubeVideos(String channelId, VolleyRequestListener<YoutubeItemsResponse> uiListener) {
//        String url = String.format(YOUTUBE_PLAYLIST_ITEMS_URL, channelId);
//        Log.d("get youtube vids", url);
//
//        GsonVolleyRequester<YoutubeItemsResponse> volleyRequester = new GsonVolleyRequester<>(mActivity, YoutubeItemsResponse.class);
//        volleyRequester.makeGetRequest(mActivity, url, uiListener);
//    }

}