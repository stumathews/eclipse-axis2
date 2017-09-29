package WSL;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import javax.xml.namespace.QName;

public class UserFacade 
{
	/**
	 * Creates a new user
	 * @param token
	 * @param user
	 * @return a BOLO.User representing the new user.
	 * @throws Exception if failed to create a new user.
	 */
	public static BOLO.User createUser(String token, String user, String password) throws AxisFault
	{
		try
		{
			return BSL.UserAdmin.createUser(token, user, password);
		}
		catch (Exception err)
		{
			throw new AxisFault(new QName("http://someuri.org", "FaultException"), err.getMessage(), err);
		}
	}
	
	/**
	 * Cleans out all the persisted tokens for this user. 
	 * @param token
	 * @return
	 * @throws AxisFault
	 */
	public static boolean logout(String token) throws AxisFault
	{
		try
		{			
			return BOL.Token.cleanOldUserTokens(token);			
		}
		catch (Exception err)
		{
			throw new AxisFault(new QName("http://someuri.org", "FaultException"), err.getMessage(), err);
		}
		
	}
		
}
