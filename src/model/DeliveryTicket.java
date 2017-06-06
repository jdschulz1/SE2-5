package model;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import model.DeliveryTracker;

/**
 * Delivery tickets are records containing all information related to a delivery request, including pickup and delivery details.
 */
@Entity(name = "delivery_ticket")
public class DeliveryTicket implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Represents the identification number of the ticket.
	 */
	@Id
	@Column(name = "ticket_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ticketId;
	
	/**
	 * The Courier involved in the delivery represented by this DeliveryTicket.
	 */
	private Courier courier;

	/**
	 * Date and time that the order call is received by the Order Taker.
	 */
	private LocalDateTime orderDateTime;
	/**
	 * Order takers are the people that take phone calls and record the details of the Delivery Ticket.
	 */
	private User orderTaker;
	/**
	 * The Client from whom the Courier picks up the package.
	 */
	private Client pickupClient;
	/**
	 * The Client to whom the Courier will deliver the package.
	 */
	private Client deliveryClient;
	/**
	 * The Client (pickup or delivery)  who is paying for the delivery.
	 */
	private Client payingClient;
	/**
	 * The time that the Client who placed the order requests that the Courier arrives to pickup the package.
	 */
	private LocalDateTime requestedPickupTime;
	/**
	 * The unique number created upon Delivery Ticket creation, which will be used to verify the correct package is picked up.
	 */
	private int packageID;
	/**
	 * The estimated time the package is expected to be delivered at.
	 */
	private LocalDateTime estimatedDeliveryTime;
	/**
	 * The time that the system calculates the Courier will need to leave the Office in order to reach the pickup location by the requested pickup time.
	 */
	private LocalDateTime calculatedDepartureTime;
	/**
	 * The actual time that the Courier departs on the delivery circuit.
	 */
	private LocalDateTime actualDepartureTime;
	/**
	 * The actual time that the Courier picks the package up from the pickup Client.
	 */
	private LocalDateTime actualPickupTime;
	/**
	 * The actual time that the package is delivered by the Courier to the delivery Client.
	 */
	private LocalDateTime actualDeliveryTime;
	/**
	 * The actual time that the Courier returns from the delivery.
	 */
	private LocalDateTime actualReturnTime;
	/**
	 * The amount of the monetary bonus, awarded to the Courier for a successful on-time delivery of the package.
	 */
	private BigDecimal bonusAmount;
	/**
	 * Any special remarks about the delivery.
	 */
	private String specialRemarks;
	/**
	 * A boolean to record that the delivery has been confirmed with the delivery Client.
	 */
	private boolean deliveryConfirmed;
	/**
	 * The invoice number associated with the delivery ticket.
	 */
	private int invoiceNumber;
	private BigDecimal price;
	/**
	 * The route from pickup location to delivery location.
	 */
	private Route deliveryRoute;
	/**
	 * The route from the company location to the pickup location.
	 */
	private Route pickupRoute;
	/**
	 * The route from the delivery to the company location.
	 */
	private Route returnRoute;

	/**
	 * Sets the courier bonus for the delivery.
	 */
	
	private static DeliveryTracker deliveryTracker;
	
	public void calculateBonus() {
		this.setBonusAmount(new BigDecimal(0));
		if(isOnTime())
			this.setBonusAmount(deliveryTracker.getBonusAmount());
	}

	/**
	 * Determines whether the delivery was made within the time window to be considered "on time".
	 */
	public boolean isOnTime() {
		Duration between = Duration.between(this.estimatedDeliveryTime, this.actualDeliveryTime);
		return (between.getSeconds() > (deliveryTracker.getBonusTimeVariance() * 60));
	}

	/**
	 * Generate the Courier Package from the details stored in the delivery ticket.  Populates the courierPackage attribute.
	 */
	public void generateCourierPackage() {
		// TODO - implement DeliveryTicket.generateCourierPackage
		throw new UnsupportedOperationException();
	}

	/**
	 * Calculates the estimated delivery time based on distance and overhead times for pick up and drop off.
	 * @param pickupOverheadTIme
	 * @param deliveryOverheadTime
	 * @param distance
	 */
	public void calculateDeliveryTime(LocalDateTime pickupOverheadTIme, LocalDateTime deliveryOverheadTime, int distance) {
		// TODO - implement DeliveryTicket.calculateDeliveryTime
		throw new UnsupportedOperationException();
	}

	/**
	 * Calculates the time the courier is required to depart from the company office in order to pick up the package at the requested pick up time. This is calculated based on distance from the company office to pick up location and the requested pick up location.
	 */
	public LocalDateTime calculateDepartureTime() {
		// TODO - implement DeliveryTicket.calculateDepartureTime
		throw new UnsupportedOperationException();
	}

	/**
	 * Calculates a price quote for the delivery using the billRateBase, billRatePerBlock, and routeDistance.
	 */
	public BigDecimal calculatePrice() {
		// TODO - implement DeliveryTicket.calculatePrice
		throw new UnsupportedOperationException();
	}

	public Courier getCourier() {
		return courier;
	}

	public void setCourier(Courier courier) {
		this.courier = courier;
	}

	public long getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public LocalDateTime getOrderDateTime() {
		return orderDateTime;
	}

	public void setOrderDateTime(LocalDateTime orderDateTime) {
		this.orderDateTime = orderDateTime;
	}

	public User getOrderTaker() {
		return orderTaker;
	}

	public void setOrderTaker(User orderTaker) {
		this.orderTaker = orderTaker;
	}

	public Client getPickupClient() {
		return pickupClient;
	}

	public void setPickupClient(Client pickupClient) {
		this.pickupClient = pickupClient;
	}

	public Client getDeliveryClient() {
		return deliveryClient;
	}

	public void setDeliveryClient(Client deliveryClient) {
		this.deliveryClient = deliveryClient;
	}

	public LocalDateTime getRequestedPickupTime() {
		return requestedPickupTime;
	}

	public void setRequestedPickupTime(LocalDateTime requestedPickupTime) {
		this.requestedPickupTime = requestedPickupTime;
	}

	public Client getPayingClient() {
		return payingClient;
	}

	public void setPayingClient(Client payingClient) {
		this.payingClient = payingClient;
	}

	public int getPackageID() {
		return packageID;
	}

	public void setPackageID(int packageID) {
		this.packageID = packageID;
	}

	public LocalDateTime getCalculatedDepartureTime() {
		return calculatedDepartureTime;
	}

	public void setCalculatedDepartureTime(LocalDateTime calculatedDepartureTime) {
		this.calculatedDepartureTime = calculatedDepartureTime;
	}

	public LocalDateTime getActualDepartureTime() {
		return actualDepartureTime;
	}

	public void setActualDepartureTime(LocalDateTime actualDepartureTime) {
		this.actualDepartureTime = actualDepartureTime;
	}

	public LocalDateTime getActualPickupTime() {
		return actualPickupTime;
	}

	public void setActualPickupTime(LocalDateTime actualPickupTime) {
		this.actualPickupTime = actualPickupTime;
	}

	public LocalDateTime getActualReturnTime() {
		return actualReturnTime;
	}

	public void setActualReturnTime(LocalDateTime actualReturnTime) {
		this.actualReturnTime = actualReturnTime;
	}

	public BigDecimal getBonusAmount() {
		return bonusAmount;
	}

	public void setBonusAmount(BigDecimal bonusAmount) {
		this.bonusAmount = bonusAmount;
	}

	public String getSpecialRemarks() {
		return specialRemarks;
	}

	public void setSpecialRemarks(String specialRemarks) {
		this.specialRemarks = specialRemarks;
	}

	public boolean isDeliveryConfirmed() {
		return deliveryConfirmed;
	}

	public void setDeliveryConfirmed(boolean deliveryConfirmed) {
		this.deliveryConfirmed = deliveryConfirmed;
	}

	public int getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(int invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Route getDeliveryRoute() {
		return deliveryRoute;
	}

	public void setDeliveryRoute(Route deliveryRoute) {
		this.deliveryRoute = deliveryRoute;
	}

	public Route getPickupRoute() {
		return pickupRoute;
	}

	public void setPickupRoute(Route pickupRoute) {
		this.pickupRoute = pickupRoute;
	}

	public Route getReturnRoute() {
		return returnRoute;
	}

	public void setReturnRoute(Route returnRoute) {
		this.returnRoute = returnRoute;
	}

}