package cs221.group15.pathfinder;

/**
 * Created by gad16 on 29/01/2014.
 */

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.Thread;
import java.net.URI;
import java.net.URISyntaxException;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class HTTPPostSender extends AsyncTask<Route,Void,Void> {
    /**
     * Thread which constantly checks the network connection status.
     */
    Thread networkThread;

    /**
     * Value set by networkThread. True if available, false if not.
     */
    private boolean isAvailable;

    /**
     * Determines whether the networkThread should be running.
     */
    private boolean isRunning = true;
    /**
     * The message built in this class to be sent as a POST request.
     */
    private String postMessage;

    public ConnectivityManager cm;
    NetworkInfo activeNetwork;
    HttpPost request;
    HttpClient client;
    HttpResponse response;
    URI uri;
    BufferedReader in = null;

    /**
     * Class constructor, runs thread.
     */
    public HTTPPostSender() {
        try {
            uri = new URI("http://groupproject.lewisdale.co.uk/get.php");
        } catch (URISyntaxException e) {
            // Abandon all hope...
        }


        isRunning = true;

    }

    @Override
    protected Void doInBackground(Route... routes) {
        StringBuffer sb = new StringBuffer();
        try {
            client = new DefaultHttpClient();
            request = new HttpPost();
            request.setEntity(new StringEntity(postMessage));
            request.setHeader("Content-type", "application/json");
            request.setHeader("Accept", "application/json");
            request.setURI(uri);

            response = client.execute(request); // Gets a response from the server side
            HttpEntity httpEntity = response.getEntity();
            String line = EntityUtils.toString(httpEntity);


            Log.e("SERVERRESPONSE",line);
            in = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            sb.append(in.readLine());

        } catch (Exception e) {
            if(e.getMessage() != null) {
                Log.e("SERVERRESPONSE",e.getMessage());
            }
        } finally {
            try {
                if(in != null) {
                    in.close();
                }
            } catch (IOException e) {
                Log.e("SERVERROR",e.getMessage());
            }
        }

        return null;
    }



    /**
     * Returns true if a connection is available, false if not
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * Attempts to send the HTTP POST request to the server.
     * @return boolean value stating whether or not the POST request was received by the server.
     */


    /**
     * Should be easy to understand.
     * @param s String returned from the trySend() method i.e. PHP response from the server.
     * @return boolean value. True if request was successful, false otherwise.
     */
    public boolean doneSuccessfully(String s) {
        return s.equals("True");
    }

    /**
     * Builds a string containing the HTTP POST request.
     * @param postRoute the walking tour used to build the request string.
     */
    public void buildString(Route postRoute, ContentResolver cr) {
        // Builds the POST message as a JSON array of JSON objects, each with the parameters of a waypoint

        JSONObject parent = new JSONObject();

        JSONArray route = new JSONArray();

        for(int i = 0; i < postRoute.getTotalWaypoints(); i++) {
            JSONObject waypoint = new JSONObject();
            try {
                Waypoint location = postRoute.getWaypoint(i);
                waypoint.put("description", location.getDescription());
                waypoint.put("timestamp", String.valueOf(location.getTimestamp()));
                waypoint.put("lat",String.valueOf(location.getLat()));
                waypoint.put("long",String.valueOf(location.getLng()));
                route.put(waypoint);

            } catch (Exception e) {
                Log.e("Error:", e.getMessage());
            }
        }
        try {
             parent.put("Route",route);
             parent.put("Title",postRoute.getTitle());
             parent.put("Short_Description",postRoute.getShortDescription());
             parent.put("Long_Description",postRoute.getLongDescription());
        } catch (Exception e) {
            Log.e("Error:", e.getMessage());
        }


     /*   for (int i = 0; i < postRoute.getTotalWaypoints();i++) {
            JSONObject waypoint = new JSONObject();
            try {
                waypoint.put("name", postRoute.getWaypoint(i).getName());
                waypoint.put("latitude", postRoute.getWaypoint(i).getLatitude());
                waypoint.put("longitude", postRoute.getWaypoint(i).getLongitude());
                waypoint.put("description", postRoute.getWaypoint(i).getDescription());

                // Encoding a bitmap into a string for POST request:
                // http://stackoverflow.com/questions/4830711/how-to-convert-a-image-into-base64-string

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                postRoute.getWaypoint(i).getPhoto().compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] photo = stream.toByteArray(); // Convert bitmap to byte array
                String encodedPhoto = Base64.encodeToString(photo, Base64.DEFAULT); // Converts byte array to string
                waypoint.put("photo", encodedPhoto);

                waypoint.put("timestamp", postRoute.getWaypoint(i).getTimestamp());
                route.put(waypoint);
            } catch (Exception e) {

            } */



        postMessage = parent.toString();
    }

    public void close() {
        isRunning = false;
    }

}