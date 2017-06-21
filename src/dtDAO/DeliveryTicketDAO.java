package dtDAO;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import dtDAO.LocalDateTimeConverter;
import java.util.List;

import javax.persistence.TypedQuery;

import model.Client;
import model.Courier;
import model.DeliveryTicket;
import model.DeliveryTracker;
import model.User;

public class DeliveryTicketDAO {

	public static void saveDeliveryTicket(DeliveryTicket ticket) {
		
		emDAO.getEM().getTransaction().begin();
		emDAO.getEM().persist(ticket);
		emDAO.getEM().getTransaction().commit();
	}
	public static void addDeliveryTicket(DeliveryTicket DeliveryTicket) {
		emDAO.getEM().getTransaction().begin();
		emDAO.getEM().persist(DeliveryTicket);
		emDAO.getEM().getTransaction().commit();
	}

	public static List<DeliveryTicket> listDeliveryTicket()
	{
		TypedQuery<DeliveryTicket> query = emDAO.getEM().createQuery("SELECT ticket FROM delivery_ticket ticket", DeliveryTicket.class);
		return query.getResultList();
	}
	
	public static List<DeliveryTicket> findDeliveryTicketByDateRange(LocalDateTime startDate, LocalDateTime endDate)
	{
		TypedQuery<DeliveryTicket> query = emDAO.getEM().createQuery("SELECT ticket FROM delivery_ticket ticket WHERE ticket.orderDateTime >= :date1 AND ticket.orderDateTime < :date2", DeliveryTicket.class);
		query.setParameter("date1", startDate);
		query.setParameter("date2", endDate);
		return query.getResultList();
	}
	
	public static List<DeliveryTicket> findDeliveryTicketsByClient(Client client)
	{
		TypedQuery<DeliveryTicket> query = emDAO.getEM().createQuery("SELECT ticket FROM delivery_ticket ticket WHERE ticket.payingClient = :c OR ticket.pickupClient = :c OR ticket.deliveryClient = :c", DeliveryTicket.class);
		query.setParameter("c", client);
		return query.getResultList();
	}
	public static List<DeliveryTicket> findDeliveryTicketsByClientByDate(Client client, LocalDateTime sdate, LocalDateTime edate)
	{
		TypedQuery<DeliveryTicket> query = emDAO.getEM().createQuery("SELECT ticket FROM delivery_ticket ticket WHERE (ticket.payingClient = :c OR ticket.pickupClient = :c OR ticket.deliveryClient = :c) AND (ticket.orderDateTime >= :sd AND ticket.orderDateTime < :ed)", DeliveryTicket.class);
		query.setParameter("c", client);
		query.setParameter("sd", sdate);
		query.setParameter("ed", edate);
		return query.getResultList();
	}
	public static List<DeliveryTicket> findDeliveryTicketsByCourier(Courier courier)
	{
		TypedQuery<DeliveryTicket> query = emDAO.getEM().createQuery("SELECT ticket FROM delivery_ticket ticket WHERE ticket.courier = :c", DeliveryTicket.class);
		query.setParameter("c", courier);
		return query.getResultList();
	}
	
	public static List<DeliveryTicket> findDeliveryTicketsByUser(User user)
	{
		TypedQuery<DeliveryTicket> query = emDAO.getEM().createQuery("SELECT ticket FROM delivery_ticket ticket WHERE ticket.orderTaker = :u", DeliveryTicket.class);
		query.setParameter("u", user);
		return query.getResultList();
	}
	
	public static DeliveryTicket findDeliveryTicketById(int id)
	{
		DeliveryTicket ticket = emDAO.getEM().find(DeliveryTicket.class, new Integer(id));
		return ticket;
	}

	public static boolean removeDeliveryTicket(DeliveryTicket ticket)
	{	
		DeliveryTracker.deleteDeliveryTicket(ticket);
		emDAO.getEM().getTransaction().begin();
		int sizeOld = listDeliveryTicket().size();
		emDAO.getEM().remove(ticket);
		emDAO.getEM().getTransaction().commit();
		
		return listDeliveryTicket().size() != sizeOld;
	}
}
