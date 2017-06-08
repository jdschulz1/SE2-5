package model;
import java.util.ArrayList;

/**
 * A Map is the representation of the usable map for the DeliveryTracker. ?It is composed of a two-dimentional array of Intersections and has operations for determining shortest path between two intersections.
 */
public class CityMap {

	private static final CityMap instance = new CityMap();
	
	private CityMap(){
		
	}
	
	public static CityMap getCityMap(){
		return instance;
	}
	
	/**
	 * intersections is the two-dimensional array representing all of the intersections top-to-bottom and left-to-right.
	 */
	private static ArrayList<Intersection> intersections;
	/**
	 * A Set of all of the Streets in the map.
	 */
	private static ArrayList<Street> streets;
	
	private static ArrayList<Street> avenues;



	public static ArrayList<Street> getStreets() {
		return streets;
	}

	public static void setStreets(ArrayList<Street> streets) {
		CityMap.streets = streets;
	}

	public static ArrayList<Street> getAvenues() {
		return avenues;
	}

	public static void setAvenues(ArrayList<Street> avenues) {
		CityMap.avenues = avenues;
	}

	public static ArrayList<Intersection> getIntersections() {
		return intersections;
	}

	public static void setIntersections(ArrayList<Intersection> intersections) {
		CityMap.intersections = intersections;
	}
	
	

}