package model;
public class Vertex {

	/**
	 * Used in the shortest path algorithm, shortestDist represents the shortest distance to the current Intersection from a source Intersection. shortestDist is initialized to 0 for the source Intersection and Integer.MAX_VALUE for every other Intersection and shortestDist continues to get lower as you go through Dijkstra's Algorithm.
	 */
	private int shortestDist;
	/**
	 * previous - Used in the shortest path algorithm, previous represents the last intersection in a path representing shortest distance to the current Intersection from a source Intersection. If this Intersection is the source Intersection, previous is set to the source itself.
	 */
	private Vertex previous;
	/**
	 * Represents the intersection information associated with the vertex.
	 */
	private Intersection intersection;

}