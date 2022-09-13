package dt066g.assignments.assignment7.task1;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import dt066g.assignments.assignment7.task1.Robber;

/**
 * @author Daniel Westerlund
 * Server that will take a text and translate it robberlanguage
 * and then send it back
 */
public class RobberServer implements Runnable {
	private static int PORT = 10001;

	/**
	 * Default constructor
	 */
	public RobberServer() {
		super();
	}


	/**
	 * Running the program
	 * @param args parameters with given host/port
	 */
	public static void main(String[] args) {
		PORT = getPortNumber(args);
		System.setProperty("java.rmi.server.hostname","localhost");
		System.setProperty("permissions","java.security.AllPermission");
		try{
			new Thread(new RobberServer()).start();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Running server by creating a stub with available remote methods
	 */
	public void run(){
		try{
			RobberLanguage remoteMethods = new RobberLanguage();

			// Exports Robber-methods
			Robber stub = (Robber) UnicastRemoteObject.exportObject(remoteMethods,0);

			// Creates registry and binds stub and "Robber"
			Registry registry = LocateRegistry.createRegistry(PORT);
			registry.bind("Robber", stub);
			printMsg("RMI server started");
		}
		catch (Exception e){
			e.printStackTrace();
		}

	}

	/**
	 * Fix port, uses parameters from user or using default (10001)
	 * @param args with parameters from user
	 * @return port number that will be used on server
	 */
	private static int getPortNumber(String[] args){
		int output = 10001;
		try{
			if(args.length > 0)
				output = Integer.parseInt(args[0]);
		}
		catch (Exception e){
			e.printStackTrace();
		}

		return output;
	}


	/**
	 * Prints a msg to console
	 * @param msg to be shown to user
	 */
	private void printMsg(String msg){
		System.out.println(msg);
	}
}