package model;
import java.util.ArrayList;

/**
 * A Map is the representation of the usable map for the DeliveryTracker. ?It is composed of a two-dimentional array of Intersections and has operations for determining shortest path between two intersections.
 */
public class CityMap {

	/**
	 * intersections is the two-dimensional array representing all of the intersections top-to-bottom and left-to-right.
	 */
	private Intersection[] intersections;
	/**
	 * A Set of all of the Streets in the map.
	 */
	private ArrayList<Street> streets;

}