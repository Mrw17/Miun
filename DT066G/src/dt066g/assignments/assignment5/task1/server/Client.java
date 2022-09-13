package dt066g.assignments.assignment5.task1.server;


import dt066g.assignments.assignment5.task1.common.*;

import java.io.IOException;
import java.net.Socket;

/**
 * @author Daniel Westerlund
 * Represent a client on the serverside
 */
public class Client implements Runnable {
    Socket clientSocket;
    Communication communication;
    MathExpression mathExpression;

    /**
     * Constructor that takes a socket
     * @param clientSocket give socket
     */
    public Client(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    /**
     * Returns status of socket connection
     * @return
     */
    private boolean checkIfSocketActive(){
        return clientSocket != null && !clientSocket.isClosed();
    }

    /**
     * Running the client
     * Will read requested command from socket then handle it
     */
    public void run() {
        communication = new Communication(clientSocket);
        String msg;

        try{
            while(checkIfSocketActive()) {
                msg = communication.readString();
                //Continue if nothing was read
                if(msg == null)
                    continue;

                //Handle requested command
                switch (Commands.valueOf(msg)) {
                    case NEW_PROBLEM -> handleNewProblem();
                    case CHECK_ANSWER -> handleCheckAnswer();
                }
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Handles a new problem request by user
     * generate a new MathExpression-object and send it to client
     * @throws IOException if something went wrong
     */
    private void handleNewProblem() throws IOException {
        mathExpression = new MathExpression();
        printMsg(String.format("Generated new problem: %s", mathExpression.toString()));
        communication.sendMessage(mathExpression);
    }

    /**
     * Handles an Check Answer request by user
     * Reads MathExpression from client and updates the object if it was true or false
     * Sends it back to client
     * @throws IOException if something went wrong
     */
    private void handleCheckAnswer() throws IOException {
        mathExpression = communication.readMathExpression();
        printMsg(String.format("Checking if %s = %s", mathExpression.toString(), mathExpression.getUserAnswer()));

        boolean res = checkAnswer();
        printMsg(String.format("%s = %s is %s", mathExpression.toString(), mathExpression.getUserAnswer(), res));
        communication.sendResult(res);
        if(res)
            clientSocket.close();
    }

    /**
     * Checks if user guessed right answer or not
     * @return boolean with true/false on right answer
     */
    private boolean checkAnswer(){
        boolean res = false;

        //Gets generated number
        int num1 = mathExpression.getNumber1();
        int num2 = mathExpression.getNumber2();
        MathExpression.Operator op =  mathExpression.getOperator();

        //Calculates right answer and checks if they are equal
        MathCalculator mathCalculator = new MathCalculator();
        if(mathCalculator.calculateRes(num1,num2,op) == mathExpression.getUserAnswer())
            res = true;

        return res;
    }

    /**
     * Prints out messages that are sent on server-window
     * @param msg to be written
     */
    private void printMsg(String msg){
        System.out.println(msg);
    }


}


