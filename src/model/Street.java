package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * A Street object describes streets that can be cross-streets in an Intersection.  It has a direction that describes if it travels "North", "South", "East", "West", "East-West", or "North-South" and a name that identifies it.  As a result of its usage as an edge in shortest path calculations, a destination Intersection and weight are also stored.
 */
@Entity(name = "street")
public class Street implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Represents the identification number of the street.
	 */
	@Id
	@Column(name = "street_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long streetId;
	
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
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Intersection getSource() {
		return source;
	}
	public void setSource(Intersection source) {
		this.source = source;
	}
	public Intersection getDestination() {
		return destination;
	}
	public void setDestination(Intersection destination) {
		this.destination = destination;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}

}