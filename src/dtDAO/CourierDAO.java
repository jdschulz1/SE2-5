package dtDAO;

import java.util.List;

import javax.persistence.TypedQuery;

import model.Courier;
import model.DeliveryTracker;

public class CourierDAO {

	public static void saveCourier(Courier courier) {
		emDAO.getEM().getTransaction().begin();
		emDAO.getEM().persist(courier);
		emDAO.getEM().getTransaction().commit();
	}
	public static void addCourier(Courier Courier) {
		emDAO.getEM().getTransaction().begin();
		emDAO.getEM().persist(Courier);
		emDAO.getEM().getTransaction().commit();
	}

	public static List<Courier> listCourier()
	{
		TypedQuery<Courier> query = emDAO.getEM().createQuery("SELECT courier FROM courier courier", Courier.class);
		return query.getResultList();
	}

	public static Courier findCourierById(int id)
	{
		Courier courier = emDAO.getEM().find(Courier.class, new Integer(id));
		return courier;
	}

	public static boolean removeCourier(Courier courier)
	{
		DeliveryTracker.deleteCourier(courier);
		emDAO.getEM().getTransaction().begin();
		int sizeOld = listCourier().size();
		emDAO.getEM().remove(courier);
		emDAO.getEM().getTransaction().commit();
		
		return listCourier().size() != sizeOld;
	}
}
