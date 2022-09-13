package dt066g.assignments.assignment5.task1.server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Daniel Westerlund
 * Class that represents the server
 */
public class MathServer extends Thread {
    private static int PORT = 10006;

    /**
     * Runs the server
     * @param args parameters to the program
     */
    public static void main(String[] args) {
        fixPort(args);
        MathServer mathServer = new MathServer();
        try{
            mathServer.start();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * Starts the server, runs "forever"
     */
    @Override
    public void run() {
        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(PORT);
            printMsg(String.format("Server started on port: %s", PORT));
        }
        catch (IOException e){
            e.printStackTrace();
        }

        while(true){
            try{
                if(serverSocket != null){
                    //Waits for client
                    Socket clientSocket = serverSocket.accept();
                    printMsg("New client connected: " + clientSocket.getInetAddress());
                    Client client = new Client(clientSocket);
                    //Starts a client in a new thread
                    new Thread(client).start();
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * Sets port to parameter that was sent to the program
     * or uses default 10006
     * @param args that contains parameters
     */
    private static void fixPort(String[] args){
        try{
            if(args.length > 0)
                PORT = Integer.parseInt(args[0]);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * Outputs a message to console
     * @param msg to be shown in console
     */
    private void printMsg(String msg){
        System.out.println(msg);
    }
}