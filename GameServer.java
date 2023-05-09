import java.io.*;
import java.net.*;

public class GameServer {
    private ServerSocket serverSocket; 
    private int numPlayers; 
    private int maxPlayers; 

    private ServerSideConnection playerOne;
    private ServerSideConnection playerTwo; 

    public GameServer() {
        System.out.println("---- GAME SERVER ----");
        numPlayers = 0; 
        maxPlayers = 2; 

        try {
            serverSocket = new ServerSocket(65000);
        
        } catch(IOException ex) {
            System.out.println("IOException from GameServer constructor");
        }
    }

    public void acceptConnections() {
        try {
            System.out.println("Waiting for connections...");
            while(numPlayers < maxPlayers) {
                Socket s = serverSocket.accept();
                numPlayers++; 
                System.out.println("Player #" + numPlayers + " has connected.");
                ServerSideConnection ssc = new ServerSideConnection(s, numPlayers);

                if(numPlayers == 1) {
                    playerOne = ssc; 
                } else {
                    playerTwo = ssc; 
                }

                Thread t = new Thread(ssc);
                t.start();
            }
            System.out.println("We now have 2 players. No longer accepting connections.");

        } catch(IOException ex) {
            System.out.println("IOException from acceptConnections() GameServer");
        }
    }
    
    private class ServerSideConnection implements Runnable {
        private Socket playerSocket;
        private DataInputStream dataIn; 
        private DataOutputStream dataOut; 
        private int playerID;

        public ServerSideConnection(Socket s, int id) {
            playerSocket = s; 
            playerID = id;

            try {
                dataIn = new DataInputStream(playerSocket.getInputStream());
                dataOut = new DataOutputStream(playerSocket.getOutputStream());

            } catch(IOException ex) {
                System.out.println("IOException from SSC Constructor");
            }
        }


        @Override 
        public void run() {

            try {
                dataOut.writeInt(playerID);
                dataOut.flush();

                while(true) {

                }

            } catch(IOException ex) {
                System.out.println("IOException from run() SSC");
            }
           

        }
    }

    public static void main(String[] args) {
        GameServer gs = new GameServer();
        gs.acceptConnections();
    }
}