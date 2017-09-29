/**
 * This class represents the Admin service. 
 * Each method represents operation that the service exposes.
 * 
 * This service is useed for ad hoc testing and administering of the underlying services application
 */

package WSL;

import javax.xml.namespace.QName;
import org.apache.axis2.AxisFault;

public class AdminFacade 
{
	/**
	 * Authenticates the client.
	 * @param username
	 * @param password
	 * @return a token hash that can be used on subsequent web service calls.
	 * @throws Exception authentication fails.
	 */
	public String authenticate( String username, String password) throws AxisFault
	{
		try
		{
			return BSL.LoginAdmin.authenticate(username, password);
		}
		catch (Exception err)
		{
			throw new org.apache.axis2.AxisFault(new QName("http://someuri.org", "FaultException"), err.getMessage(), err);
		}
	}
	
	/***
	 * Crude way to initialise the database
	 * @return true if the initialisation works or exception indicates issue
	 * @throws AxisFault
	 */
	public boolean initialise() throws AxisFault
	{
		try
		{
			DAL.HibernateHelper.getCurrentSession().beginTransaction().commit();
			DAL.HibernateHelper.tearDown();
		}
		catch( Exception err )
		{
			throw new org.apache.axis2.AxisFault(new QName("http://someuri.org", "FaultException"), err.getMessage(), err);
		}
		return true;
	}
			
}
