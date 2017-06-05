package model;
/**
 * Courier is an employee that delivers packages for ACME.
 */
public class Courier {

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

}