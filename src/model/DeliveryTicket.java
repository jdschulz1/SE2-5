package model;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import dtDAO.LocalDateTimeConverter;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.DeliveryTracker;
import utils.CSVWriter;

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
	@JoinColumn(name = "courier_id")
	@OneToOne(cascade = CascadeType.PERSIST)
	private Courier courier;

	/**
	 * Date and time that the order call is received by the Order Taker.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "order_date_time", columnDefinition = "TIMESTAMP")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime orderDateTime;
	
	/**
	 * @return the estimatedDeliveryTime
	 */
	public LocalDateTime getEstimatedDeliveryTime() {
		return estimatedDeliveryTime;
	}

	/**
	 * @param estimatedDeliveryTime the estimatedDeliveryTime to set
	 */
	public void setEstimatedDeliveryTime(LocalDateTime estimatedDeliveryTime) {
		this.estimatedDeliveryTime = estimatedDeliveryTime;
	}

	/**
	 * @return the actualDeliveryTime
	 */
	public LocalDateTime getActualDeliveryTime() {
		return actualDeliveryTime;
	}

	/**
	 * @param actualDeliveryTime the actualDeliveryTime to set
	 */
	public void setActualDeliveryTime(LocalDateTime actualDeliveryTime) {
		this.actualDeliveryTime = actualDeliveryTime;
	}

	/**
	 * Order takers are the people that take phone calls and record the details of the Delivery Ticket.
	 */
	@JoinColumn(name = "user_id")
	@OneToOne(cascade = CascadeType.PERSIST)
	private User orderTaker;
	
	/**
	 * The Client from whom the Courier picks up the package.
	 */
	@JoinColumn(name = "pickup_client_id")
	@OneToOne(cascade = CascadeType.PERSIST)
	private Client pickupClient;
	
	/**
	 * The Client to whom the Courier will deliver the package.
	 */
	@JoinColumn(name = "delivery_client_id")
	@OneToOne(cascade = CascadeType.PERSIST)
	private Client deliveryClient;
	
	/**
	 * The Client (pickup or delivery)  who is paying for the delivery.
	 */
	@JoinColumn(name = "paying_client_id")
	@OneToOne(cascade = CascadeType.PERSIST)
	private Client payingClient;
	
	/**
	 * The time that the Client who placed the order requests that the Courier arrives to pickup the package.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "requested_pickup_time", columnDefinition = "TIMESTAMP")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime requestedPickupTime;
	
	/**
	 * The unique number created upon Delivery Ticket creation, which will be used to verify the correct package is picked up.
	 */
	@Column(name = "package_id")
	private int packageID;
	
	/**
	 * The estimated time the package is expected to be delivered at.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "estimated_delivery_time", columnDefinition = "TIMESTAMP")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime estimatedDeliveryTime;

	/**
	 * The time that the system calculates the Courier will need to leave the Office in order to reach the pickup location by the requested pickup time.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "calculated_departure_time", columnDefinition = "TIMESTAMP")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime calculatedDepartureTime;
	
	/**
	 * The actual time that the Courier departs on the delivery circuit.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "actual_departure_time", columnDefinition = "TIMESTAMP")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime actualDepartureTime;
	
	/**
	 * The actual time that the Courier picks the package up from the pickup Client.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "actual_pickup_time", columnDefinition = "TIMESTAMP")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime actualPickupTime;
	
	/**
	 * The actual time that the package is delivered by the Courier to the delivery Client.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "actual_delivery_time", columnDefinition = "TIMESTAMP")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime actualDeliveryTime;
	
	/**
	 * The actual time that the Courier returns from the delivery.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "actual_return_time", columnDefinition = "TIMESTAMP")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime actualReturnTime;
	
	/**
	 * The amount of the monetary bonus, awarded to the Courier for a successful on-time delivery of the package.
	 */
	@Column(name = "bonus_amount")
	private BigDecimal bonusAmount;
	
	/**
	 * Any special remarks about the delivery.
	 */
	@Column(name = "special_remarks")
	private String specialRemarks;
	
	/**
	 * A boolean to record that the delivery has been confirmed with the delivery Client.
	 */
	@Column(name = "delivery_confirmed")
	private boolean deliveryConfirmed;
	
	/**
	 * The invoice number associated with the delivery ticket.
	 */
	@Column(name = "invoice_number")
	private int invoiceNumber;
	
	@Column(name = "price")
	private BigDecimal price;
	/**
	 * The route from pickup location to delivery location.
	 */
	@Transient
	private Route deliveryRoute;
	
	/**
	 * The route from the company location to the pickup location.
	 */
	@Transient
	private Route pickupRoute;
	
	/**
	 * The route from the delivery to the company location.
	 */
	@Transient
	private Route returnRoute;

	/**
	 * Sets the courier bonus for the delivery.
	 */
	@Transient
	private static DeliveryTracker deliveryTracker;
	
	public void calculateBonus() {
		deliveryTracker = DeliveryTracker.getDeliveryTracker();
		this.setBonusAmount(new BigDecimal(0));
		if(isOnTime())
			this.setBonusAmount(deliveryTracker.getBonusAmount());
	}

	/**
	 * Determines whether the delivery was made within the time window to be considered "on time".
	 */
	public boolean isOnTime() {
		deliveryTracker = DeliveryTracker.getDeliveryTracker();
		Duration between = Duration.between(this.estimatedDeliveryTime, this.actualDeliveryTime).abs();
		return (between.getSeconds() <= (deliveryTracker.getBonusTimeVariance() * 60));
	}

	/**
	 * Generate the Courier Package from the details stored in the delivery ticket.  Populates the courierPackage attribute.
	 */
	public void generateCourierPackage() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd YYYY");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
		
		if(this.price == null)this.calculatePrice();
		else if(this.pickupRoute == null){
			this.pickupRoute = new Route(DeliveryTracker.getDeliveryTracker().getCompanyLocation(), this.pickupClient.getLocation());
			this.deliveryRoute = new Route(this.pickupClient.getLocation(), this.deliveryClient.getLocation());
			this.returnRoute = new Route(this.deliveryClient.getLocation(), DeliveryTracker.getDeliveryTracker().getCompanyLocation());
			if(this.pickupRoute == null || this.deliveryRoute == null || this.returnRoute == null){
				Alert a = new Alert(AlertType.ERROR);
		        a.setTitle("Error");
		        a.setHeaderText("Pickup Route Not Possible");
		        a.setContentText("The Pickup Route failed to generate with the existing Traffic Impediments.");
		        a.showAndWait();
			}
		}
    	
    	FileWriter writer;
        try {
        	String csvFile = System.getProperty("user.home") + "\\Desktop\\CourierPackage-" + this.getPackageID() + ".csv";
            writer = new FileWriter(csvFile);
            
            CSVWriter.writeLine(writer,Arrays.asList("Courier Package"));
            CSVWriter.writeLine(writer,Arrays.asList("Package Details", "", "Delivery Details"));
            CSVWriter.writeLine(writer,Arrays.asList("Order Date", this.orderDateTime.format(formatter), "Calculated Departure Time", this.calculatedDepartureTime.format(timeFormatter)));
            CSVWriter.writeLine(writer,Arrays.asList("Package ID", Integer.valueOf(this.packageID).toString(), "Requested Pickup Time", this.requestedPickupTime.format(timeFormatter)));
            CSVWriter.writeLine(writer,Arrays.asList("Courier", this.courier.getName(), "Estimated Delivery Time", this.estimatedDeliveryTime.format(timeFormatter)));
            CSVWriter.writeLine(writer,Arrays.asList("Pickup Client", this.pickupClient.getName(), "Total Distance", Integer.toString(this.calculateTotalDistance())+ " blocks"));
            CSVWriter.writeLine(writer,Arrays.asList("Delivery Client", this.deliveryClient.getName(), "Special Remarks", this.specialRemarks));
            CSVWriter.writeLine(writer,Arrays.asList(""));
            CSVWriter.writeLine(writer,Arrays.asList("Delivery Instructions"));
            CSVWriter.writeLine(writer,Arrays.asList("Pick Up the Package:"));
            for(Instruction i : this.pickupRoute.getInstructionsList()) {
                CSVWriter.writeLine(writer,Arrays.asList(i.CreateInstruction()));
            }
            CSVWriter.writeLine(writer,Arrays.asList("Deliver the Package:"));
            for(Instruction i : this.deliveryRoute.getInstructionsList()) {
                CSVWriter.writeLine(writer,Arrays.asList(i.CreateInstruction()));
            }
            CSVWriter.writeLine(writer,Arrays.asList("Return to " + DeliveryTracker.getDeliveryTracker().getCompanyName() + ":"));
            for(Instruction i : this.returnRoute.getInstructionsList()) {
                CSVWriter.writeLine(writer,Arrays.asList(i.CreateInstruction()));
            }
            
            writer.flush();
			writer.close();
            Alert a = new Alert(AlertType.INFORMATION);
	        a.setTitle("File Saved");
	        a.setContentText("Your Courier Package has been saved to " + System.getProperty("user.home") + "\\Desktop");
            a.showAndWait();
        
        } catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Calculates the estimated delivery time based on distance and overhead times for pick up and drop off.
	 * @param pickupOverheadTIme
	 * @param deliveryOverheadTime
	 * @param distance
	 */
	public LocalDateTime calculateDeliveryTime() {
		 deliveryTracker = DeliveryTracker.getDeliveryTracker();
		 int blocksToTravel = this.deliveryRoute.getRouteDistance();
		 double travelTimeInMinutes = (double)blocksToTravel / (double)deliveryTracker.getBlocksToMile() / deliveryTracker.getCourierSpeed() * 60;
		 LocalDateTime deliveryTime = this.getRequestedPickupTime().plusMinutes(deliveryTracker.getPickupOverheadTime()).plusMinutes(deliveryTracker.getDeliveryOverheadTime()).plusMinutes((long)travelTimeInMinutes);
		 return deliveryTime;
 	}

	/**
	 * Calculates the time the courier is required to depart from the company office in order to pick up the package at the requested pick up time. This is calculated based on distance from the company office to pick up location and the requested pick up location.
	 */
	public LocalDateTime calculateDepartureTime() {
		deliveryTracker = DeliveryTracker.getDeliveryTracker();
		 int blocksToTravel = this.pickupRoute.getRouteDistance();
		 double travelTimeInMinutes = (double)blocksToTravel / (double)deliveryTracker.getBlocksToMile() / deliveryTracker.getCourierSpeed() * 60;
		 LocalDateTime departureTime = this.getRequestedPickupTime().minusMinutes(deliveryTracker.getPickupOverheadTime()).minusMinutes((long)travelTimeInMinutes);
		 return departureTime;
	}

	/**
	 * Calculates a price quote for the delivery using the billRateBase, billRatePerBlock, and routeDistance.
	 */
	public BigDecimal calculatePrice() {
		/*
		 * distanceToTravel =
		 * 		graph.calculateDistanceInBlocks(office,pickupLocation) +
		 * 		graph.calculateDistanceInBlocks(pickupLocation, deliveryLocation) +
		 * 		graph.calculateDistanceInBlocks(deliveryLocation, office)
		 * 
		 * price = billRateBase + (billRatePerBlock * distanceToTravel)
		*/
		if(this.pickupRoute == null){
			this.pickupRoute = new Route(DeliveryTracker.getDeliveryTracker().getCompanyLocation(), this.pickupClient.getLocation());
			this.deliveryRoute = new Route(this.pickupClient.getLocation(), this.deliveryClient.getLocation());
			this.returnRoute = new Route(this.deliveryClient.getLocation(), DeliveryTracker.getDeliveryTracker().getCompanyLocation());
			if(this.pickupRoute == null || this.deliveryRoute == null || this.returnRoute == null){
				Alert a = new Alert(AlertType.ERROR);
		        a.setTitle("Error");
		        a.setHeaderText("Pickup Route Not Possible");
		        a.setContentText("The Pickup Route failed to generate with the existing Traffic Impediments.");
		        a.showAndWait();
		        return new BigDecimal(0);
			}
		}
		
		BigDecimal distanceToTravel = BigDecimal.valueOf(this.pickupRoute.getRouteDistance() + this.deliveryRoute.getRouteDistance() + this.returnRoute.getRouteDistance());
		
		return DeliveryTracker.getDeliveryTracker().getBillRateBase().add(DeliveryTracker.getDeliveryTracker().getBillRatePerBlock().multiply(distanceToTravel));
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
	
	public int calculateTotalDistance(){
		int totalDistance = this.getPickupRoute().getRouteDistance() +  this.getDeliveryRoute().getRouteDistance() + this.getReturnRoute().getRouteDistance();
		return totalDistance;
	}

}