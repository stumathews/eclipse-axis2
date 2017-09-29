package exe;

import java.util.HashSet;
import java.util.Set;

import org.apache.axis2.wsdl.WSDL2Java;


public class main 
{
	
	
	/** Bootstrapping for Client. Regenerates WS files if asked to.
	 * @param args
	 * @throws Exception if there is a problem running WSDL2Java
	 */
	public static void main(String[] args) throws Exception 
	{		
		
		Client client = new Client();
		client.start();
	}


	

}
