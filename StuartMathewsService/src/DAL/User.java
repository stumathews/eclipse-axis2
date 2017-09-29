/**
 * 
 */
package DAL;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.sun.istack.internal.logging.Logger;

import sun.rmi.runtime.Log;


/**
 * This represents the data access layer User class.
 * Its primary objective is to interface with the data entity later and construct BOLO objects for the BOL layer.
 *
 */
public class User {
	/**
	 * Creates a user
	 * @param username
	 * @param password
	 * @return BOLO.User
	 * @throws Exception if cannot persist user to the database
	 */
	public static BOLO.User createUser(String username, String password) throws Exception
	{
		DAL.DEL.User del_user = new DAL.DEL.User();
		del_user.setUsername(username);
		del_user.setPassword(password);
		
		Session session = HibernateHelper.getCurrentSession();
		session.beginTransaction();
			session.persist(del_user);
		session.getTransaction().commit();
			
			
		session.beginTransaction();		
		String query = String.format("from User where username = '%s' and password = '%s'", username, password);
		List results = session.createQuery(query).list();
		session.getTransaction().commit();
		session.close();
		
		for( DAL.DEL.User result_user : (List<DAL.DEL.User>)results)
		{
			BOLO.User bol_user = new BOLO.User();
				bol_user.setName(result_user.getUsername());
				bol_user.setPassword(result_user.getPassword());
				return bol_user;
		}
		throw new Exception("Could not persist user.");
		
	}
	/***
	 * Gets a named user in the database
	 * @param username
	 * @param password
	 * @return the user, or null if no user found
	 * @throws Exception
	 */
	public static DAL.DEL.User retrieve(String username, String password) throws Exception
	{
		Session session = HibernateHelper.getCurrentSession();
		session.beginTransaction();
			String query = String.format("from User where username = '%s' and password = '%s'",username,password);
		List<?> results = session.createQuery(query).list();
		session.getTransaction().commit();
		session.close();
		for( DAL.DEL.User user : (List<DAL.DEL.User>)results){			
			return user;
		}
		return null;
		
	}

	public static boolean exists(String username, String password) throws Exception
	{
		Session session = HibernateHelper.getCurrentSession();
		session.beginTransaction();
			String query = String.format("from User where username = '%s' and password = '%s'",username,password);
		List results = session.createQuery(query).list();
		session.getTransaction().commit();
		session.close();
		
		if( !results.isEmpty() ) 
			return true;
		else
			return false;
	
		
	}

	public static BOLO.User getUser(String username, String password) throws HibernateException, Exception {
		
			Session session = HibernateHelper.getCurrentSession();
			session.beginTransaction();
				String query = String.format("from User where username = '%s' and password = '%s'",username,password);
			List<?> results = session.createQuery(query).list();
			session.getTransaction().commit();
			session.close();
			
			for( DAL.DEL.User user : (List<DAL.DEL.User>)results){
				BOLO.User bol_user = new BOLO.User();
				bol_user.setPassword(user.getPassword());
				bol_user.setName(user.getUsername());
				return bol_user;
			}		
			return null;
		
	}
}
