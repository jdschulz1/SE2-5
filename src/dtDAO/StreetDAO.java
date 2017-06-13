package dtDAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import model.Intersection;
import model.Street;

public class StreetDAO {
	public static void saveStreet(Street street) {
		emDAO.getEM().getTransaction().begin();
		emDAO.getEM().persist(street);
		emDAO.getEM().getTransaction().commit();
	}
	public static void addStreet(Street Street) {
		emDAO.getEM().getTransaction().begin();
		emDAO.getEM().persist(Street);
		emDAO.getEM().getTransaction().commit();
	}

	public static List<Street> listStreet()
	{
		TypedQuery<Street> query = emDAO.getEM().createQuery("SELECT street FROM street street", Street.class);
		return query.getResultList();
	}
	
	public static List<Street> affectedStreets(Intersection impediment){
		List<Street> affected = new ArrayList<Street>();
		for(Street s : listStreet()){
			if(s.getSource() != null){
				if(s.getSource().equals(impediment) || s.getDestination().equals(impediment)){
					affected.add(s);
				}
			}
		}
		return affected;
	}
	
	public static ArrayList<Intersection> neighbors (Intersection intersection){
		ArrayList<Intersection> neighbors = new ArrayList<Intersection>();
		
		for(Street s : affectedStreets(intersection)){
			neighbors.add(s.getDestination());
		}
		return neighbors;
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
	
	public static Street findStreetByIntersections(Intersection src, Intersection dest){
		for(Street s : listStreet()){
			if(s.getSource().getName() == src.getName() && s.getDestination().getName() == dest.getName())
				return s;
		}
		return null;
	}
}
