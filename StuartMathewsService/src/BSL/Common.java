
package BSL;

	/** Checks to see if the provided user name and password match anything in the system
	 * @param username
	 * @param password
	 * @return true if credentials found and match in system, else assume invalid credentials
	 */
	public class Common
	{
		public static boolean isValidSpecialUser(String username, String password) throws Exception
		{
			boolean isSpecial = false;
			
			isSpecial = (username.equalsIgnoreCase("admin") && password.equals("axis2"));			
			isSpecial = (username.equalsIgnoreCase("garfield") && password.equals("john"));		
			isSpecial = (username.equalsIgnoreCase("Administrator") && password.equals("apps3cur3"));
						
			return isSpecial;
		}
	}