/*
 *@(#)HTTPPostSender.java 0.5 2014-01-31
 * 
 * Copyright (c)2014 Aberystwyth University.
 * All rights reserved.
 */
package cs221.group15.pathfinder;

/**
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

/**
 * The class is responsible for building a string to use in a HTTP post
 * request. It is only responsible for building and storing the request
 * string.
 * 
 * @author	gad16
 * @author 	lpd1
 * @since	0.3
 * @version	1.0 2014-01-31 9ed077e148cf94320295b5f4553fb380cb6c0c43
 *
 */
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
     * 
     */
    public HTTPPostSender() {
        try {
            uri = new URI("http://groupproject.lewisdale.co.uk/get.php");
        } catch (URISyntaxException e) {
            // Abandon all hope...
        }


        isRunning = true;

    }

    
    /**
     * The override allows the method to perform it's computations in the background.
     * This is done asynchronously to prevent the the application from freezing while
     * the request is happening.
     * Creates a new HttpClient and a Post Header.
     * The Apache HttpClient is used to handle HTTP requests.
     */
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
     * Returns true if the POST has managed to go through the server successfully, false if something
     * has gone wrong in between.
     * @param s String returned from the trySend() method i.e. PHP response from the server.
     * @return boolean value. True if request was successful, false otherwise.
     */
    public boolean doneSuccessfully(String s) {
        return s.equals("True");
    }

    /**
     * Builds a string containing the HTTP POST request.
     * Creates a JSON object and is filled up with key value pairs to be sent through
     * the POST.
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
        postMessage = parent.toString();
    }

    /**
     * Setting this to be false will stop the network thread from running.
     */
    public void close() {
        isRunning = false;
    }

}