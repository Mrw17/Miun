package dt066g.assignments.assignment4.task1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

/**
 * Handles communication between server/client from client-side
 * @author Daniel Westerlund
 */
public class RobberClient {

	/**
	 * Send a message to the server and returns answer from server
	 * @param host server address
	 * @param port port address
	 * @param msg msg to the server
	 * @return answer from the server or exception msg
	 */
	public String send(String host, int port, String msg) {
		System.out.printf("Trying to send: %s on host: %s:%s%n",msg,host,port);
		String answer;
		try {
			DatagramSocket datagramSocket = new DatagramSocket();
			datagramSocket.setSoTimeout(5000);
			printToConsole("To server: " + msg);
			sendMsg(datagramSocket, host,port,msg);
			answer = readMsg(datagramSocket);
			printToConsole("From server: " + answer);

			datagramSocket.close();
		}
		//Returns error msg if exception is caught
		catch (IOException e) {
			e.printStackTrace();
			answer = e.getMessage();
		}
		return answer;
	}

	/**
	 * Reads a message from a datagramSocket
	 * @param datagramSocket that will be listen on
	 * @return message from server
	 * @throws IOException if something went wrong
	 */
	private String readMsg(DatagramSocket datagramSocket) throws IOException {
		String output;
		try {
			int bufferLength = 1024;
			byte[] buf = new byte[bufferLength];
			DatagramPacket dp = new DatagramPacket(buf, bufferLength);

			datagramSocket.receive(dp);
			output = new String(dp.getData(), 0, dp.getLength());

		}
		//If no answer was received from server
		//Will return error msg to user
		catch (SocketTimeoutException e) {
			e.printStackTrace();
			output = "Could not communicate with server";
		}
		return output;
	}

	/**
	 * Sends a message to server with Datagrams
	 * @param datagramSocket where message will be sent on
	 * @param host ip-address of the server
	 * @param port that server is listening on
	 * @param msg to be send
	 * @throws IOException if something went wrong
	 */
	private void sendMsg(DatagramSocket datagramSocket, String host, int port, String msg) throws IOException {
		InetAddress ip = InetAddress.getByName(host);
		DatagramPacket dp = new DatagramPacket(msg.getBytes(), msg.length(), ip, port);
		datagramSocket.send(dp);
	}

	/**
	 * Print a message to console, only for debug
	 * @param msg to be written
	 */
	private void printToConsole(String msg){
		System.out.println(msg);
	}


}
