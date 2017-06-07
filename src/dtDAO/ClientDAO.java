package dtDAO;

import java.util.List;

import javax.persistence.TypedQuery;

import model.Client;

public class ClientDAO {

	public static void saveClient(Client client) {
		emDAO.getEM().persist(client);
	}
	public static void addClient(Client Client) {
		emDAO.getEM().persist(Client);
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

	public static void removeClient(Client client)
	{
		emDAO.getEM().remove(client);
	}
}
