package cs221.group15.pathfinder;

import static junit.framework.Assert.*;
import android.test.AndroidTestCase;

public class TestWaypoint extends AndroidTestCase{
	private Waypoint testWaypoint;
	
	@test
	public void testGetCoordinatesWithValidValue(){
		testWaypoint = new Waypoint();
		testWaypoint.setCoordinates(21.75, 35.62);
		assertNull(testWaypoint.getCoordinates().getlat());
		assertNull(testWaypoint.getCoordinates().getlng());
	}
	
	@test
	public void testGetCoordinatesWithNullValue(){
		testWaypoint = new Waypoint();
		testWaypoint.setCoordinates(null, null);
		testWaypoint.getCoordinates();
		assertEquals(21.75, testWaypoint.getCoordinates().getlat());
		assertEquals(35.62, testWaypoint.getCoordinates().getlng());
	}
	
	@test
	public void testGetNameWithValidValue(){
		testWaypoint = new Waypoint();
		testWaypoint.setName("A place");
		assertEquals("A place", testWaypoint.getName());
	}
	
	@test
	public void testGetNameWithNullValue(){
		testWaypoint = new Waypoint();
		testWaypoint.setName(null);
		assertNull(testWaypoint.getName());
	}
	
	@test
	public void testGetDescriptionWithValidValue(){
		testWaypoint = new Waypoint();
		testWaypoint.setDescription("The quick brown fox jumped over the lazy dog.");
		assertEquals("The quick brown fox jumped over the lazy dog.", testWaypoint.getName());
	}
	
	@test
	public void testGetDescriptionWithNullValue(){
		testWaypoint = new Waypoint();
		testWaypoint.setDescription(null);
		assertNull(testWaypoint.getName());
	}
	
	@test
	public void testGetTimestampWithValidValue(){
		testWaypoint = new Waypoint();
		testWaypoint.setTimestamp(5.00);
		assertEquals(5.00, testWaypoint.getTimestamp());
	}
	
	@test
	public void testGetTimeStampWithNullValue(){
		testWaypoint = new Waypoint();
		testWaypoint.setTimestamp(5.00);
		assertNull(testWaypoint.getTimestamp());
	}

}