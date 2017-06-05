package model;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Delivery tickets are records containing all information related to a delivery request, including pickup and delivery details.
 */
public class DeliveryTicket {

	/**
	 * The Courier involved in the delivery represented by this DeliveryTicket.
	 */
	private Courier courier;
	/**
	 * Represents the identification number of the ticket.
	 */
	private int ticketId;
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
	public void calculateBonus() {
		// TODO - implement DeliveryTicket.calculateBonus
		throw new UnsupportedOperationException();
	}

	/**
	 * Determines whether the delivery was made within the time window to be considered "on time".
	 */
	public boolean isOnTime() {
		// TODO - implement DeliveryTicket.isOnTime
		throw new UnsupportedOperationException();
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

}