package model;
/**
 * Client is a class representing a client that requests ACME's courier services.
 */
public class Client {

	public Client(String name, Street crossStreet1, Street crossStreet2, String email){
		this.clientNumber = 12345; //DUMMY data.  We will generate this number to be unique
		this.name = name;
		this.location = new Intersection(crossStreet1, crossStreet2);
		this.deliveryDetails = "Fill me in later";
		this.email = email;
	}
	
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