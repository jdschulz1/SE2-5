package dtDAO;

import java.util.List;

import javax.persistence.TypedQuery;

import model.Client;
import model.DeliveryTracker;

public class ClientDAO {

	public static void saveClient(Client client) {
		
		emDAO.getEM().getTransaction().begin();
		emDAO.getEM().persist(client);
		emDAO.getEM().getTransaction().commit();
	}
	public static void addClient(Client Client) {
		emDAO.getEM().getTransaction().begin();
		emDAO.getEM().persist(Client);
		emDAO.getEM().getTransaction().commit();
	}

	public static List<Client> listClient()
	{
		TypedQuery<Client> query = emDAO.getEM().createQuery("SELECT client FROM client client", Client.class);
		return query.getResultList();
	}

	public static Client findClientById(int id)
	{
		Client client = emDAO.getEM().find(Client.class, new Integer(id));
		return client;
	}

	public static boolean removeClient(Client client)
	{
		DeliveryTracker.deleteClient(client);
		emDAO.getEM().getTransaction().begin();
		int sizeOld = listClient().size();
		emDAO.getEM().remove(client);
		emDAO.getEM().getTransaction().commit();
		
		return listClient().size() != sizeOld;
	}
}
