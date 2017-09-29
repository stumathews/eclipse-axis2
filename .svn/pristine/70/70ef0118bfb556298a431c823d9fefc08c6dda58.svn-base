/**
 * Represents the User service administration layer
 */
package BSL;

/**
 * @author stuart
 * This manages access to the Admin Service
 */
public class UserAdmin 
{
	/***
	 * Creates a new user
	 * @param token the token that represents your authenticated status
	 * @param username the intended name of the new user to be created.
	 */
	public static BOLO.User createUser( String token, String username, String password) throws Exception
	{		
		boolean is_special = BSL.Common.isValidSpecialUser(username, password);
		boolean needs_valid_login_token = !is_special;
		
		if(needs_valid_login_token == true)	
			BOL.ServiceAuthoriser.authorise(token);
		
		return BOL.User.createUser(username, password);
		
	}	
}
