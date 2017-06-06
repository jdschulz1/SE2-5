package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Courier is an employee that delivers packages for ACME.
 */
@Entity(name = "courier")
public class Courier implements Serializable{

	public Courier(int courierNumber, String name, String phoneNumber,boolean isActive){
		this.courierNumber = courierNumber;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.isActive = isActive;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "courier_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long courierId;
	
	/**
	 * A number identifying the courier.
	 */
	private int courierNumber;
	/**
	 * Phone number for the Courier.
	 */
	private String phoneNumber;
	/**
	 * Specifies whether the courier is currently available for the system to use.
	 */
	private boolean isActive;
	/**
	 * The name of the courier.
	 */
	private String name;
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