/**package uk.ac.aber.imaps.cs22120.fuzzyNinja.pathfinder.model;

public class Model {

	private RouteLocationManager rtLocMan;
	private HTTPPostSender httpPostMan;
	
	
	
	
	public Model()
	{
		
		rtLocMan = new RouteLocationManager();
		Thread rlm = new Thread(rtLocMan);
		rlm.start();
		
		httpPostMan = new HTTPPostSender();
		Thread hpm = new Thread(httpPostMan);
		hpm.start();
		
	}
	
}
**/