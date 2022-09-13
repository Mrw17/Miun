package dt066g.assignments.assignment4.task2;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Program that allows the user to specifies settings for a captcha
 * Then download it and shows it the user
 * @author Daniel Westerlund
 */
public class Captcha extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final String BASE_URL = "https://dt066g.programvaruteknik.nu/captcha.php";
	private static JFrame jFrame;
	private static JPanel jPanelBottom;
	private static JPanel jPanelTop;

	/**
	 * Default constructor, will create the GUI
	 */
	public Captcha() {
		createGUI();
	}

	/**
	 * Creats whole GUI
	 */
	private void createGUI(){
		createJFrame();
		createJPanelTop();
		createJPanelBottom();
		createNumOfChars();
		createSize();
		createRandom();
		createGenerate();
		createCaptchaLbl();
	}

	/**
	 * Creates JFrame 1024x768
	 * Will use borderLayout for components
	 */
	private void createJFrame(){
		jFrame = new JFrame();
		jFrame.setSize(1024,768);
		jFrame.setResizable(false);
		jFrame.setVisible(true);
		BorderLayout borderLayout = new BorderLayout();
		jFrame.setLayout(borderLayout);
	}

	/**
	 * Creates the top-panel 800x168
	 */
	private void createJPanelTop(){
		jPanelTop = createJPanel(800,168);
		jPanelTop.setName("jPanelTop");
		jFrame.add(jPanelTop, BorderLayout.NORTH);
	}

	/**
	 * Creates bottom-panel 800x500
	 */
	private void createJPanelBottom(){
		jPanelBottom = createJPanel(800,500);
		jPanelBottom.setSize(0,0);
		jPanelBottom.setName("jPanelBottom");
		jFrame.add(jPanelBottom, BorderLayout.CENTER);
	}

	/**
	 * Creates a default JPanel-object
	 * @param width of the JPanel-object
	 * @param height of the JPanel-object
	 * @return a new JPanel-object
	 */
	private JPanel createJPanel(int width, int height){
		JPanel jPanel = new JPanel();
		jPanel.setVisible(true);
		jPanel.setSize(width,height);
		return jPanel;
	}

	/**
	 * Creates a JLabel and JCombBox for user to select number of chars
	 */
	private void createNumOfChars(){
		JLabel jLabel = createJLabel("Chars: ", SwingConstants.LEFT);
		jPanelTop.add(jLabel);

		String[] choices = generateIndexedStringArr(1,20);
		JComboBox<String> jComboBox = createJComboBox("CBNumOfChars", choices);
		jPanelTop.add(jComboBox);
	}

	/**
	 * Creates a JLabel and JCombBox for user to select size of the chars
	 */
	private void createSize(){
		JLabel jLabel = createJLabel("Size: ", SwingConstants.LEFT);
		jPanelTop.add(jLabel);
		String[] choices = generateIndexedStringArr(10,141);
		JComboBox<String> jComboBox = createJComboBox("CBSize", choices);

		jPanelTop.add(jComboBox);
	}

	/**
	 * Creates a default JCombobox
	 * @param name of the JCombobox
	 * @param choices that user can choice between
	 * @return a JCombobox with desired options
	 */
	private JComboBox<String> createJComboBox(String name, String[] choices){
		JComboBox<String> jComboBox = new JComboBox<>(choices);
		jComboBox.setVisible(true);
		jComboBox.setName(name);
		jComboBox.setSelectedIndex(choices.length/2);
		return jComboBox;
	}

	/**
	 * Creates a default JLabel
	 * @param text that will be shown to user
	 * @param spot preferred alignment
	 * @return JLabel-object
	 */
	private JLabel createJLabel(String text, int spot){
		JLabel jLabel = new JLabel();
		jLabel.setVisible(true);
		jLabel.setText(text);
		jLabel.setHorizontalAlignment(spot);
		return jLabel;
	}

	/**
	 * Generated a string[] with integers
	 * @param from start integer
	 * @param size total number of integers
	 * @return String[] with integers
	 */
	private String[] generateIndexedStringArr(int from, int size){
		String[] strArr = new String[size];
		for(int i = 0; i < size; i++)
			strArr[i] = Integer.toString(i+from);

		return strArr;
	}

	/**
	 * Create JLabel and JCheckBox that user can check if Captcha should be random generated
	 */
 	private void createRandom(){
		JLabel jLabel = createJLabel("Random: ", SwingConstants.CENTER);
		jPanelTop.add(jLabel);

		JCheckBox jCheckBox = createJCheckBox("cbRandom");
		jPanelTop.add(jCheckBox);
	}

	/**
	 * Creates a default JCheckBox
	 * @param name of the CheckBox
	 * @return a new JCheckBox with preferred name
	 */
	private JCheckBox createJCheckBox(String name){
		JCheckBox jCheckBox = new JCheckBox();
		jCheckBox.setName(name);
		jCheckBox.setVisible(true);

		return jCheckBox;
	}

	/**
	 * Creates JButton that allows user to selected when it wants to generate captcha image
	 * Will invoke generateCaptchaSwingWorker when pressed
	 */
	private void createGenerate(){
		JButton jButton = createJButton("Generate");

		jButton.addActionListener(e -> {
			GenerateCaptchaSwingWorker generateCaptchaSwingWorker = new GenerateCaptchaSwingWorker(fixUrl());
			generateCaptchaSwingWorker.execute();
		});
		jPanelTop.add(jButton);
	}

	/**
	 * Creates a default JButton-object
	 * @param text that will be shown to the user
	 * @return a JButton-object
	 */
	private JButton createJButton(String text){
		JButton jButton = new JButton();
		jButton.setText(text);
		jButton.setVisible(true);

		return jButton;
	}

	/**
	 * Creates Captcha-Label that will show the captcha-image to the user
	 */
	private void createCaptchaLbl(){
		JLabel jLabel = createJLabel("", SwingConstants.CENTER);
		jLabel.setName("lblCaptcha");
		jPanelBottom.add(jLabel);
	}

	/**
	 * Gets a specific JLabel from JPanelBottom
	 * @param name of the wanted JLabel
	 * @return JLabel if it could be found or null
	 */
	private JLabel getJLabelFromJPanelBottom(String name){
		for(Component c : jPanelBottom.getComponents()) {
			if (c.getName() != null && c.getName().equals("lblCaptcha")) {
				return (JLabel) c;
			}
		}
		return null;
	}

	/**
	 * Fix the url based on users preferred settings
	 * @return url to website with preffered settings
	 */
	private String fixUrl(){
		String output = BASE_URL;
		JCheckBox cbRandom = getJCheckBox("cbRandom");

		try{
			// User wants to specify own options
			if(!cbRandom.isSelected()) {
				//Number of chars
				String temp = (String) getAJComboBox("CBNumOfChars").getSelectedItem();
				int numOfChars = Integer.parseInt(temp);

				// Size of chars
				temp = (String) getAJComboBox("CBSize").getSelectedItem();
				int size = Integer.parseInt(temp);;

				//Adds options to base url
				output += String.format("?numChars=%s&charSize=%s", numOfChars, size);
			}

			// User wants random
			else{
				// Adds options to base url
				output += ("?random=true");
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return output;
	}

	/**
	 * Gets a specific JCheckBox from jPanelTop
	 * @param name of the JCheckBox
	 * @return JCheckBox if it could be found or else it will return null
	 */
	private JCheckBox getJCheckBox(String name){
		JCheckBox cb = null;
		for(Component c : jPanelTop.getComponents()){
			if(c.getName() != null && c.getName().equals(name)){
				cb = (JCheckBox) c;
			}
		}
		return cb;
	}

	/**
	 * Gets a specific JComboBox from jPanelTop
	 * @param name of the JComboBox
	 * @return JComboBox if it could be found or else it will return null
	 */
	private JComboBox getAJComboBox(String name){
		JComboBox cb = null;
		for(Component c : jPanelTop.getComponents()){
			if(c.getName() != null && c.getName().equals(name)){
				cb = (JComboBox<String>) c;
			}
		}
		return cb;
	}

	/**
	 * SwingWorker class that will download captcha and update GUI
	 */
	private class GenerateCaptchaSwingWorker extends SwingWorker<Void, String> {
		private final String url;
		String saveImgPath = System.getProperty("user.dir") + "/src/assets/assignment4/captcha.jpg";

		/**
		 * Constructor that takes a specific url that will be used for downloading captcha
		 * @param url to captcha
		 */
		public GenerateCaptchaSwingWorker(String url) {
			this.url = url;
		}

		/**
		 * Will download the image in the background to saveImgPath
		 * @return null
		 */
		@Override
		protected Void doInBackground() {
			try {
				URL website = new URL(url);

				BufferedImage image = ImageIO.read(website);
				File file = new File(saveImgPath);
				ImageIO.write(image,"jpg", file);

			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		/**
		 * Updates GUI when downloading is done
		 */
		@Override
		protected void done() {
			JLabel jLabel = getJLabelFromJPanelBottom("lblCaptcha");

			//Fix cache problem when download image with same name, so we can update GUI with new img
			Image img = Toolkit.getDefaultToolkit().createImage(saveImgPath);

			//Fix size of the image
			img = img.getScaledInstance(800, 400,  java.awt.Image.SCALE_SMOOTH);

			ImageIcon icon = new ImageIcon(img);
			jLabel.setIcon(icon);
		}
	};

	/**
	 * Running the program
	 * @param args parameters to the program
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(Captcha::new);
	}
}