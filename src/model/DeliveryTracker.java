package model;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The object representing the system and overall company information for ACME Couriers.
 */
public class DeliveryTracker {

	private static final DeliveryTracker instance = new DeliveryTracker();
	
	private DeliveryTracker(){
		
	}
	
	public static DeliveryTracker getDeliveryTracker(){
		return instance;
	}
	
	/**
	 * A list of couriers working for the company.
	 */
	private List<Courier> couriers;
	/**
	 * The base charge for a delivery.
	 */
	private BigDecimal billRateBase;
	/**
	 * The bill rate for the per-block charge on the delivery.
	 */
	private BigDecimal billRatePerBlock;
	/**
	 * The speed of the Courier making the delivery.
	 */
	private double courierSpeed;
	/**
	 * The bonus amount to be applied when Couriers make on-time deliveries.
	 */
	private BigDecimal bonusAmount;
	/**
	 * The acceptable difference in minutes between the estimated delivery time and the actual delivery time to be considered on-time.
	 */
	private int bonusTimeVariance;
	/**
	 * The time in minutes that the Courier is expected to spend at the pickup Client location.
	 */
	private int pickupOverheadTime;
	/**
	 * The time in minutes that the Courier is expected to spend at the delivery Client location
	 */
	private int deliveryOverheadTime;
	/**
	 * The number of blocks in a mile.
	 */
	private int blocksToMile;
	/**
	 * The list of Person objects that are known by the DeliveryTracker, which includes the Users and Couriers.
	 */
	private List<User> users;
	/**
	 * The Clients known to the DeliveryTracker.
	 */
	private List<Client> clients;
	/**
	 * All TrafficImpediments known to the DeliveryTracker.
	 */
	private List<TrafficImpediment> trafficImpediments;
	/**
	 * The data representing the map used for planning out the delivery routes for the Couriers.
	 */
	private CityMap map;
	/**
	 * The name of the Company.
	 */
	private String companyName;
	/**
	 * The intersection the company is located on.
	 */
	private Intersection companyLocation;

	public BigDecimal getBillRateBase() {
		return this.billRateBase;
	}

	public void setBillRateBase(BigDecimal billRateBase) {
		this.billRateBase = billRateBase;
	}

	public BigDecimal getBillRatePerBlock() {
		return this.billRatePerBlock;
	}

	public void setBillRatePerBlock(BigDecimal billRatePerBlock) {
		this.billRatePerBlock = billRatePerBlock;
	}

	public double getCourierSpeed() {
		return this.courierSpeed;
	}

	public void setCourierSpeed(double courierSpeed) {
		this.courierSpeed = courierSpeed;
	}

	public BigDecimal getBonusAmount() {
		return this.bonusAmount;
	}

	public void setBonusAmount(BigDecimal bonusAmount) {
		this.bonusAmount = bonusAmount;
	}

	public int getBonusTimeVariance() {
		return this.bonusTimeVariance;
	}

	public void setBonusTimeVariance(int bonusTimeVariance) {
		this.bonusTimeVariance = bonusTimeVariance;
	}

	public int getPickupOverheadTime() {
		return this.pickupOverheadTime;
	}

	public void setPickupOverheadTime(int pickupOverheadTime) {
		this.pickupOverheadTime = pickupOverheadTime;
	}

	public int getDeliveryOverheadTime() {
		return this.deliveryOverheadTime;
	}

	public void setDeliveryOverheadTime(int deliveryOverheadTime) {
		this.deliveryOverheadTime = deliveryOverheadTime;
	}

	public int getBlocksToMile() {
		return this.blocksToMile;
	}

	public void setBlocksToMile(int blocksToMile) {
		this.blocksToMile = blocksToMile;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<TrafficImpediment> getTrafficImpediments() {
		return this.trafficImpediments;
	}

	public void setTrafficImpediments(List<TrafficImpediment> trafficImpediments) {
		this.trafficImpediments = trafficImpediments;
	}

	public CityMap getMap() {
		return this.map;
	}

	public void setMap(CityMap map) {
		this.map = map;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Intersection getCompanyLocation() {
		return this.companyLocation;
	}

	public void setCompanyLocation(Intersection companyLocation) {
		this.companyLocation = companyLocation;
	}

	/**
	 * Generates an invoice for a client during a specified period.
	 * @param client
	 * @param billingDate
	 */
	public void generateInvoiceForClient(Client client, LocalDateTime billingDate) {
		// TODO - implement DeliveryTracker.generateInvoiceForClient
		throw new UnsupportedOperationException();
	}

	/**
	 * Generates a report of deliveries for a specified courier with a calculation of the courier bonus.
	 * @param courier
	 * @param startDate
	 * @param endDate
	 */
	public void generateCourierPerformanceReport(Courier courier, LocalDateTime startDate, LocalDateTime endDate) {
		// TODO - implement DeliveryTracker.generateCourierPerformanceReport
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns a client for a specified Id.
	 * @param i
	 */
	public Client getClientById(int i) {
		// TODO - implement DeliveryTracker.getClientById
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns a delivery ticket for a specified Id.
	 * @param id
	 */
	public DeliveryTicket getTicketByID(int id) {
		// TODO - implement DeliveryTracker.getTicketByID
		throw new UnsupportedOperationException();
	}

	/**
	 * Finds all tickets within a specified date range for a client.
	 * @param client
	 * @param startDate
	 * @param endDate
	 */
	public DeliveryTicket[] getTicketsByClient(Client client, LocalDateTime startDate, LocalDateTime endDate) {
		// TODO - implement DeliveryTracker.getTicketsByClient
		throw new UnsupportedOperationException();
	}

	/**
	 * Finds all tickets for a courier within a date range.
	 * @param courier
	 * @param startDate
	 * @param endDate
	 */
	public DeliveryTicket[] getTicketsbyCourier(Courier courier, LocalDateTime startDate, LocalDateTime endDate) {
		// TODO - implement DeliveryTracker.getTicketsbyCourier
		throw new UnsupportedOperationException();
	}

	/**
	 * Calculates the average rate tickets were delivered on time for a list of tickets.
	 * @param deliveryTickets
	 */
	public int calculateOntimeDeliveryRate(List<DeliveryTicket> deliveryTickets) {
		// TODO - implement DeliveryTracker.calculateOntimeDeliveryRate
		throw new UnsupportedOperationException();
	}

	/**
	 * Finds all deliveries for a specified client for a specific date range.
	 * @param client
	 * @param startDate
	 * @param endDate
	 */
	public void generateClientDeliveryReport(Client client, LocalDateTime startDate, LocalDateTime endDate) {
		// TODO - implement DeliveryTracker.generateClientDeliveryReport
		throw new UnsupportedOperationException();
	}

	/**
	 * Adds a User to the known Users on the DeliveryTracker. (Requires Admin privileges from User u)
	 * @param u
	 * @param name
	 * @param userName
	 * @param password
	 * @param role
	 * @param email
	 */
	public boolean addUser(User u, String name, String userName, String password, String role, String email){
		return u.getRole() == "Admin" && users.add(new User(name, userName, password, role, email));
	}
	
	/**
	 * Removed the deleted User from the known Users on the Delivery Tracker. (Requires Admin privileges from User u) 
	 * @param u
	 * @param deleted
	 * @return
	 */
	public boolean deleteUser(User u, User deleted){
		return u.getRole() == "Admin" && users.remove(deleted);
	}
	
	public List<Courier> getCouriers() {
		return couriers;
	}

	public void setCouriers(List<Courier> couriers) {
		this.couriers = couriers;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
	
	public void addClient(Client client) {
		if(this.clients == null)
			this.clients = new ArrayList<Client>();
		this.clients.add(client);
	}
	public void addCourier(Courier courier) {
		if(this.couriers == null)
			this.couriers = new ArrayList<Courier>();
		this.couriers.add(courier);
	}
	/**
	 * Adds Client added to the known Clients on the DeliveryTracker.
	 * @param clientNumber
	 * @param name
	 * @param crossStreet1
	 * @param crossStreet2
	 * @param deliveryDetails
	 * @param email
	 * @return
	 */
	public boolean addClient(int clientNumber, String name, Street crossStreet1, Street crossStreet2, String deliveryDetails, String phone, String email){
		return clients.add(new Client(clientNumber, name, crossStreet1, crossStreet2, deliveryDetails, phone, email));
	}
	
	/**
	 * Removes Client deleted from known Clients on the DeliveryTracker.
	 * @param deleted
	 * @return
	 */
	public boolean deleteClient(Client deleted){
		return clients.remove(deleted);
	}

	public boolean deleteCourier(Courier c) {
		// TODO Auto-generated method stub
		return couriers.remove(c);
	}
}