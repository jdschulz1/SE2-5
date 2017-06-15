package model;
import java.util.ArrayList;
import java.util.List;

import dtDAO.IntersectionDAO;
import dtDAO.StreetDAO;

/**
 * A Map is the representation of the usable map for the DeliveryTracker. ?It is composed of a two-dimentional array of Intersections and has operations for determining shortest path between two intersections.
 */
public class CityMap {

	private static final CityMap instance = new CityMap();
	
	private CityMap(){

	}
	
	public static CityMap getCityMap(){
		
		List<Street> allStreets = StreetDAO.listStreet();
		List<Intersection> allIntersections = IntersectionDAO.listIntersection();
		intersections = new ArrayList<Intersection>();
		streets = new ArrayList<Street>();
		avenues = new ArrayList<Street>();
		wholeStreets = new ArrayList<Street>();
		wholeAvenues = new ArrayList<Street>();
		
		streetNames = new ArrayList<String>(){
		{
			add("A Street");
			add("B Street");
			add("C Street");
			add("D Street");
			add("E Street");
			add("F Street"); 
			add("G Street"); 
		}};
		
		avenueNames = new ArrayList<String>(){
		{
			add("1st Ave");
			add("2nd Ave");
			add("3rd Ave");
			add("4th Ave");
			add("5th Ave");
			add("6th Ave");
			add("7th Ave"); 
		}};	
		
		if(allStreets.isEmpty() && allIntersections.isEmpty()){
			initializeMapDBData();
		}
		else{
			for(Street s : allStreets){
				
				if(streetNames.contains(s.getName())){
					if(s.getSource() == null || s.getDestination() == null){
						wholeStreets.add(s);
					}
					else {
//						System.out.println("Source: " + s.getSource().getName() + " adds adj street segment " + s.getName() + "(" + s.getDirection() + ")");
//						System.out.println("Destination: " + s.getDestination().getName() + " adds adj street segment " + s.getName());
//						System.out.println("");
						s.getSource().getAdjSegments().add(s);
						s.getDestination().getAdjSegments().add(s);
						streets.add(s);
					}
				}
				else {
					if(s.getSource() == null || s.getDestination() == null){
						wholeAvenues.add(s);
					}
					else {
						s.getSource().getAdjSegments().add(s);
						s.getDestination().getAdjSegments().add(s);
						avenues.add(s);
					}
				}
			}
			for(Intersection i : allIntersections){
				intersections.add(i);
			}
		}

		return instance;
	}
	
	/**
	 * intersections is the two-dimensional array representing all of the intersections top-to-bottom and left-to-right.
	 */
	private static ArrayList<Intersection> intersections = new ArrayList<Intersection>();
	/**
	 * A Set of all of the Streets in the map.
	 */
	private static ArrayList<Street> streets = new ArrayList<Street>(), wholeStreets;
	private static ArrayList<String> streetNames = new ArrayList<String>(){
	{
		add("A Street");
		add("B Street");
		add("C Street");
		add("D Street");
		add("E Street");
		add("F Street"); 
		add("G Street"); 
	}};
	
	private static ArrayList<Street> avenues = new ArrayList<Street>(), wholeAvenues;
	private static ArrayList<String> avenueNames = new ArrayList<String>(){
	{
		add("1st Ave");
		add("2nd Ave");
		add("3rd Ave");
		add("4th Ave");
		add("5th Ave");
		add("6th Ave");
		add("7th Ave"); 
	}};
	
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
	
	
	private static void initializeMapDBData(){
		ArrayList<Street> tempstreets = new ArrayList<Street>(), tempavenues = new ArrayList<Street>();
		Street A = new Street(), B = new Street(), C = new Street(), D = new Street(), 
				E = new Street(), F = new Street(), G = new Street();
		A.setName("A Street"); A.setDirection("South");A.setWeight(1);
		B.setName("B Street"); B.setDirection("North");B.setWeight(1);
		C.setName("C Street"); C.setDirection("South");C.setWeight(1);
		D.setName("D Street"); D.setDirection("North-South");D.setWeight(1);
		E.setName("E Street"); E.setDirection("South");E.setWeight(1);
		F.setName("F Street"); F.setDirection("North");F.setWeight(1);
		G.setName("G Street"); G.setDirection("South");G.setWeight(1);
		
		Street first = new Street(), second = new Street(), third = new Street(), fourth = new Street(), 
				fifth = new Street(), sixth = new Street(), seventh = new Street();
		first.setName("1st Ave"); first.setDirection("West");first.setWeight(1);
		second.setName("2nd Ave"); second.setDirection("East-West");second.setWeight(1);
		third.setName("3rd Ave"); third.setDirection("West");third.setWeight(1);
		fourth.setName("4th Ave"); fourth.setDirection("East");fourth.setWeight(1);
		fifth.setName("5th Ave"); fifth.setDirection("West");fifth.setWeight(1);
		sixth.setName("6th Ave"); sixth.setDirection("East-West");sixth.setWeight(1);
		seventh.setName("7th Ave"); seventh.setDirection("West");seventh.setWeight(1);
		
		tempstreets.add(A);
		tempstreets.add(B);
		tempstreets.add(C);
		tempstreets.add(D);
		tempstreets.add(E);
		tempstreets.add(F);
		tempstreets.add(G);
		
		tempavenues.add(first);
		tempavenues.add(second);
		tempavenues.add(third);
		tempavenues.add(fourth);
		tempavenues.add(fifth);
		tempavenues.add(sixth);
		tempavenues.add(seventh);
		System.out.println("Adding Intersections");
		intersections.add(new Intersection(A,first));
		intersections.add(new Intersection(B,first));
		intersections.add(new Intersection(C,first));
		intersections.add(new Intersection(D,first));
		intersections.add(new Intersection(E,first));
		intersections.add(new Intersection(F,first));
		intersections.add(new Intersection(G,first));
		intersections.add(new Intersection(A,second));
		intersections.add(new Intersection(B,second));
		intersections.add(new Intersection(C,second));
		intersections.add(new Intersection(D,second));
		intersections.add(new Intersection(E,second));
		intersections.add(new Intersection(F,second));
		intersections.add(new Intersection(G,second));
		intersections.add(new Intersection(A,third));
		intersections.add(new Intersection(B,third));
		intersections.add(new Intersection(C,third));
		intersections.add(new Intersection(D,third));
		intersections.add(new Intersection(E,third));
		intersections.add(new Intersection(F,third));
		intersections.add(new Intersection(G,third));
		intersections.add(new Intersection(A,fourth));
		intersections.add(new Intersection(B,fourth));
		intersections.add(new Intersection(C,fourth));
		intersections.add(new Intersection(D,fourth));
		intersections.add(new Intersection(E,fourth));
		intersections.add(new Intersection(F,fourth));
		intersections.add(new Intersection(G,fourth));
		intersections.add(new Intersection(A,fifth));
		intersections.add(new Intersection(B,fifth));
		intersections.add(new Intersection(C,fifth));
		intersections.add(new Intersection(D,fifth));
		intersections.add(new Intersection(E,fifth));
		intersections.add(new Intersection(F,fifth));
		intersections.add(new Intersection(G,fifth));
		intersections.add(new Intersection(A,sixth));
		intersections.add(new Intersection(B,sixth));
		intersections.add(new Intersection(C,sixth));
		intersections.add(new Intersection(D,sixth));
		intersections.add(new Intersection(E,sixth));
		intersections.add(new Intersection(F,sixth));
		intersections.add(new Intersection(G,sixth));
		intersections.add(new Intersection(A,seventh));
		intersections.add(new Intersection(B,seventh));
		intersections.add(new Intersection(C,seventh));
		intersections.add(new Intersection(D,seventh));
		intersections.add(new Intersection(E,seventh));
		intersections.add(new Intersection(F,seventh));
		intersections.add(new Intersection(G,seventh));
//		System.out.println(intersections.size() + " Intersections added (" + intersections.get(0).getName() + " through " + intersections.get(48).getName() + ")");
//		System.out.println("Adding Streets");
		for(int i = 0; i < tempavenues.size(); i++){
			for(int j = 0; j < tempstreets.size(); j++){
				int currIntersection = i*tempavenues.size()+j;
				if(j+1 < tempstreets.size()){
					if(tempavenues.get(i).getDirection() == "East" || tempavenues.get(i).getDirection() == "East-West"){
						//All street segments moving Eastward or both directions on tempavenues.get(i)
						Intersection src = intersections.get(currIntersection), dest = intersections.get(currIntersection+1);
						Street s = new Street(tempavenues.get(i).getName(),tempavenues.get(i).getDirection(),src,dest,1);
						s.getSource().getAdjSegments().add(s);
						s.getDestination().getAdjSegments().add(s);
						avenues.add(s);
					}
					
					if(tempavenues.get(i).getDirection() == "West" || tempavenues.get(i).getDirection() == "East-West"){
						Intersection src = intersections.get(currIntersection+1), dest = intersections.get(currIntersection);
						Street s = new Street(tempavenues.get(i).getName(),tempavenues.get(i).getDirection(),src,dest,1);
						s.getSource().getAdjSegments().add(s);
						s.getDestination().getAdjSegments().add(s);
						avenues.add(s);
					}
				}
				if(i+1 < tempavenues.size()){
					if(tempstreets.get(j).getDirection() == "South" || tempstreets.get(j).getDirection() == "North-South"){
						//All street segments moving Eastward or both directions on tempavenues.get(i)
						Intersection src = intersections.get(currIntersection), dest = intersections.get(currIntersection+7);
						Street s = new Street(tempstreets.get(j).getName(),tempstreets.get(j).getDirection(),src,dest,1);
						s.getSource().getAdjSegments().add(s);
						s.getDestination().getAdjSegments().add(s);
						streets.add(s);
					}
					
					if(tempstreets.get(j).getDirection() == "North" || tempstreets.get(j).getDirection() == "North-South"){
						Intersection src = intersections.get(currIntersection+7), dest = intersections.get(currIntersection);
						Street s = new Street(tempstreets.get(j).getName(),tempstreets.get(j).getDirection(),src,dest,1);
						s.getSource().getAdjSegments().add(s);
						s.getDestination().getAdjSegments().add(s);
						streets.add(s);
					}
				}
			}
		}
//		System.out.println(streets.size() + " Streets added (e.g. " + streets.get(0).getName() + ")");
//		System.out.println(avenues.size() + " Avenues added (e.g. " + avenues.get(0).getName() + ")");
		
//		System.out.println("Adding " + intersections.size() + " Intersections to Team5DT Database");
		for(Intersection i: intersections){
			IntersectionDAO.addIntersection(i);
		}
//		System.out.println(IntersectionDAO.listIntersection().size() + " Intersections added to Team5DT Database");
//		System.out.println("Adding " + streets.size() + " Streets to Team5DT Database");
		for(Street s: streets){
			StreetDAO.addStreet(s);
		}
//		System.out.println(StreetDAO.listStreet().size() + " Streets added to Team5DT Database");
//		System.out.println("Adding " + avenues.size() + " Avenues to Team5DT Database");
		for(Street s: avenues){
			StreetDAO.addStreet(s);
		}
//		System.out.println(StreetDAO.listStreet().size() + " Streets added to Team5DT Database");
	}

	public static ArrayList<Street> getWholeStreets() {
		return wholeStreets;
	}

	public static void setWholeStreets(ArrayList<Street> wholeStreets) {
		CityMap.wholeStreets = wholeStreets;
	}

	public static ArrayList<Street> getWholeAvenues() {
		return wholeAvenues;
	}

	public static void setWholeAvenues(ArrayList<Street> wholeAvenues) {
		CityMap.wholeAvenues = wholeAvenues;
	}
}