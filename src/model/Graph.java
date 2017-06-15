package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Stack;
import java.util.TreeMap;

import dtDAO.IntersectionDAO;
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
		Street[] crossStreets = new Street[2];
		for(Intersection i : IntersectionDAO.listIntersection()){
			if(i.getName() == name){
				crossStreets[0] = i.getStreet();
				crossStreets[1] = i.getAvenue();
				break;
			}
		}
		
		return crossStreets;
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
		 Stack<Vertex> path = new Stack<Vertex>();

		 CityMap map = CityMap.getCityMap();
		//Initialize pending vertices
		 for(Intersection c : map.getIntersections()){
			 Vertex v = new Vertex(c);

			 if(v.getIntersection().equals(source.getIntersection())){
				 v.setShortestDist(0);
				 v.setPrevious(v);
			 }
			 else{
				 v.setShortestDist(Integer.MAX_VALUE - 100);
				 v.setPrevious(null);
			 }

			 pending.add(v);
		 }
		 
		 Vertex v;

		 while(!pending.isEmpty()){
			 v = getShortestFoundPath(pending);
			 processed.add(v);
			 pending.remove(v);
			 ArrayList<Street> possibleStreets = new ArrayList<Street>();
			 possibleStreets.addAll(StreetDAO.affectedStreets(v.getIntersection()));
			 for(Street e : possibleStreets){
				 Intersection notV = v.getIntersection().equals(e.getSource()) ? e.getDestination() : e.getSource();
				 for(Vertex v2 : pending){
					 //boolean isPossible = possibleEdge(e, v, v2);
					 if(!e.getDestination().equals(v.getIntersection()) && v2.getIntersection() == notV){
						 
						 relaxStreet(new Edge(e, v, v2));						 
						 
//						 System.out.println("--------------------------------------------------------------------------------");
//						 System.out.println("after relax on v:" + v.getIntersection().getName() + " and v2:" + v2.getIntersection().getName());
//						 System.out.println("ShortestDist v/v2:" + v.getShortestDist() + "/" + v2.getShortestDist());
//						 System.out.println(" ");
					 }
				 }
			 }
		 }
		 
		 pending.clear();
		 pending.addAll(processed);
		 processed.clear();
		 Vertex nextV;
		 for(Vertex vertex : pending){
			 if(vertex != null && vertex.getIntersection().equals(destination.getIntersection())){
				 nextV = vertex;
				 path.push(nextV);
				 do {
					 nextV = nextV.getPrevious();
					 path.push(nextV);
				 }while(nextV.getPrevious() != nextV);
				 
				 break;
			 }
			 else continue;
		 }
		 
		 while(!path.isEmpty() && path.peek() != null){
			 processed.add(path.pop());
		 }
		 
		 return processed;
	}
	
	public static Vertex getShortestFoundPath(ArrayList<Vertex> pending){
		Vertex shortest = null;
		
		for(Vertex v: pending){
			if(shortest == null || v.getShortestDist() < shortest.getShortestDist())
				shortest = v;
		}
		
		return shortest;
	}

	public static boolean possibleEdge(Street street, Vertex v, Vertex v2){
		String streettostreet = CityMap.directionTraveled(v.getIntersection().getStreet(), v.getIntersection().getStreet());
		if(streettostreet != "None"){
			return (street.getDirection() == "East" || street.getDirection() == "East-West") ? streettostreet == "East" : streettostreet == "West"; 
		}
		
		String avenuetoavenue = CityMap.directionTraveled(v.getIntersection().getStreet(), v.getIntersection().getStreet());
		if(streettostreet != "None"){
			return (street.getDirection() == "South" || street.getDirection() == "North-South") ? avenuetoavenue == "South" : avenuetoavenue == "North"; 
		}
		
		return false;
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
		boolean isPossible = possibleEdge(s, a, b);
		
		//if(!isPossible)s.setWeight(Integer.MAX_VALUE - 100);
		
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