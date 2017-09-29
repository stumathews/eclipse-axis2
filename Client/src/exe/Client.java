/**
 *  Client that communicates to the web services.
 */
package exe;

import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Set;

import wsl.*;
import org.apache.axis2.AxisFault;
import org.apache.axis2.wsdl.WSDL2Java;

/**
 * @author stuart
 * Client that invokes remove web service calls
 */
public class Client 
{
		
	public Client()
	{
		updateWSDL(false);	
	}
	
	/**
	 * Entry point of client.
	 */
	public void start()
	{
		try
		{
			if( !initialise() )
				throw new Exception("could not initialise.");
			
			String token = authenticate("Administrator","apps3cur3");			
			createUser(token,"Stuart","Password");
			Logout(token);
			System.out.println("Client Finished");
		}
		catch( Exception error)
		{
			System.out.println("Client Error:"+error.getMessage());
		}
	
	}

	private boolean initialise() throws RemoteException, AxisFault
	{
		AdminFacadeStub.Initialise init = new AdminFacadeStub.Initialise();
		AdminFacadeStub.InitialiseResponse response = new AdminFacadeStub().initialise(init);
		
		boolean result = response.get_return();
		return result;
	}

	private void updateWSDL(boolean regenerate) {
		if(regenerate) 
		{		
			Set<String[]> wsdl_files = getWSDLFiles();		
			regenerateStubs(wsdl_files);
		}
	}
	/** Builds up a Set of wsdl references (This needs to be manually updated when new services are added)
	 * @return Set of wsdl references
	 */
	private Set<String[]> getWSDLFiles() {
		Set<String[]> wsdl_files = (Set<String[]>) new HashSet<String[]>();
		
		wsdl_files.add(new String[]{"-uri","http://localhost:8080/StuartMathewsService/services/AdminFacade?wsdl","-or"});
		wsdl_files.add(new String[]{"-uri","http://localhost:8080/StuartMathewsService/services/UserFacade?wsdl","-or"});
		return wsdl_files;
	}
	/** Calls WSDL2JAVA on provided .wsdl references.
	 * @param wsdl_files
	 * @throws Exception if there was a problem running WSDL2JAVA
	 */
	private void regenerateStubs(Set<String[]> wsdl_files)
	{
		boolean errors = false;
		System.out.println("Preparing refresh of WS Stubs...");
		for( String[] wsdl : wsdl_files)
		{
			System.out.println("Regenerating stubs based on "+ wsdl[1]);
			try
			{
				WSDL2Java.main(wsdl);
			}catch( Exception err)
			{  
				System.out.println("WSDL2Java error:" + err.getMessage());
				errors = true;
			}
		}
		if( !errors)
			System.out.println("Regeneration complete.");
		else
			System.out.println("There were errors encountered during the regeneration process.");
	}
	/**
	* Logs the user out and cleans up his/her session
	 * @param token
	 * @throws RemoteException
	 * @throws AxisFault
	 */
	private static void Logout(String token) throws RemoteException, AxisFault {
		UserFacadeStub.Logout logout_method = new UserFacadeStub.Logout();
		logout_method.setToken(token);
		UserFacadeStub.LogoutResponse logout_response = new UserFacadeStub().logout(logout_method);
		if( logout_response.get_return() == true)
			System.out.println("successfully logged out");
		else
			System.out.println("error trying to log out");
	}
	/** Creates a user in the system
	 * @param token
	 * @param username
	 * @param password
	 * @throws RemoteException
	 * @throws AxisFault User already exists or problem persisting.
	 */
	private static UserFacadeStub.User createUser(String token, String username, String password) throws RemoteException, AxisFault 
	{
		UserFacadeStub.CreateUser create_user = new UserFacadeStub.CreateUser();
			create_user.setToken(token);
			create_user.setUser(username);
			create_user.setPassword(password);		
		UserFacadeStub.CreateUserResponse response = new UserFacadeStub().createUser(create_user);
		UserFacadeStub.User created_user = response.get_return();
		return created_user;
		
	}
	/** Authenticates a username and password
	 * @param username
	 * @param password
	 * @return If provided username and password are correct, an authentication token is provided and can be used for subsequent WS request.
	 * @throws RemoteException
	 * @throws AxisFault is user already exists or persisting in db fails
	 */
	private static String authenticate(String username, String password) throws RemoteException, AxisFault 
	{
		AdminFacadeStub.Authenticate authenticate = new AdminFacadeStub.Authenticate();
			authenticate.setUsername(username);
			authenticate.setPassword(password);
		AdminFacadeStub.AuthenticateResponse authentication_result = new AdminFacadeStub().authenticate(authenticate);
		
		String result = authentication_result.get_return();
		return result;
	}
}
