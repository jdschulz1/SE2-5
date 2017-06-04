/**
 * Client is a class representing a client that requests ACME's courier services.
 */
public class Client {

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

}