package com.example.dashan.codefundo;

/**
 * Created by dashan on 21/3/18.
 */

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class GetNearByPlaces extends AsyncTask<Object,String,String>{

    private String googlePlacesData;
    private GoogleMap mMap;
    private PlaceInfo placeInfo;
    private Context mContext;
    String url;
    @Override
    protected String doInBackground(Object... objects) {
        mMap=(GoogleMap)objects[0];
        url=(String)objects[1];

        DownloadUrl downloadUrl=new DownloadUrl();

        try {
            googlePlacesData=downloadUrl.readUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return googlePlacesData;
    }

    @Override
    protected void onPostExecute(String s) {
        List<HashMap<String, String>> nearbyPlaceList;
        DataParser parser = new DataParser();
        nearbyPlaceList = parser.parse(s);
        Log.d("nearbyplacesdata","called parse method");
        showNearbyPlaces(nearbyPlaceList);
    }
    private void showNearbyPlaces(List<HashMap<String, String>> nearbyPlaceList)
    {
        for(int i = 0; i < nearbyPlaceList.size(); i++)
        {
            MarkerOptions markerOptions = new MarkerOptions();

            HashMap<String, String> googlePlace = nearbyPlaceList.get(i);

            String placeName = googlePlace.get("place_name");
            String vicinity = googlePlace.get("vicinity");
            double lat = Double.parseDouble( googlePlace.get("lat"));
            double lng = Double.parseDouble( googlePlace.get("lng"));

            String s="";
            String s1="";
            String s2="";
            try {
                s+="Address: " + nearbyPlaceList.get(i).get("place_name") + "\n";
                s1+="rating: "+nearbyPlaceList.get(i).get("rating")+"\n";
                s2+="Address : "+nearbyPlaceList.get(i).get("vicinity")+"\n";
                Log.v("VALUE OF S",s);
                Log.d("MSG",s);
            }catch (Exception e){
                e.printStackTrace();
            }
            Log.v("VALUE OF S111",s);
            Log.d("MSG11",s);
            Log.v("VALUE OF S111",s2);
            Log.d("MSG11",s2);


/*
            try {

                s += "Address: " + placeInfo.getAddress() + "\n" +
                        "Phone Number: " + placeInfo.getPhoneNumber() + "\n" +
                        "Website: " + placeInfo.getWebsiteUri() + "\n" +
                        "Price Rating: " + placeInfo.getRating() + "\n";
            }catch (Exception e){
                e.printStackTrace();
            }*/


            LatLng latLng = new LatLng( lat, lng);
            markerOptions.position(latLng);
            markerOptions.title(placeName + " : "+ vicinity);
            markerOptions.snippet(s);
            markerOptions.snippet(s1);
            markerOptions.snippet(s2);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

            mMap.addMarker(markerOptions);
            //Marker marker=mMap.addMarker(markerOptions);
            //Marker.showInfoWindow();
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.moveCamera(CameraUpdateFactory.zoomTo(10));
        }
    }


}
