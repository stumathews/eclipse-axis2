package BOL;
import java.util.UUID;
import java.util.Date;
import java.sql.Timestamp;

public class Authentication
{
	/**
	 * Default number of minutes a token is valid for.
	 */
	private static final int DEFAULT_MINS_VALID = 15;

	/***
	 * Authenticate username and password, provide token for 15 minutes if valid credentials are found.
	 * @param username the username
	 * @param password the password
	 * @return token that can be used in subsequent web service calls
	 */
	public static String authenticate( String username, String password ) throws Exception
	{		
		boolean password_is_correct = areCredentialsValid( username, password );
		if( password_is_correct == false )
		{
			throw new Exception("Invalid Username or password.");
		}
		else
		{		
			boolean store_token = true; //we want to persist it moving forward
			String token = null;			
			token = makeToken(store_token, username, password);			
			return token;
		}
	}
	
	/** Checks to see if the provided user name and password match anything in the system
	 * @param username
	 * @param password
	 * @return true if credentials found and match in system, else assume invalid credentials
	 */
	private static boolean areCredentialsValid(String username, String password) throws Exception
	{
		boolean special_user = BSL.Common.isValidSpecialUser(username, password);
				
		if( special_user == false )
		{			
			// Get get the user by checking if a user with specific password exists in db:
			if( DAL.User.getUser( username, password ) != null)
				return true;
			else
				return false;
			
		}
		
		return true;
	}
	
	
	/** 
	 * Produce business logic to create  a UUID that represents a token, store it into the database and give it a DEFAULT_MINS_VALID minute expiry
	 * @param auto_persist indicates if we should persist the token entity that is created.
	 * @return a string representing the token
	 * @throws Exception if persisting causes a problem.
	 */
	private static String makeToken(boolean auto_persist, String username, String password) throws Exception 
	{
		
		UUID 		uuid = UUID.randomUUID();
		BOLO.Token  token = new BOLO.Token();
		Date 		date = new java.util.Date();
		Timestamp 	tstamp = new java.sql.Timestamp( date.getTime() );		        
		
		token.setToken(uuid.toString());
		token.setIssued_time(tstamp);
		token.setMins_valid(DEFAULT_MINS_VALID);				
		
		// Ask the Data Access layer to store it for us.
		if(auto_persist == true)
			DAL.Token.storeToken(token, username, password );				
		return token.getToken();
	}

}
