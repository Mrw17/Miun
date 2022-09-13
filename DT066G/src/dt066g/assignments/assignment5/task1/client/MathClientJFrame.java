package dt066g.assignments.assignment5.task1.client;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * @author Daniel Westerlund
 *
 * Class that represent a clients GUI
 */
public class MathClientJFrame extends JFrame {
    private static String HOST = "localhost";
    private static int PORT = 10006;
    MathClient mathClient;
    private static JFrame jFrame;
    private static JPanel jPanelTop;


    /**
     * Running the program
     * @param args parameters to the program, can contains host/port
     */
    public static void main(String[] args) {
        setUpAddress(args);
        SwingUtilities.invokeLater(MathClientJFrame::new);
    }

    /**
     * Constructor, will create GUI
     */
    public MathClientJFrame() {
        createGUI();
        try{
            mathClient = new MathClient(HOST,PORT);
        }
        catch (Exception e){
            showMsgBoxToUser("Could not connect to server", "Connection error");
        }
    }

    /**
     * Creates the GUI
     */
    private void createGUI(){
        createJFrame();
        createJPanel();
        createProblemJTextField();
        createJLabelEqual();
        createAnswerJTextField();
        createButtonNewProblem();
        createButtonCheckProblem();
    }

    /**
     * Creates label that shows =
     */
    private void createJLabelEqual(){
        JLabel jLabel = createJLabel("jlEqual", "=");
        jPanelTop.add(jLabel);
    }

    /**
     * Creates a default JLabel
     * @param name of the JLabel
     * @param text text on JLabel
     * @return a generic JLabel with given data
     */
    private JLabel createJLabel(String name, String text) {
        JLabel jLabel = new JLabel();
        jLabel.setName(name);
        jLabel.setText(text);

        return jLabel;
    }

    /**
     * Creates a button that client can request a new problem to solve
     */
    private void createButtonNewProblem() {
        JButton jButton = createJButton("btnSend", "Get a new problem");

        jButton.addActionListener(e -> {
            NewProblemSwingWorker newProblemSwingWorker = new NewProblemSwingWorker();
            newProblemSwingWorker.execute();
        });

        jPanelTop.add(jButton);
    }

    /**
     * Creates a button that client can request that server checks the answer
     * Disabled by default
     */
    private void createButtonCheckProblem(){
        JButton jButton = createJButton("btnCheck", "Check your answer");
        jButton.setEnabled(false);
        jPanelTop.add(jButton);

        jButton.addActionListener(e -> {
            CheckProblemSwingWorker newCheckProblemSwingWorker = new CheckProblemSwingWorker();
            newCheckProblemSwingWorker.execute();
        });
    }

    /**
     * Creates a default JButton
     * @param name of the JButton
     * @param text of the JButton
     * @return default JButton with given data
     */
    private JButton createJButton(String name, String text){
        JButton jButton = new JButton();
        jButton.setName(name);
        jButton.setText(text);

        return jButton;
    }

    /**
     * Creates a JTextField that shows the problem
     * Doesn't allow editable by default
     */
    private void createProblemJTextField() {
        JTextField jTextField = createJTextField("tfProblem", "0");
        jTextField.setEditable(false);
        jPanelTop.add(jTextField);
    }

    /**
     * Creates a JTextField that allows the user to give a answer
     */
    private void createAnswerJTextField() {
        JTextField jTextField = createJTextField("tfAnswer", "0");
        jPanelTop.add(jTextField);
    }

    /**
     * Creates a default JTextField-object
     * @param name of the JTextField
     * @param text of the JTextField
     * @return a default JTextField with given data
     */
    private JTextField createJTextField(String name, String text){
        JTextField jTextField = new JTextField(10);
        jTextField.setName(name);
        jTextField.setText(text);
        jTextField.setVisible(true);

        return jTextField;
    }

    /**
     * Creates a JFrame
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
     * Creates the top-panel and adds it to the jFrame
     */
    private void createJPanel(){
        jPanelTop = createJPanel(0,0);
        jPanelTop.setName("jPanelTop");
        jFrame.add(jPanelTop, BorderLayout.CENTER);
    }

    /**
     * Creates a default JPanel-object
     * @param width width of the JPanel
     * @param height height of the JPanel
     * @return a default JPanel-object with given data
     */
    private JPanel createJPanel(int width, int height){
        JPanel jPanel = new JPanel();
        jPanel.setVisible(true);
        jPanel.setSize(width,height);
        return jPanel;
    }

    /**
     * Search jPanelTop after a JTextField
     * @param name of the JTextField
     * @return JTextField-object if it was found, else null
     */
    private JTextField getJTextField(String name){
        for (Component c : jPanelTop.getComponents()) {
            if(c.getName().equals(name))
                return (JTextField) c;
        }
        return null;
    }

    /**
     * Search jPanelTop after a JButton
     * @param name of the JButton
     * @return JButton-object if it was found, else null
     */
    private JButton getJButton(String name){
        for (Component c : jPanelTop.getComponents()) {
            if(c.getName().equals(name))
                return (JButton) c;
        }
        return null;
    }

    /**
     * Sets enable-status on a JButton
     * @param name of the button the be changed
     * @param res to be shown of not
     */
    private void setJButtonEnable(String name, boolean res){
        JButton checkAnswer = getJButton(name);
        if(checkAnswer != null)
            checkAnswer.setEnabled(res);
    }


    /**
     * Updates host/port to given parameters or else it used default (localhost:10006)
     * @param args parameters to program
     */
    private static void setUpAddress(String[] args) {
      try{
          if(args.length > 1){
            HOST = args[0];
            PORT = Integer.parseInt(args[1]);
          }
      }
      catch (Exception e){
          HOST = "localhost";
          PORT = 10006;
          e.printStackTrace();
      }
    }

    /**
     * Checks if client is disconnected
     * @return status of connection
     */
    private boolean checkIfDisconnected(){
        return mathClient == null || mathClient.getSocket() == null || mathClient.getSocket().isClosed();
    }

    /**
     * Reconnects to server, if it has been disconnected
     * else it starts a new connection
     */
    private void connectToServer() throws IOException {
        if(this.mathClient != null)
                mathClient.reconnectToServer(HOST,PORT);
            else
                mathClient = new MathClient(HOST,PORT);
    }

    /**
     * Shows a messageDialog to the user
     * @param msg msg to be shown to the user
     * @param title of the MessageDialog-box
     */
    private void showMsgBoxToUser(String msg, String title){
        JOptionPane.showMessageDialog(jFrame,
                msg,
                title,
                JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Worker-class that will request a new problem from the server and
     * updated GUI-with new problem
     */
    private class NewProblemSwingWorker extends SwingWorker<Void, Void> {

        /**
         * Send a request to server to give a new problem
         * @return null
         */
        @Override
        protected Void doInBackground()
        {
            try{
                if(checkIfDisconnected())
                    connectToServer();
                else
                    mathClient.requestNewMathExpression();
            }
            catch (Exception exc){
                showMsgBoxToUser("Connection down, please try again ", "Connection error");
                cancel(true);
            }
            return null;
        }

        /**
         * When new problem was received -> updates GUI
         */
        @Override
        protected void done() {
            JTextField jTextField = getJTextField("tfProblem");
            if(jTextField != null)
                jTextField.setText(mathClient.getMathExpression().toString());

            setJButtonEnable("btnCheck", true);
        }
    }

    /**
     * Worker that checks if a problem was right or wrong
     */
    private class CheckProblemSwingWorker extends SwingWorker<Void, Void> {

        /**
         * Send a request to server to checks users answer
         * @return null
         */
        @Override
        protected Void doInBackground()
        {
            try{
                if(checkIfDisconnected()){
                    connectToServer();
                }
                else {
                    JTextField answerField = getJTextField("tfAnswer");
                    int e = Integer.parseInt(answerField.getText());
                    mathClient.getMathExpression().setUserAnswer(e);
                    mathClient.sendCheckMathExpression();
                }

            }
            catch (NumberFormatException e){
                showMsgBoxToUser("Please only user numbers", "Format error");
                cancel(true);
            }
            catch (Exception exc){
                showMsgBoxToUser("Connection down, please try again ", "Connection error");
                cancel(true);
            }
            return null;
        }

        /**
         * When answer was received -> updates GUI
         */
        @Override
        protected void done() {
            if(isCancelled())
                return;

            String msg = getResultMsg(mathClient.getRes());
            setJButtonEnable("btnCheck", !mathClient.getRes());
            showMsgBoxToUser(msg, "Result");
        }

        /**
         * Gives right result message that will be shown to user
         * @param res if answer was right or wrong
         * @return string with message based on it was right or wrong guess
         */
        private String getResultMsg(boolean res){
            if(res)
                return "Congratulations, you guessed right!";
            else
                return "To bad, you guessed wrong!";
        }
    }
}
