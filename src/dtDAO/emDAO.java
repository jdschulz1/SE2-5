package dtDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class emDAO {
	static EntityManagerFactory entityManagerFactory;
	static EntityManager em;
	
	public static void initEM()
	{
	entityManagerFactory =  Persistence.createEntityManagerFactory("Team5DT");
    em = entityManagerFactory.createEntityManager();
	}
	
	public static EntityManager getEM()
	{
		if (em==null)
		{ 
			initEM();
		
		}
		return em;
	}
	
	public static void close()
	{
		em.close();
	    entityManagerFactory.close();
	}
}
