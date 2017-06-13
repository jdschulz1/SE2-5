package dtDAO;

import java.util.List;

import javax.persistence.TypedQuery;

import model.DeliveryTracker;
import model.User;

public class UserDAO {
	public static void saveUser(User user) {
		emDAO.getEM().getTransaction().begin();
		emDAO.getEM().persist(user);
		emDAO.getEM().getTransaction().commit();
	}
	public static void addUser(User User) {
		emDAO.getEM().getTransaction().begin();
		emDAO.getEM().persist(User);
		emDAO.getEM().getTransaction().commit();
	}

	public static List<User> listUser()
	{
		TypedQuery<User> query = emDAO.getEM().createQuery("SELECT user FROM user user", User.class);
		return query.getResultList();
	}

	public static User findUserById(int id)
	{
		User user = emDAO.getEM().find(User.class, new Integer(id));
		return user;
	}

	public static boolean removeUser(User user)
	{
		DeliveryTracker.deleteUser(user);
		emDAO.getEM().getTransaction().begin();
		int sizeOld = listUser().size();
		emDAO.getEM().remove(user);
		emDAO.getEM().getTransaction().commit();
		
		return listUser().size() != sizeOld;
	}
}
