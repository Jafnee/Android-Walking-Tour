package uk.ac.aber.cs22120.fuzzyNinja.pathFinder;

import java.lang.Thread;
import android.net.ConnectivityManager;
/**
* This class is responsible for sending the HTTP Post.
* It will also manage monitoring of network availability,
* which controls the ability to upload information.
* It will run its own thread, and send when it is possible.
*/
public class HTTPPostSender implements Runnable{
	Thread networkThread;
	boolean isAvailable;
	ConnectivityManager cm;
	/** Sends an HTTP post request to the web server
	* @param request the string obtained from HTTPPostBuilder
	*/
	public void send(String request) {}
}