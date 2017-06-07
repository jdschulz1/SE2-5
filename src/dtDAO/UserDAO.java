package dtDAO;

import java.util.List;

import javax.persistence.TypedQuery;

import model.User;

public class UserDAO {
	public static void saveUser(User user) {
		emDAO.getEM().persist(user);
	}
	public static void addUser(User User) {
		emDAO.getEM().persist(User);
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

	public static void removeUser(User user)
	{
		emDAO.getEM().remove(user);
	}
}
