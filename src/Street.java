/**
 * A Street object describes streets that can be cross-streets in an Intersection.  It has a direction that describes if it travels "North", "South", "East", "West", "East-West", or "North-South" and a name that identifies it.  As a result of its usage as an edge in shortest path calculations, a destination Intersection and weight are also stored.
 */
public class Street {

	/**
	 * The direction of the Road object, which may be "North", "South", "East", "West", "East-West", or "North-South".
	 */
	private String direction;
	/**
	 * The name identifying the Street object.
	 */
	private String name;
	/**
	 * The source Intersection for defining the start of this Street opposite the destination Intersection's end point. ?This will be necessary when applying the relaxStreet operation while running Dijkstra's Algorithm.
	 */
	private Intersection source;
	/**
	 * The destination of the Street as an Edge in a directed Graph, determined by the direction of the road.
	 */
	private Intersection destination;
	/**
	 * The weight of the Street, which will be 1 block, unless the source or destination Intersections are unavailable, in which case the weight will be Integer.MAX_VALUE.
	 */
	private int weight;

}