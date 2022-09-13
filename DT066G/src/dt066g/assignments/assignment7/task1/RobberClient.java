package dt066g.assignments.assignment7.task1;

import dt066g.assignments.assignment7.task1.Robber;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Handles communication between server/client with RMI
 * @author Daniel Westerlund
 */
public class RobberClient {
	Registry registry;
	Robber stub;

	/**
	 * Default constructor, using default port 10006
	 */
	public RobberClient(){
		this(10006);
	}

	/**
	 * Constructor that creates registry and stub
	 * @param port port that registry will be available on
	 */
	public RobberClient(int port){
		try{
			registry = LocateRegistry.getRegistry(port);
			stub = (Robber) registry.lookup("Robber");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Send a message to the server and returns answer from server
	 * @param msg msg to the server
	 * @return answer from the server or exception msg
	 */
	public String sendToRobber(String msg) {
		String answer;
		try {
			printToConsole("sendToRobber: To server: " + msg);
			answer = stub.toRobber(msg);
			printToConsole("sendToRobber: From server: " + answer);
		}
		//Returns error msg if exception is caught
		catch (IOException e) {
			e.printStackTrace();
			answer = e.getMessage();
		}
		return answer;
	}

	/**
	 * Decrypt a robber-msg
	 * @param msg to be decrypted
	 * @return a robber-msg, string that has been decrypted
	 */
	public String sendFromRobber(String msg){
		String answer;
		try {
			printToConsole("sendFromRobber: To server: " + msg);
			answer = stub.fromRobber(msg);
			printToConsole("sendFromRobber: From server: " + answer);
		}
		//Returns error msg if exception is caught
		catch (IOException e) {
			e.printStackTrace();
			answer = e.getMessage();
		}
		return answer;
	}


	/**
	 * Print a message to console, only for debug
	 * @param msg to be written
	 */
	private void printToConsole(String msg){
		System.out.println(msg);
	}


}
