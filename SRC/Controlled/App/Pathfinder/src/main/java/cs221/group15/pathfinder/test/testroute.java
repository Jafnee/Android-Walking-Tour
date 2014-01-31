package cs221.group15.pathfinder;

import static junit.framework.Assert.*;
import android.test.AndroidTestCase;

public class TestRoute extends AndroidTestCase{
	private Route testRoute;
	@test
	public void testGetTitleWithValidValue(){
		testRoute = new Route();
		testRoute.setTitle("A walk in the park");
		assertEquals("A walk in the park", testRoute.getTitle());
	}
	
	@test
	public void testGetTitleWithNullValue(){
		testRoute = new Route();
		testRoute.setTitle(null);
		assertNull(testRoute.getTitle());
	}
	
	@test
	public void testgetShortDescriptionWithValidValue(){
		testRoute = new Route();
		testRoute.setShort("Shortenough");
		assertEquals("Shortenough", testRoute.getShortDescription(););
	}
	
	@test
	public void testgetShortDescriptionWithNullValue(){
		testRoute = new Route();
		testRoute.setShort("Shortenough");
		assertNull(testRoute.getShortDescription(););
	}
	
	
	@test
	public void testgetLongDescriptionWithValidValue(){
		testRoute = new Route();
		testRoute.setLong("Try to make this sentence as long as humanly possible without looking completely and utterly ridiculous.");
		assertEquals("Try to make this sentence as long as humanly possible without looking completely and utterly ridiculous.", testRoute.getLongDescription());
	}
	
	
	@test
	public void testgetLongDescriptionWithNullValue(){
		testRoute = new Route();
		testRoute.setLong(null);
		assertNull(testRoute.getLongDescription());
	}
}