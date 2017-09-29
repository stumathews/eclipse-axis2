/**
 * Business Logic for Tokens
 */
package BOL;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;


/** Represents Token bussiness logic.
 * @author stuart
 *
 */
public class Token 
{
	
	/**
	 * Checks if the token is valid and usable
	 * @param bolo_token to check
	 * @return true if the token if valid
	 * @throws Exception
	 */
	public static boolean isValid(BOLO.Token bolo_token) throws Exception
	{	
		if( hasExpired(bolo_token) == true )
			return false;
		else
			return true;
	}
	/** Determines if the token has expired.
	 * @param bolo_token
	 * @return true if the token is past its expiry time.
	 */
	private static boolean hasExpired(BOLO.Token bolo_token)
	{
		boolean 	isValid = true;
		Timestamp	token_timestamp = bolo_token.getIssued_time();
		int 		token_mins_valid = bolo_token.getMins_valid();
		Date 		date = new Date();
		Calendar 	cal = Calendar.getInstance();
		
		// Lets see what the time would be if the token expired, ie past its validity	
		cal.setTime(token_timestamp);
		cal.add(Calendar.MINUTE, token_mins_valid);
		
		Timestamp latest_valid_time = new Timestamp( cal.getTime().getTime());
		Timestamp time_now = new Timestamp( date.getTime());
		
		// Debug
		System.out.println("Time now: " + time_now.toString());
		System.out.println("Token issued time: " + token_timestamp.toString());
		System.out.println("Token expire time: " + latest_valid_time.toString());
		
		if( time_now.after(latest_valid_time))
			return true;
		else
			return false;		
		
	}
	/**
	 * Takes a token that has expired, get the associated user and frees up other old tokens
	 * created by this user.
	 * @param token
	 */
	public static boolean cleanOldUserTokens(String token) throws Exception {
		boolean worked = false;			
			if( BOL.Token.isValid(token)){
				DAL.Token.cleanOldUserTokens(token);
				worked = true;
			}
			else
				throw new Exception(String.format( "Provided token,'%s' is not valid" , token ) );
		
		return worked;
	}
	/**
	 * Checks to see if token is valid(exists in db)
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public static boolean isValid(String token) throws Exception {
			if( token.isEmpty())
				return false;
			BOLO.Token found_token = DAL.Token.getToken(token);
			if( BOL.Token.isValid(found_token) == true)
				return true;
			else
				return false;			
		
	}
}
