/**
 * This service is responsible for authenticating users
 */
package BSL;

/**
 * @author stuart
 *
 */
public class LoginAdmin 
{
	/***
	 * Authenticate credentials against database
	 * @param username the username
	 * @param password the password
	 * @return a token that can be used in subsequent web service calls
	 * @throws Exception when credentials are invalid
	 */
	public static String authenticate( String username, String password) throws Exception
	{
		
		// No need to check permissions to access this service. everyone should be able to at least 
		// try to authenticate.
		{
			return BOL.Authentication.authenticate(username, password);
		}
	}
}
