package dt066g.assignments.assignment5.task1.client;

import dt066g.assignments.assignment5.task1.common.Commands;
import dt066g.assignments.assignment5.task1.common.Communication;
import dt066g.assignments.assignment5.task1.common.MathExpression;

import java.io.IOException;
import java.net.Socket;

/**
 * @author Daniel Westerlund
 * Helper class to MathClientJFrame
 */
public class MathClient {
    Communication communication;
    MathExpression mathExpression;
    boolean res;
    Socket socket;
    String host;
    int port;

    /**
     * Construct that takes a host and port to the server
     * and tries to connect to the server through a socket
     * @param host to the server
     * @param port of the server
     * @throws IOException if could not connect
     */
    public MathClient(String host, int port) throws IOException{
        this.host = host;
        this.port = port;
        socket = new Socket(host,port);
        communication = new Communication(socket);
    }


    /**
     * Send a check_answer-msg followed by the math expression that should be checked
     * Then tries to read a updated math expression
     */
    public void sendCheckMathExpression() throws ClassNotFoundException, IOException {
        Boolean res;
        communication.sendMessage(Commands.CHECK_ANSWER.name());
        communication.sendMessage(this.mathExpression);
        while((res = communication.readRes()) != null){
            this.res = res;
            break;
        }

    }


    /**
     * Send a new problem request to the server
     * Reads the answer, MathExpression
     */
    public void requestNewMathExpression() throws IOException {
        MathExpression newMathExpr;
        if(!isConnected())
            reconnectToServer();

        communication.sendMessage(Commands.NEW_PROBLEM.name());
        while((newMathExpr = communication.readMathExpression()) != null){
            this.mathExpression = newMathExpr;
            break;
        }
    }

    /**
     * Reconnects to the server by creating a new socket-object
     * @throws IOException if it could not connect
     */
    public void reconnectToServer() throws IOException {
        reconnectToServer(host,port);
    }

    /**
     * Reconnects to the server by creating a new socket-object
     * @param host to the server
     * @param port of the server
     * @throws IOException if it could not connect
     */
    public void reconnectToServer(String host, int port) throws IOException {
        socket = new Socket(host,port);
        communication = new Communication(socket);
    }


    public MathExpression getMathExpression() {
        return mathExpression;
    }

    public void setMathExpression(MathExpression mathExpression) {
        this.mathExpression = mathExpression;
    }

    public boolean getRes() {
        return res;
    }

    public void setRes(boolean res) {
        this.res = res;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    private boolean isConnected(){
        return !socket.isConnected();
    }
}
