package dtDAO;

import java.util.List;

import javax.persistence.TypedQuery;

import model.DeliveryTracker;

public class DeliveryTrackerDAO {

	public static void save(DeliveryTracker deliveryTracker) {
		emDAO.getEM().getTransaction().begin();
		emDAO.getEM().persist(deliveryTracker);
		emDAO.getEM().getTransaction().commit();
	}

	public static DeliveryTracker getDeliveryTracker()
	{
		TypedQuery<DeliveryTracker> query = emDAO.getEM().createQuery("SELECT deliverytracker FROM deliverytracker deliverytracker", DeliveryTracker.class);
		return query.getSingleResult();
	}

}
