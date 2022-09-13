package dt066g.assignments.assignment3.task1;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * @author Daniel Westerlund
 * Program that allows user to write a text and when:
 * user saves it, it will decode the text to robber-language,
 * user loads a text that has been written in robber-language it will decode it
 * and print the output in a textarea
 */
public class RobberEditor extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	static JFrame f;
	static JPanel jPanel = new JPanel();

	/**
	 * Running the program
	 * @param args lite of argument to the program
	 */
	public static void main(String[] args) {
		RobberEditor m = new RobberEditor();
		createMenuBar(m);
		createTextArea();
		setWindowSettings();
	}

	/**
	 * Set window properties
	 */
	private static void setWindowSettings(){
		f.setVisible(true);
		f.setSize(800, 640);
	}

	/**
	 * Creates the text area
	 */
	private static void createTextArea(){
		String defaultText = "texttexttext";
		String textAreaName = "textArea";
		JTextArea jt = new JTextArea(defaultText, 30, 70);
		jt.setName(textAreaName);
		jPanel.add(jt);
		f.add(jPanel);
	}

	/**
	 * Creates a menubar
	 * @param m reference to RobberEditor-object
	 */
	private static void createMenuBar(RobberEditor m) {
		String title = "Yarr!";
		f = new JFrame(title);
		JMenuBar mb = new JMenuBar();
		JMenu x = new JMenu("Menu");
		addMenuItemsToMenu(m, x);
		mb.add(x);
		f.setJMenuBar(mb);
	}

	/**
	 * Adds menu-items to the menu
	 * @param robberEditor reference to robberEditor
	 * @param jMenu reference to jMenu
	 */
	private static void addMenuItemsToMenu(RobberEditor robberEditor, JMenu jMenu){
		jMenu.add(createMenuItem("Open file (transform from robber)",robberEditor));
		jMenu.add(createMenuItem("Save file (transform to robber)",robberEditor));
		jMenu.add(createMenuItem("Clear text",robberEditor));
		jMenu.add(createMenuItem("Exit",robberEditor));
	}

	/**
	 * Creates a JMenuItem and adds listener to robberEditor
	 * @param name of the men
	 * @param robberEditor reference to robberEditor
	 * @return a new jMenuItem with given name
	 */
	private static JMenuItem createMenuItem(String name, RobberEditor robberEditor){
		JMenuItem jMenuItem = new JMenuItem(name);
		jMenuItem.addActionListener(robberEditor);
		return jMenuItem;
	}

	/**
	 * Clears the text area
	 */
	private void clearTextArea(){
		setTextArea("");
	}

	/**
	 * Sets the text area to the given text
	 * @param text that will shown in text area
	 */
	private void setTextArea(String text){
		JTextArea jTextArea = getJTextAreaComponent("textArea");
		jTextArea.setText(text);
	}

	/**
	 * Gets the current from a text area
	 * @param name of the text area
	 * @return current text of the specified text area component
	 */
	private JTextArea getJTextAreaComponent(String name){
		JTextArea jTextArea = new JTextArea();
		//Locates the textarea
		for(Component c : jPanel.getComponents()) {
			if (c.getName().equals(name)) {
				jTextArea = (JTextArea) c;
			}
		}
		return jTextArea;
	}

	/**
	 * Give the user ability to browse to a file that will opened
	 * and decoded and shown in text area
	 */
	private void openFile(){
		JFileChooser fileChooser = setUpFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(jPanel);


		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			try {
				String fileToRead = selectedFile.getAbsolutePath();
				BufferedReader bufferedReader = new BufferedReader(new FileReader(fileToRead));
				RobberReader robberReader = new RobberReader(bufferedReader);
				setTextArea(robberReader.readAll());
				robberReader.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Creates a fileChooser and lets the user select a file to save it too
	 * It will encode the text in text area to robber language and output it the file
	 */
	private void saveFile(){
		try {
			JFileChooser fileChooser = setUpFileChooser();
			int userSelection = fileChooser.showSaveDialog(f);

			if (userSelection == JFileChooser.APPROVE_OPTION) {
				File fileToSave = fileChooser.getSelectedFile();
				String filePath = fileToSave.toString();
				if(!filePath.endsWith(".rov"))
					filePath += ".rov";

				//Writes to file
				FileWriter fw = new FileWriter(filePath);
				RobberWriter robberWriter = new RobberWriter(fw);
				robberWriter.write(getCurrentTextArea());
				robberWriter.flush();
				robberWriter.close();
				showMessageBox("You have successfully saved to file: \n" + filePath);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Shows a message to the user
	 * @param msg the be shown to the user
	 */
	private void showMessageBox(String msg){
		JOptionPane.showMessageDialog(null, msg);
	}

	/**
	 * Sets up a fileChooser with some default settings
	 * Will only allow .rov files
	 * @return a new JFileChooser
	 */
	private JFileChooser setUpFileChooser(){
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);

		fileChooser.addChoosableFileFilter(new FileFilter() {
			public boolean accept(File f) {
				if (f.isDirectory()) {
					return true;
				} else {
					return f.getName().toLowerCase().endsWith(".rov");
				}
			}
			//Files to be accepted
			public String getDescription() {
				return "Robberlanguage (*.rov)";
			}
		});

		return fileChooser;
	}

	/**
	 * Gets the current text from the text area
	 * @return current text from text area
	 */
	private String getCurrentTextArea(){
		JTextArea jTextArea = getJTextAreaComponent("textArea");
		return jTextArea.getText();
	}

	/**
	 * Actions on menu, when a user press the menu item
	 * @param e ActionEvent on menu
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		{
			switch (e.getActionCommand()) {
				case "Open file (transform from robber)" -> openFile();
				case "Save file (transform to robber)" -> saveFile();
				case "Clear text" -> clearTextArea();
				case "Exit" -> System.exit(0);
			}
		}
	}
}