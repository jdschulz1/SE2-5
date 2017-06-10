package model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * The Intersection is described by two cross-streets and an availability that determines if a Courier can get to that Intersection.
 */
@Entity(name = "intersection")
public class Intersection implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Intersection(){
		
	}
	
	public Intersection(Street street, Street avenue){
		this.availability = true;
		this.street = street;
		this.avenue = avenue;
		this.name = street.getName() + "&" + avenue.getName();
	}
	
	/**
	 * Represents the identification number of the intersection.
	 */
	@Id
	@Column(name = "intersection_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long intersectionId;
	
	/**
	 * The Street is available if true and unavailable if false.
	 */
	@Column(name = "availability")
	private boolean availability;
	
	/**
	 * One of two cross-streets that composes an Intersection. ?crossStreet1 travels, North, South or North and South.
	 */
	@JoinColumn(name = "street_id")
	@OneToOne(cascade = CascadeType.PERSIST)
	private Street street;
	
	/**
	 * The second of two cross-streets that compose an Intersection. ?crossStreet2 travels, East, West or East and West.
	 */
	@JoinColumn(name = "avenue_id")
	@OneToOne(cascade = CascadeType.PERSIST)
	private Street avenue;
	
	/**
	 * The name of the intersection, with the name of the two cross-streets on either side of an ampersand (i.e. "A Street & 1st Ave").
	 */
	@Column(name = "name")
	private String name;
	
	public boolean isAvailable() {
		return availability;
	}
	public void setAvailable(boolean availability) {
		this.availability = availability;
	}
	public Street getStreet() {
		return street;
	}
	public void setStreet(Street street) {
		this.street = street;
	}
	public Street getAvenue() {
		return avenue;
	}
	public void setAvenue(Street avenue) {
		this.avenue = avenue;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}