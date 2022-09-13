package dt066g.assignments.assignment5.task1.common;

import java.io.*;
import java.net.Socket;

/**
 * @author Daniel Westerlund
 * Class that handles the communication between server and client
 */
public class Communication {
    Socket socket;

    /**
     * Construct that takes a socket that communication will be done on
     * @param socket that communication will be done on
     */
    public Communication(Socket socket){
        this.socket = socket;
    }

    /**
     * Will retrieve MathExpression-object from socket.
     * @return MathExpression-object from socket.
     */
    public MathExpression readMathExpression() {
        if(socket != null && !socket.isInputShutdown()){
            try{
                InputStream inputStream = socket.getInputStream();
                ObjectInputStream objectInputStream  = new ObjectInputStream(inputStream);
                return (MathExpression) objectInputStream.readObject();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * Will retrieve String-object from socket.
     * @return String-object from socket
     */
    public String readString() {
        if(socket != null && !socket.isInputShutdown()){
            try{
                InputStream inputStream = socket.getInputStream();
                ObjectInputStream objectInputStream  = new ObjectInputStream(inputStream);
                return (String) objectInputStream.readObject();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     *  Will retrieve Boolean-object from socket.
     * @return Boolean-object from socket.
     * @throws IOException if something went wrong
     */
    public Boolean readRes() throws IOException, ClassNotFoundException {
        if(socket != null && !socket.isInputShutdown()){
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream  = new ObjectInputStream(inputStream);
            return (Boolean) objectInputStream.readObject();
        }
        return false;
    }


    /**
     * Tries to send a String out on the socket
     * @param msg to be send
     * @throws IOException if something went wrong
     */
    public void sendMessage(String msg) throws IOException {
        try{
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(msg);
        }
        catch(Exception e){
            this.socket.close();
        }
    }


    /**
     * Tries to send a MathExpression out on the socket
     * @param mathExpression to be send
     * @throws IOException if something went wrong
     */
    public void sendMessage(MathExpression mathExpression) throws IOException {
        try{
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(mathExpression);
        }
        catch(Exception e){
            this.socket.close();
        }
    }

    /**
     * Tries to send a Boolean out on the socket
     * @param res to be send
     * @throws IOException if something went wrong
     */
    public void sendResult(boolean res) throws IOException {
        try{
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(res);
        }
        catch(Exception e){
            this.socket.close();
        }
    }
}
