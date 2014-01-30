package cs221.group15.pathfinder;

/**
 * Created by gad16 on 29/01/2014.
 */

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Thread;
import java.net.URI;
import java.net.URISyntaxException;

import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

public class HTTPPostSender implements Runnable {
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
    private boolean isRunning;
    /**
     * The message built in this class to be sent as a POST request.
     */
    private String postMessage;

    ConnectivityManager cm;
    NetworkInfo activeNetwork;
    HttpPost request;
    HttpClient client;
    HttpResponse response;
    URI uri;
    BufferedReader in;

    /**
     * Class constructor, runs thread.
     */
    public HTTPPostSender() {
        try {
            uri = new URI("http://groupproject.lewisdale.co.uk/index.php?m=upld");
        } catch (URISyntaxException e) {
            // Abandon all hope...
        }
        isRunning = true;
        networkThread = new Thread(this);
        networkThread.start();
    }

    /**
     * Implements Runnable's run() method. Will start running when this class is instantiated
     * and will continue to run indefinitely until the method close() is called.
     */
    @Override
    public void run() {
        while(isRunning) {
            try {
            activeNetwork = cm.getActiveNetworkInfo(); // gets network info for the network.
            isAvailable = activeNetwork.isConnectedOrConnecting(); // checks if device is connected/connecting to the network
            } catch (Exception e) {
                // Move along. Nothing to see here.
            }
        }
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
    public boolean trySend(){
        StringBuffer sb = new StringBuffer();
        try {
            if (isAvailable) {

                client = new DefaultHttpClient();
                request = new HttpPost();
                request.setEntity(new StringEntity(postMessage));
                request.setURI(uri);
                response = client.execute(request); // Gets a response from the server side

                in = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));

                sb.append(in.readLine());
            }
        } catch (Exception e) {

        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return doneSuccessfully(sb.toString()); // Determines whether or not the server received and processed the request
    }

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
    public void buildString(Route postRoute) {
        // Builds the POST message as a JSON array of JSON objects, each with the parameters of a waypoint
        JSONArray route = new JSONArray();


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



        postMessage = route.toString();
    }
    public void close() {
        isRunning = false;
    }
}