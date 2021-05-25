package dt066g.assignments.assignment4.task1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Server that will take a text and translate it robberlanguage
 * and then send it back
 */
public class RobberServer {
	private static int PORT = 0;

	// TODO: add code to:
	// 1. wait for a datagram (message) from a client on the specified 'port'
	// 2. translate message to robber language using RobberLanguage class from assignment 3
	// 3. send translated message back to the client as a datagram
	// 4. wait for a new datagram to receive
	// 5. Optional, implement a GUI for the server

	/**
	 * Creates RobberServer with given port
	 * @param port port that server will run on
	 */
	public RobberServer(int port) {
		PORT = port;
	}

	/**
	 * Method that runs the server
	 * @throws Exception IO-errors
	 */
	private void start() throws Exception {
		ServerSocket serverSocket = new ServerSocket(PORT);
		printMsg("Server started on port " + PORT);


		//Always run server
		while(true){
			try {
				//Waiting for client to connect
				Socket clientSocket = serverSocket.accept();
				clientSocket.setSoTimeout(10000);
				printMsg("New Client connected: " + clientSocket.getInetAddress());

				//Read msg from client
				String newMsg = readMessage(clientSocket);
				printMsg("Message from client: " + newMsg);

				//Send back translated msg
				String toRobber = dt066g.assignments.assignment3.task1.RobberLanguage.toRobber(newMsg);
				sendMsg(clientSocket, toRobber);
				printMsg("Message to client: " + toRobber);

				clientSocket.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Method responsible to send msg to client
	 * @param clientSocket socket to the client
	 * @param msg that will be send to client
	 */
	public void sendMsg(Socket clientSocket, String msg){
		try{
			if(clientSocket != null && !clientSocket.isClosed()) {
				DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
				dataOutputStream.writeUTF(msg);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 *
	 * @param clientSocket socket to the client communication
	 * @return msg from client or null if it not could be read
	 * @throws IOException if IOException occurred when communicated
	 */
	public String readMessage(Socket clientSocket) throws IOException {
		DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
		if(!clientSocket.isClosed()){
			return dis.readUTF();
		}
		else
			return null;
	}

	/**
	 * Running the program
	 * @param args parameters with given host/port
	 */
	public static void main(String[] args) {
		PORT = getPortNumber(args);
		RobberServer robberServer  = new RobberServer(PORT);
		try {
			robberServer.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Prints a msg to console
	 * @param msg
	 */
	private void printMsg(String msg){
		System.out.println(msg);
	}

	/**
	 * Fix port, uses parameters from user or using default (10001)
	 * @param args
	 * @return
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
}