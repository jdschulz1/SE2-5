package dtDAO;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import dtDAO.LocalDateTimeConverter;
import java.util.List;

import javax.persistence.TypedQuery;

import model.DeliveryTicket;
import model.DeliveryTracker;

public class DeliveryTicketDAO {

	public static void saveDeliveryTicket(DeliveryTicket ticket) {
		emDAO.getEM().persist(ticket);
	}
	public static void addDeliveryTicket(DeliveryTicket DeliveryTicket) {
		emDAO.getEM().persist(DeliveryTicket);
	}

	public static List<DeliveryTicket> listDeliveryTicket()
	{
		TypedQuery<DeliveryTicket> query = emDAO.getEM().createQuery("SELECT ticket FROM ticket ticket", DeliveryTicket.class);
		return query.getResultList();
	}
	
	public static List<DeliveryTicket> findDeliveryTicketByDateRange(LocalDateTime startDate, LocalDateTime endDate)
	{
		TypedQuery<DeliveryTicket> query = emDAO.getEM().createQuery("SELECT ticket FROM delivery_ticket ticket WHERE ticket.orderDateTime >= :date1 AND ticket.orderDateTime < :date2", DeliveryTicket.class);
		query.setParameter("date1", startDate);
		query.setParameter("date2", endDate);
		System.out.println("Results: " + query.getResultList().size());
		return query.getResultList();
	}

	public static DeliveryTicket findDeliveryTicketById(int id)
	{
		DeliveryTicket ticket = emDAO.getEM().find(DeliveryTicket.class, new Integer(id));
		return ticket;
	}

	public static void removeDeliveryTicket(DeliveryTicket ticket)
	{
		emDAO.getEM().remove(ticket);
	}
}
