package dt066g.assignments.assignment7.task1;


import javax.swing.*;
import java.awt.*;

/**
 * GUI For RobberClient that allows the user to write in a text
 * that will be send to a server that translate it to robberlanguage
 * and sends it back to the client
 */
public class RobberClientJFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_PORT = 10001;
	private static final String DEFAULT_HOST_NAME = "127.0.0.1";
	private static int PORT = 0;
	private static String HOST = "";
	private static JTextArea userTextArea;
	private static JTextArea fromServerTextArea;
	private static final JPanel jPanel = new JPanel();
	private final RobberClient robberClient;

	/**
	 * Running the program on host/port that was send as parameters or using default values
	 * @param args with host/port
	 */
	public static void main(String[] args) {
		try{
			//checks if host/port was send to the program, else it used default values
			if(args.length > 1) {
				HOST = args[0];
				PORT = Integer.parseInt(args[1]);
			}
			else{
				HOST = DEFAULT_HOST_NAME;
				PORT = DEFAULT_PORT;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		SwingUtilities.invokeLater(() -> new RobberClientJFrame(HOST,PORT));
	}


	/**
	 * Constructor, will create GUI
	 */
	public RobberClientJFrame(String host, int port) {
		HOST = host;
		PORT = port;
		robberClient = new RobberClient(PORT);
		createGUI();
	}

	/**
	 * Creates the GUI
	 */
	private void createGUI(){
		createJFrame();
		createJPanel();
		createJTextAreas();
		createSendButton();
	}

	/**
	 * Will create a send button
	 * When a user press it, it will invoke SwingWorker and
	 * translate the text that a user has put in trough a server
	 */
	private void createSendButton(){
		JButton jButton = new JButton();
		jButton.setVisible(true);
		jButton.setName("translate");
		jButton.setText("Translate");
		jButton.setAlignmentX(20);

		//User press button
		jButton.addActionListener(e -> {
			SendSwingWorker sendSwingWorker = new SendSwingWorker();
			sendSwingWorker.execute();
		});
		jPanel.add(jButton);
	}

	/**
	 * Creates a JPanel that holds a JFrame
	 */
	private void createJPanel(){
		jPanel.setSize(800,640);
		jPanel.setVisible(true);
	}

	/**
	 * Creates a JFrame that holds all elements
	 */
	private void createJFrame(){
		JFrame jFrame = new JFrame();
		jFrame.setVisible(true);
		jFrame.setSize(800, 640);
		jFrame.add(jPanel);
	}

	/**
	 * Search jPanelTop after a JButton
	 * @param name of the JButton
	 * @return JButton-object if it was found, else null
	 */
	private JButton getJButton(String name){
		for (Component c : jPanel.getComponents()) {
			if(c.getName() != null && c.getName().equals(name))
				return (JButton) c;
		}
		return null;
	}

	/**
	 * Creates both user and received TextArea
	 */
	private void createJTextAreas(){
		userTextArea = new JTextArea();
		userTextArea.setVisible(true);
		userTextArea.setRows(30);
		userTextArea.setColumns(30);
		userTextArea.setMaximumSize(new Dimension(30,30));
		userTextArea.setSize(400,320);
		JScrollPane scrollPane = new JScrollPane( userTextArea );
		jPanel.add(scrollPane);

		fromServerTextArea = new JTextArea();
		fromServerTextArea.setVisible(true);
		fromServerTextArea.setRows(30);
		fromServerTextArea.setColumns(30);
		fromServerTextArea.setMaximumSize(new Dimension(30,30));
		fromServerTextArea.setEnabled(false);
		fromServerTextArea.setDisabledTextColor(new Color(255,0,0));
		scrollPane = new JScrollPane( fromServerTextArea );
		scrollPane.setName("ScrollEncodedText");
		jPanel.add(scrollPane);
	}

	/**
	 * SwingWorker class that will handle communication between server/client in the background
	 * Updates GUI when result has been received
	 */
	private class SendSwingWorker extends SwingWorker<Void, Void> { // you are free to change data type for the Type Parameters
		private String toRobber;

		/**
		 * Send userText to server and saves received text (that has been transladed)
		 * @return null
		 */
		@Override
		protected Void doInBackground()
		{
			try{
				JButton translate = getJButton("translate");
				translate.setEnabled(false);
				String msg = userTextArea.getText();
				toRobber = robberClient.sendToRobber(msg);
			}
			catch (Exception exc){
				exc.printStackTrace();
			}
			return null;
		}

		/**
		 * Updated GUI with translated text
		 */
		@Override
		protected void done() {
			JButton translate = getJButton("translate");
			translate.setEnabled(true);
			fromServerTextArea.setText(toRobber);
		}
	};
}