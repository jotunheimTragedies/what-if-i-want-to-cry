import java.net.*;
import java.io.*;

public class Player {
    private Socket clientSocket; 
    private DataInputStream dataIn; 
    private DataOutputStream dataOut; 
    private GameFrame gf; 

    private int playerID;
    private int otherPlayerID;
    private ClientSideConnection csc;  

    public Player() {
        System.out.println("---- CLIENT ----");

        gf = new GameFrame(playerID, 1920, 1080);
        connectToServer();
        gf.setUpGUI();
        gf.changetoRunningState();
        gf.changetoOverState();
    }

    private class ClientSideConnection {
        private Socket clientSocket; 
        private DataInputStream dataIn; 
        private DataOutputStream dataOut; 

        public ClientSideConnection() {
            try {
                clientSocket = new Socket("localhost", 65000);
                dataIn = new DataInputStream(clientSocket.getInputStream());
                dataOut = new DataOutputStream(clientSocket.getOutputStream());

                playerID = dataIn.readInt();
                gf.playerID = playerID; 
                System.out.println("Connected to server as Player #" + playerID + ".");

            } catch(IOException ex) {
                System.out.println("IOException from CSC Constructor");
            }
        }
    }

    public void connectToServer() {
        csc = new ClientSideConnection(); 
    }
}
