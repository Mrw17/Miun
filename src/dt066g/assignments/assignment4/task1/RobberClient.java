package dt066g.assignments.assignment4.task1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Handles communication between server/client
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
		Socket socket = null;
		String answer = "";
		try {
			socket = new Socket(host,port);
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			socket.setSoTimeout(10000);
			dos.writeUTF(msg);
			answer = (String) dis.readUTF();

			//closing
			dos.close();
			dis.close();
			socket.close();

		}
		//Returns error msg if exception is caught
		catch (IOException e) {
			e.printStackTrace();
			answer = e.getMessage();
		}
		return answer;
	}
}
