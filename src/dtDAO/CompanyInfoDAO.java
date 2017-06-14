package dtDAO;

import java.util.List;

import javax.persistence.TypedQuery;

import model.DeliveryTracker;
import model.TrafficImpediment;

public class CompanyInfoDAO {
	public static void save(DeliveryTracker companyInfo) {
		emDAO.getEM().getTransaction().begin();
		emDAO.getEM().persist(companyInfo);
		emDAO.getEM().getTransaction().commit();
	}
}
