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

import dtDAO.IntersectionDAO;
import model.Intersection;
import model.Street;

/**
 * Client is a class representing a client that requests ACME's courier services.
 */
@Entity(name = "client")
public class Client implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Client(){
			
	}
	
	public Client(int clientNumber, String name, Street street, Street avenue, String deliveryDetails, String phone, String email){
		this.clientNumber = clientNumber; 
		this.name = name;
		this.location = IntersectionDAO.findIntersectionByStreets(street, avenue);
		this.deliveryDetails = deliveryDetails;
		this.phoneNumber = phone;
		this.email = email;
	}
	
	@Id
	@Column(name = "client_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long clientId;
	
	/**
	 * An identifying number for Clients.
	 */
	@Column(name = "client_number")
	private int clientNumber;
	
	/**
	 * Name of the Client.
	 */
	@Column(name = "name")
	private String name;
	
	/**
	 * The intersection marking the location of the Client.
	 */
	@JoinColumn(name = "intersection_id")
	@OneToOne(cascade = CascadeType.PERSIST)
	private Intersection location;
	
	/**
	 * Additional details about the deliver (i.e. Office Number)
	 */
	@Column(name = "delivery_details")
	private String deliveryDetails;
	
	/**
	 * Email address for the Client.
	 */
	@Column(name = "email")
	private String email;
	
	/**
	 * Phone number for the Client.
	 */
	@Column(name = "phone_number")
	private String phoneNumber;
	
	public int getClientNumber() {
		return clientNumber;
	}
	public void setClientNumber(int clientNumber) {
		this.clientNumber = clientNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Intersection getLocation() {
		return location;
	}
	public void setLocation(Intersection location) {
		this.location = location;
	}
	public String getDeliveryDetails() {
		return deliveryDetails;
	}
	public void setDeliveryDetails(String deliveryDetails) {
		this.deliveryDetails = deliveryDetails;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}