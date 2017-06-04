import java.util.TreeMap;

/**
 * A Graph for the purpose of generating the shortest path for the Courier Package.
 */
public class Graph {

	/**
	 * The vertices contained in a Graph, in this case represented by Intersections of two streets.
	 */
	private TreeMap<String, Vertex> vertices;

	/**
	 * Get the Streets from an Intersection with a name matching the name passed into this operation.
	 * @param name
	 */
	public Street[] getStreetsFrom(String name) {
		// TODO - implement Graph.getStreetsFrom
		throw new UnsupportedOperationException();
	}

	/**
	 * The calculated shortest path according to Dijkstra's Algorithm.
	 * @param source
	 * @param destination
	 */
	public Vertex[] calculateShortestPath(Vertex source, Vertex destination) {
		// TODO - implement Graph.calculateShortestPath
		throw new UnsupportedOperationException();
	}

	/**
	 * The equivalent of relax edge in Dijkstra's shortest path algorithm. ?This algorithm helper for the main shortest path algorithm takes in a street and sets the Intersection that comes before the destination Intersection to
	 * @param street
	 */
	public void relaxStreet(Street street) {
		// TODO - implement Graph.relaxStreet
		throw new UnsupportedOperationException();
	}

	/**
	 * Calculates the total distance in blocks between two intersections.
	 * @param source
	 * @param destination
	 */
	public int calculateDistanceInBlocks(Intersection source, Intersection destination) {
		// TODO - implement Graph.calculateDistanceInBlocks
		throw new UnsupportedOperationException();
	}

}