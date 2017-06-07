package dtDAO;

import java.util.List;

import javax.persistence.TypedQuery;

import model.Street;

public class StreetDAO {
	public static void saveStreet(Street street) {
		emDAO.getEM().persist(street);
	}
	public static void addStreet(Street Street) {
		emDAO.getEM().persist(Street);
	}

	public static List<Street> listStreet()
	{
		TypedQuery<Street> query = emDAO.getEM().createQuery("SELECT street FROM street street", Street.class);
		return query.getResultList();
	}

	public static Street findStreetById(int id)
	{
		Street street = emDAO.getEM().find(Street.class, new Integer(id));
		return street;
	}

	public static void removeStreet(Street street)
	{
		emDAO.getEM().remove(street);
	}
}
