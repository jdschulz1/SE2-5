package dtDAO;

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
		if(query.getResultList().size() > 0) {
			return query.getSingleResult();
		}
		else {
			return null;
		}
	}

}
