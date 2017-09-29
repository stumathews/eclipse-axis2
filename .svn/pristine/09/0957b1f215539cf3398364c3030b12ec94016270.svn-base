/**
 * 
 */
package DAL;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author stuart
 *
 */
public class HibernateHelper 
{
    private static SessionFactory sessionFactory;
	@SuppressWarnings("deprecation")
	private static SessionFactory setUp() throws Exception 
	{
				// A SessionFactory is set up once for an application		
		        sessionFactory = new Configuration()
		        	.configure() // configures settings from hibernate.cfg.xml		
		            .buildSessionFactory();
		return sessionFactory; 
	}
	/**
	 * Gets the static session object, which is instantiated once.
	 * @return the session
	 * @throws Exception if the current session could not be fetched.
	 */
	public static Session getCurrentSession() throws Exception
	{		
		try
		{			
			return sessionFactory.openSession();				
		}
		catch(Exception error)
		{
			return setUp().openSession();
		}		
	}
	
	public static  void tearDown() throws Exception 
	{		
        if ( sessionFactory != null ) 
        {
           sessionFactory.close();
        }
		
	}
}
