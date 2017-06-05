package model;
import java.util.*;

/**
 * Directions are the set of instructions given to the Courier in the Courier Package that tell the Courier turn-by-turn directions from ACME headquarters to pickup location to delivery location.and back to the ACME headquarters.
 */
public class Route {

	private Collection<Instruction> instructionsList;
	/**
	 * A collection of Instructions that is printed up on the Courier Package so that they know how to get to all of their destinations.
	 */
	private Instruction[] route;
	/**
	 * A by-product of generating Instructions.  This distance for the route taken by the courier may be used in the calculation of price or time to traverse for the route.
	 */
	private int routeDistance;
	/**
	 * A list of vertices representing the delivery route.
	 */
	private Vertex[] path;

	/**
	 * Creates a Graph for shortest path calculation and generates Instructions based on the shortest path vertices.  Intersection subclasses Vertex, so Vertex objects in the Graph's vertices TreeMap will also have access to the Intersection cross-streets and in-turn the name of the street and direction you are able to travel on the street.  With all this data combined, Instruction objects can be assembled.
	 * @param deliveryRoute The pickup location as an Intersection.
	 */
	public Instruction[] generateRouteFromPath(Vertex[] deliveryRoute) {
		// TODO - implement Route.generateRouteFromPath
		throw new UnsupportedOperationException();
	}

	/**
	 * Calculates the total distance of a route.
	 * @param deliveryRoute
	 */
	public int calculateRouteDistance(Vertex[] deliveryRoute) {
		// TODO - implement Route.calculateRouteDistance
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns a list of instructions generated from a route(list of intersections) that can be easily followed by a courier.
	 * @param source
	 * @param destination
	 */
	public Vertex[] generatePath(Intersection source, Intersection destination) {
		// TODO - implement Route.generatePath
		throw new UnsupportedOperationException();
	}

}