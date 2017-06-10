package dtDAO;

import java.util.List;

import javax.persistence.TypedQuery;

import model.Intersection;

public class IntersectionDAO {

	public static void saveIntersection(Intersection intersection) {
		emDAO.getEM().persist(intersection);
	}
	public static void addIntersection(Intersection Intersection) {
		emDAO.getEM().getTransaction().begin();
		emDAO.getEM().persist(Intersection);
		emDAO.getEM().getTransaction().commit();
	}

	public static List<Intersection> listIntersection()
	{
		TypedQuery<Intersection> query = emDAO.getEM().createQuery("SELECT intersection FROM intersection intersection", Intersection.class);
		return query.getResultList();
	}

	public static Intersection findIntersectionById(int id)
	{
		Intersection intersection = emDAO.getEM().find(Intersection.class, new Integer(id));
		return intersection;
	}

	public static void removeIntersection(Intersection intersection)
	{
		emDAO.getEM().remove(intersection);
	}
}
