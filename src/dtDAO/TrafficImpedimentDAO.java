package dtDAO;

import java.util.List;

import javax.persistence.TypedQuery;

import model.TrafficImpediment;

public class TrafficImpedimentDAO {

	public static void saveTrafficImpediment(TrafficImpediment impediment) {
		emDAO.getEM().persist(impediment);
	}
	public static void addTrafficImpediment(TrafficImpediment TrafficImpediment) {
		emDAO.getEM().getTransaction().begin();
		emDAO.getEM().persist(TrafficImpediment);
		emDAO.getEM().getTransaction().commit();
	}

	public static List<TrafficImpediment> listTrafficImpediment()
	{
		TypedQuery<TrafficImpediment> query = emDAO.getEM().createQuery("SELECT impediment FROM impediment impediment", TrafficImpediment.class);
		return query.getResultList();
	}

	public static TrafficImpediment findTrafficImpedimentById(int id)
	{
		TrafficImpediment impediment = emDAO.getEM().find(TrafficImpediment.class, new Integer(id));
		return impediment;
	}

	public static void removeTrafficImpediment(TrafficImpediment impediment)
	{
		emDAO.getEM().remove(impediment);
	}
}
