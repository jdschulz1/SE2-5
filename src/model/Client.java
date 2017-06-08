package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
		this.location = new Intersection(street, avenue);
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
	private int clientNumber;
	/**
	 * Name of the Client.
	 */
	private String name;
	/**
	 * The intersection marking the location of the Client.
	 */
	private Intersection location;
	/**
	 * Additional details about the deliver (i.e. Office Number)
	 */
	private String deliveryDetails;
	/**
	 * Email address for the Client.
	 */
	private String email;
	/**
	 * Phone number for the Client.
	 */
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