package dtDAO;

import java.util.List;

import javax.persistence.TypedQuery;

import model.Courier;

public class CourierDAO {

	public static void saveCourier(Courier courier) {
		emDAO.getEM().persist(courier);
	}
	public static void addCourier(Courier Courier) {
		emDAO.getEM().persist(Courier);
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

	public static void removeCourier(Courier courier)
	{
		emDAO.getEM().remove(courier);
	}
}
