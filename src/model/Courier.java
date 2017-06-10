package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Courier is an employee that delivers packages for ACME.
 */
@Entity(name = "courier")
public class Courier implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Courier(){
		
	}
	
	public Courier(int courierNumber, String name, String phoneNumber,boolean isActive){
		this.courierNumber = courierNumber;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.isActive = isActive;
	}
	
	@Id
	@Column(name = "courier_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long courierId;
	
	/**
	 * A number identifying the courier.
	 */
	@Column(name = "courier_number")
	private int courierNumber;
	
	/**
	 * Phone number for the Courier.
	 */
	@Column(name = "phone_number")
	private String phoneNumber;
	
	/**
	 * Specifies whether the courier is currently available for the system to use.
	 */
	@Column(name = "is_active")
	private boolean isActive;
	
	/**
	 * The name of the courier.
	 */
	@Column(name = "name")
	private String name;
	
	@Column(name = "is_available")
	private boolean isAvailable;

	public boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public void checkInOut() {
		// TODO - implement Courier.checkInOut
		throw new UnsupportedOperationException();
	}

	public int getCourierNumber() {
		return courierNumber;
	}

	public void setCourierNumber(int courierNumber) {
		this.courierNumber = courierNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

}