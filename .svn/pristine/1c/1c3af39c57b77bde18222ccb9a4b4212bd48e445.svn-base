package BOL;


public class User 
{
	/***
	 * Creates a user and returns it, represented as a BOLO object
	 * @param username
	 * @return BOLO object that represents the created user
	 * @exception If error occured accessing the database.
	 */
	public static BOLO.User createUser(String username, String password) throws Exception
	{		
		if(DAL.User.exists(username, password))
			throw new Exception(String.format("user '%s' already exists", username));
		else
			return DAL.User.createUser(username, password);
	}
	

}
