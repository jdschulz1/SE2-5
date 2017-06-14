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
	public static ArrayList<Vertex> calculateShortestPath(Vertex source, Vertex destination) {
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

		 CityMap map = CityMap.getCityMap();
		//Initialize pending vertices
		 for(Intersection c : map.getIntersections()){
			 Vertex v = new Vertex(c);

			 if(v.getIntersection().equals(source.getIntersection())){
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

						 Edge edge = new Edge(e, v, v2);
						 
						 relaxStreet(edge);
//						 System.out.println("--------------------------------------------------------------------------------");
//						 System.out.println("after relax on v:" + v.getIntersection().getName() + " and v2:" + v2.getIntersection().getName());
//						 System.out.println("ShortestDist v/v2:" + v.getShortestDist() + "/" + v2.getShortestDist());
//						 System.out.println(" ");
					 }
				 }
			 }
		 }
		 
		 return processed;
	}
	
	//Potentially not needed
	public static Street findStreetByIntersections(Intersection src, Intersection dest){
		for(Street s : CityMap.getStreets()){
			if(src.getAdjSegments().contains(s) && dest.getAdjSegments().contains(s) && s.getDestination() != src){
				return s;
			}
		}

		return null;
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
	public static void relaxStreet(Edge e) {
		// TODO - implement Graph.relaxStreet
		/*
		 * if(a.d + w < b.d) {
		 * 		b.d = a.d + w;
		 * 		b.previous = a;
		 * }
		 * */
		Vertex a = e.getSource(), b = e.getDestination();
		Street s = e.getStreet();

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