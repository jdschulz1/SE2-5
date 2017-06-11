package dtDAO;

import java.util.List;

import javax.persistence.TypedQuery;

import model.DeliveryTracker;
import model.Street;
import model.TrafficImpediment;

public class TrafficImpedimentDAO {

	public static void saveTrafficImpediment(TrafficImpediment impediment) {
		emDAO.getEM().getTransaction().begin();
		emDAO.getEM().persist(impediment);
		emDAO.getEM().getTransaction().commit();
	}
	public static void addTrafficImpediment(TrafficImpediment TrafficImpediment) {
		List<Street> affectedStreets = StreetDAO.affectedStreets(TrafficImpediment.getIntersection());
		
		emDAO.getEM().getTransaction().begin();
		
		emDAO.getEM().persist(TrafficImpediment);
		emDAO.getEM().getTransaction().commit();
	}

	public static List<TrafficImpediment> listTrafficImpediment()
	{
		TypedQuery<TrafficImpediment> query = emDAO.getEM().createQuery("SELECT traffic_impediment FROM traffic_impediment traffic_impediment", TrafficImpediment.class);
		return query.getResultList();
	}

	public static TrafficImpediment findTrafficImpedimentById(int id)
	{
		TrafficImpediment impediment = emDAO.getEM().find(TrafficImpediment.class, new Integer(id));
		return impediment;
	}

	public static boolean removeTrafficImpediment(TrafficImpediment impediment)
	{
		DeliveryTracker.deleteTrafficImpediment(impediment);
		emDAO.getEM().getTransaction().begin();
		int sizeOld = listTrafficImpediment().size();
		emDAO.getEM().remove(findTrafficImpediment(impediment));
		emDAO.getEM().getTransaction().commit();
		System.out.println("Deleted impediment on " + impediment.getIntersection().getName() + ": " + (listTrafficImpediment().size() != sizeOld));
		
		return listTrafficImpediment().size() != sizeOld;
	}
	
	public static TrafficImpediment findTrafficImpediment(TrafficImpediment ti){
		for(TrafficImpediment t : listTrafficImpediment()){
			if(t.equals(ti)){
				System.out.println("t is " + t.getIntersection().getName() + "(" + t.getStartDate().toLocalDate() + " - " + t.getEndDate().toLocalDate() + ")");
				System.out.println("ti is " + ti.getIntersection().getName() + "(" + ti.getStartDate().toLocalDate() + " - " + ti.getEndDate().toLocalDate() + ")");
				return ti;
			}
		}
		return null;
	}
}
