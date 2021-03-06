package model;
import java.io.Serializable;
import java.time.LocalDateTime;

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

import dtDAO.LocalDateTimeConverter;

/**
 * A TrafficImpediment is an object describing traffic congestion or a construction project that will make an intersection unavailable for a given date.
 */
@Entity(name = "traffic_impediment")
public class TrafficImpediment implements Serializable{

	public TrafficImpediment(){
		
	}
	
	public TrafficImpediment(Intersection intersection, LocalDateTime startDate, LocalDateTime endDate){
		this.intersection = intersection;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Represents the identification number of the traffic impediment.
	 */
	@Id
	@Column(name = "impediment_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long impedimentId;
	
	/**
	 * The intersection that is unavailable due to traffic or construction.
	 */
	@JoinColumn(name = "intersection_id")
	@OneToOne(cascade = CascadeType.PERSIST)
	private Intersection intersection;
	
	/**
	 * The date and time that the intersection will start to be unavailable due to the TrafficImpediment.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date", columnDefinition = "TIMESTAMP")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime startDate;
	
	/**
	 * The date and time that the intersection will ?be available once again due to the TrafficImpediment going away.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_date", columnDefinition = "TIMESTAMP")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime endDate;
	
	public Intersection getIntersection() {
		return intersection;
	}
	public void setIntersection(Intersection intersection) {
		this.intersection = intersection;
	}
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	public LocalDateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

}