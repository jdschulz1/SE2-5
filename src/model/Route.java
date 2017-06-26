package model;
import java.math.BigDecimal;
import java.util.*;

import dtDAO.StreetDAO;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Directions are the set of instructions given to the Courier in the Courier Package that tell the Courier turn-by-turn directions from ACME headquarters to pickup location to delivery location.and back to the ACME headquarters.
 */
public class Route {
	
	public Route(){
		instructionsList = new ArrayList<Instruction>();
	}
	
	public Route(Intersection src, Intersection dest){
		instructionsList = new ArrayList<Instruction>();
		if(!src.equals(dest)){
			this.generatePath(src, dest);
			this.generateRouteFromPath();
		}
	}
	
	/**
	 * A collection of Instructions that is printed up on the Courier Package so that they know how to get to all of their destinations.
	 */
	private Collection<Instruction> instructionsList;
	
	public Collection<Instruction> getInstructionsList() {
		return instructionsList;
	}

	/**
	 * A by-product of generating Instructions.  This distance for the route taken by the courier may be used in the calculation of price or time to traverse for the route.
	 */
	private int routeDistance;
	/**
	 * A list of vertices representing the delivery route.
	 */
	private ArrayList<Vertex> path;

	public ArrayList<Vertex> getPath() {
		return path;
	}

	/**
	 * Creates a Graph for shortest path calculation and generates Instructions based on the shortest path vertices.  Intersection subclasses Vertex, so Vertex objects in the Graph's vertices TreeMap will also have access to the Intersection cross-streets and in-turn the name of the street and direction you are able to travel on the street.  With all this data combined, Instruction objects can be assembled.
	 * @param deliveryRoute The pickup location as an Intersection.
	 */
	public void generateRouteFromPath() {
		Street currentStreet = null, nextStreet = null;
		Vertex src, dest, last = null;
		String currentDirection = "";
		
		for(int i = 0; i+1 < path.size(); i++){
			
			src = path.get(i);
			dest = path.get(i+1);
			
			
			if(nextStreet != null)
				currentStreet = nextStreet;
				
			nextStreet = StreetDAO.findStreetByIntersections(src.getIntersection(), dest.getIntersection());
			
			if(last == null && nextStreet != null && currentStreet != null && currentDirection != nextStreet.getDirection()){
				String cd, nd;
				cd = currentStreet.getSource().getStreet().equals(currentStreet.getDestination().getStreet()) ?
						CityMap.directionTraveled(currentStreet.getSource().getAvenue(), currentStreet.getDestination().getAvenue()):
						CityMap.directionTraveled(currentStreet.getSource().getStreet(), currentStreet.getDestination().getStreet());
				nd = nextStreet.getSource().getStreet().equals(nextStreet.getDestination().getStreet()) ?
						CityMap.directionTraveled(nextStreet.getSource().getAvenue(), nextStreet.getDestination().getAvenue()):
						CityMap.directionTraveled(nextStreet.getSource().getStreet(), nextStreet.getDestination().getStreet());
				if(cd != nd){	
					instructionsList.add(new Instruction(src.getShortestDist(), currentStreet, cd, nextStreet, nd));
					last = src;
				}
			}
			else if(nextStreet != null && currentStreet != null && currentDirection != nextStreet.getDirection()){
				String cd, nd;
				cd = currentStreet.getSource().getStreet().equals(currentStreet.getDestination().getStreet()) ?
						CityMap.directionTraveled(currentStreet.getSource().getAvenue(), currentStreet.getDestination().getAvenue()):
						CityMap.directionTraveled(currentStreet.getSource().getStreet(), currentStreet.getDestination().getStreet());
				nd = nextStreet.getSource().getStreet().equals(nextStreet.getDestination().getStreet()) ?
						CityMap.directionTraveled(nextStreet.getSource().getAvenue(), nextStreet.getDestination().getAvenue()):
						CityMap.directionTraveled(nextStreet.getSource().getStreet(), nextStreet.getDestination().getStreet());
				if(cd != nd){
					instructionsList.add(new Instruction(src.getShortestDist() - last.getShortestDist(), currentStreet, cd, nextStreet, nd));
					last = src;
				}
			}
			if(i+1 == path.size()-1) {
				String cd;
				currentStreet = nextStreet;
				cd = currentStreet.getSource().getStreet().equals(currentStreet.getDestination().getStreet()) ?
						CityMap.directionTraveled(currentStreet.getSource().getAvenue(), currentStreet.getDestination().getAvenue()):
						CityMap.directionTraveled(currentStreet.getSource().getStreet(), currentStreet.getDestination().getStreet());
				
				int lastDist = last != null ? last.getShortestDist() : 0;
				instructionsList.add(new Instruction(path.get(path.size()-1).getShortestDist() - lastDist, currentStreet, cd, null, null));
			}

			
			
			currentDirection = nextStreet.getDirection();
		}
	}

	/**
	 * Calculates the total distance of a route.
	 * @param deliveryRoute
	 */
	private void calculateRouteDistance() {
		if(this.path != null && this.path.size()> 0)this.routeDistance = this.path.get(this.path.size()-1).getShortestDist();
	}

	/**
	 * Returns a list of instructions generated from a route(list of intersections) that can be easily followed by a courier.
	 * @param source
	 * @param destination
	 */
	public void generatePath(Intersection source, Intersection destination) {
		this.path =  Graph.calculateShortestPath(new Vertex(source), new Vertex(destination));
		this.calculateRouteDistance();
	}

	public int getRouteDistance() {
		return routeDistance;
	}
}