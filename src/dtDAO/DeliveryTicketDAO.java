package dtDAO;

import java.util.List;

import javax.persistence.TypedQuery;

import model.DeliveryTicket;

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
