package model;

/**
 * The Intersection is described by two cross-streets and an availability that determines if a Courier can get to that Intersection.
 */
public class Intersection {

	/**
	 * The Street is available if true and unavailable if false.
	 */
	private boolean availability;
	/**
	 * One of two cross-streets that composes an Intersection. ?crossStreet1 travels, North, South or North and South.
	 */
	private Street crossStreet1;
	/**
	 * The second of two cross-streets that compose an Intersection. ?crossStreet2 travels, East, West or East and West.
	 */
	private Street crossStreet2;
	/**
	 * The name of the intersection, with the name of the two cross-streets on either side of an ampersand (i.e. "A Street & 1st Ave").
	 */
	private String name;
	public boolean isAvailable() {
		return availability;
	}
	public void setAvailable(boolean availability) {
		this.availability = availability;
	}
	public Street getCrossStreet1() {
		return crossStreet1;
	}
	public void setCrossStreet1(Street crossStreet1) {
		this.crossStreet1 = crossStreet1;
	}
	public Street getCrossStreet2() {
		return crossStreet2;
	}
	public void setCrossStreet2(Street crossStreet2) {
		this.crossStreet2 = crossStreet2;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}