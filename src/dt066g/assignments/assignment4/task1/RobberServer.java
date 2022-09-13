package dt066g.assignments.assignment4.task1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


/**
 * Server that will take a text and translate it robberlanguage
 * and then send it back
 */
public class RobberServer {
	private static int PORT = 0;


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

		printMsg("Server started on port " + PORT);
		while(true){
			DatagramSocket datagramSocket = new DatagramSocket(PORT);

			//Read message from client
			DatagramSettings datagramSettings = readMsg(datagramSocket);
			sendAnswer(datagramSocket, datagramSettings);

			datagramSocket.close();
		}

	}

	/**
	 * Will read DatagramPackets
	 * @param datagramSocket that message will be sent on
	 * @return DatagramPacketSettings-object that carries data thad is needed for receiving data
	 * @throws IOException if something went wrong
	 */
	private DatagramSettings readMsg(DatagramSocket datagramSocket) throws IOException {
		DatagramSettings datagramSettings = new DatagramSettings();
		int bufferSize = 1024;
		byte[] buffer = new byte[bufferSize];
		//Reads msg
		DatagramPacket dp = new DatagramPacket(buffer, bufferSize);
		datagramSocket.receive(dp);

		//Saves information that is needed
		datagramSettings.setInetAddress(dp.getAddress());
		datagramSettings.setPort(dp.getPort());
		datagramSettings.setMsg(new String(dp.getData(), 0, dp.getLength()));

		printMsg(String.format("Message from client on %s:%s : %s", datagramSettings.getInetAddress(), datagramSettings.getPort(), datagramSettings.getMsg()));
		return datagramSettings;
	}

	/**
	 * Sends a answer-message to the client
	 * @param datagramSocket that message will be put on
	 * @param datagramSettings settings that is needed for communication
	 * @throws IOException if something went wrong
	 */
	private void sendAnswer(DatagramSocket datagramSocket, DatagramSettings datagramSettings) throws IOException {
		//Translate answer
		String answer = generateAnswer(datagramSettings.getMsg());

		//Sends answer
		DatagramPacket datagramPacket = new DatagramPacket(answer.getBytes(),
				answer.length(), datagramSettings.getInetAddress(), datagramSettings.getPort());
		datagramSocket.send(datagramPacket);

		printMsg(String.format("Message to client on %s:%s : %s", datagramSettings.getInetAddress(), datagramSettings.getPort(), datagramSettings.getMsg()));

	}

	/**
	 * Generate a answer to the client by translating
	 * message from client to robber-language
	 * @param msg to be translated
	 * @return message that has been translated to robber-language
	 */
	private String generateAnswer(String msg){
		return dt066g.assignments.assignment3.task1.RobberLanguage.toRobber(msg);
	}



	/**
	 * Prints a msg to console
	 * @param msg to be shown to user
	 */
	private void printMsg(String msg){
		System.out.println(msg);
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
	 * Private class that's used to encapsulate DatagramPacket settings
	 * to simplify the handle of settings.
	 */
	private static class DatagramSettings {
		private String msg = "";
		private InetAddress inetAddress;
		private int port;

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public InetAddress getInetAddress() {
			return inetAddress;
		}

		public void setInetAddress(InetAddress inetAddress) {
			this.inetAddress = inetAddress;
		}

		public int getPort() {
			return port;
		}

		public void setPort(int port) {
			this.port = port;
		}
	}
}