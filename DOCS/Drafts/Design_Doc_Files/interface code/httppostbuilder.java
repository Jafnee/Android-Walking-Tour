package uk.ac.aber.cs22120.fuzzyNinja.pathFinder;

import java.util.StringBuilder;

/** This class is responsible for building a string to use in an HTTP post request
* It is only responsible for building and storing the request string.
*/
public abstract class HTTPPostBuilder {
	private StringBuilder postString; // The string used in an HTTP post request
	
	/** Builds an HTTP post request based on the attributes of a Walk objects
	* @param postWalk the Walk object used to build the HTTP post request
	*/
	public void buildString(Walk postWalk) {}
}