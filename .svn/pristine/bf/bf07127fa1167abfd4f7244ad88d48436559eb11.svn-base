/**
 * 
 */
package BOL;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;


/**
 * @author stuart
 *
 */
public class ServiceAuthoriser 
{
	/***
	 * Verify that the token is correct
	 * @param token from the client
	 * @return true if the token exist in the db.
	 */
	public static void authorise(String token) throws Exception
	{
			try
			{	
				// Try and get token from the database				
				BOLO.Token bolo_token = DAL.Token.getToken(token);			
				if( BOL.Token.isValid(bolo_token) == false){				
					throw new Exception("Token is outdated. Re-authenticate.");
				}
			}
			catch( Exception error)
			{
				throw new Exception( String.format("Unable to Authenticate due to %s", error.getMessage()));				
			}
		
	}
}
