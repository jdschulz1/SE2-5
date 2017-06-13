package model;
import java.util.ArrayList;
import java.util.TreeMap;

import dtDAO.StreetDAO;

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
	public static Vertex[] calculateShortestPath(Vertex source, Vertex destination) {
		// TODO - implement Graph.calculateShortestPath
		/*
		 * while(!pending.isEmpty()){
		 * 		v = pending.getShortestFoundPath() //The vertex with the smallest "d" value
		 * 		foreach(edge e from v to another vertex){
		 * 			if(e goes between v and another vertex in pending){
		 * 				relax e
		 * 			}
		 * 		}
		 * }
		 * */
		 ArrayList<Vertex> pending = new ArrayList<Vertex>();
		 ArrayList<Vertex> processed = new ArrayList<Vertex>();
		 //Initialize pending vertices
		 for(Intersection c : CityMap.getIntersections()){
			 Vertex v = new Vertex(c);
			 if(v.equals(source)){
				 v.setShortestDist(0);
				 v.setPrevious(v);
			 }
			 else{
				 v.setShortestDist(Integer.MAX_VALUE);
				 v.setPrevious(null);
			 }
			 
			 pending.add(v);
		 }
		 
		 Vertex v;

		 while(!pending.isEmpty()){
			 v = getShortestFoundPath(pending);
			 processed.add(v);
			 pending.remove(v);
			 for(Street e : StreetDAO.affectedStreets(v.getIntersection())){
				 Intersection notV = v.getIntersection().equals(e.getSource()) ? e.getDestination() : e.getSource();
				 for(Vertex v2 : pending){
					 if(v2.getIntersection() == notV){
						 relaxStreet(v,v2);
					 }
				 }
			 }
		 }
		 
		 return (Vertex[]) processed.toArray();
	}
	
	public static Vertex getShortestFoundPath(ArrayList<Vertex> pending){
		Vertex shortest = null;
		
		for(Vertex v: pending){
			if(shortest == null || v.getShortestDist() < shortest.getShortestDist())
				shortest = v;
		}
		
		return shortest;
	}

	/**
	 * The equivalent of relax edge in Dijkstra's shortest path algorithm. ?This algorithm helper for the main shortest path algorithm takes in a street and sets the Intersection that comes before the destination Intersection to
	 * @param street
	 */
	public static void relaxStreet(Vertex a, Vertex b) {
		// TODO - implement Graph.relaxStreet
		/*
		 * if(a.d + w < b.d) {
		 * 		b.d = a.d + w;
		 * 		b.previous = a;
		 * }
		 * */
		Street s = StreetDAO.findStreetByIntersections(a.getIntersection(), b.getIntersection());
		if(a.getShortestDist() + s.getWeight() < b.getShortestDist()){
			b.setShortestDist(a.getShortestDist() + s.getWeight());
			b.setPrevious(a);
		}
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