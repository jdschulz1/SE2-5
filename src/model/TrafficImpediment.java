package model;
import java.time.LocalDateTime;

/**
 * A TrafficImpediment is an object describing traffic congestion or a construction project that will make an intersection unavailable for a given date.
 */
public class TrafficImpediment {

	/**
	 * The intersection that is unavailable due to traffic or construction.
	 */
	private Intersection intersection;
	/**
	 * The date and time that the intersection will start to be unavailable due to the TrafficImpediment.
	 */
	private LocalDateTime startDate;
	/**
	 * The date and time that the intersection will ?be available once again due to the TrafficImpediment going away.
	 */
	private LocalDateTime endDate;

}