import java.io.*;
import java.net.*;

public class GameServer {
    private ServerSocket serverSocket; 
    private int numPlayers; 
    private int maxPlayers; 

    //private ServerSideConnection playerOne;
    //private ServerSideConnection playerTwo; 

    private Socket p1Socket; 
    private Socket p2Socket;
    private ReadFromClient p1ReadRunnable; 
    private ReadFromClient p2ReadRunnable; 
    private WriteToClient p1WriteRunnable; 
    private WriteToClient p2WriteRunnable; 

    private GameCanvas gc; 

    private int p1GameState; 
    private int p2GameState; 

    public GameServer() {
        System.out.println("---- GAME SERVER ----");
        numPlayers = 0; 
        maxPlayers = 2; 

        gc = new GameCanvas(numPlayers, 0, 0);

        p1GameState = gc.getCurrentGameState(); // gameCanvas.titleState; 
        System.out.println("Player One is in GameState " + p1GameState);
        p2GameState = gc.getCurrentGameState(); 
        System.out.println("Player Two is in GameState " + p1GameState);

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

                DataInputStream dataIn = new DataInputStream(s.getInputStream());
                DataOutputStream dataOut = new DataOutputStream(s.getOutputStream());
                
                numPlayers++;
                dataOut.writeInt(numPlayers);
                System.out.println("Player #" + numPlayers + " has connected.");
                //ServerSideConnection ssc = new ServerSideConnection(s, numPlayers);

                ReadFromClient rfc = new ReadFromClient(numPlayers, dataIn);
                WriteToClient wtc = new WriteToClient(numPlayers, dataOut);

                if(numPlayers == 1) {
                    p1Socket = s; 
                    p1ReadRunnable = rfc; 
                    p1WriteRunnable = wtc; 
                    
                } else {
                    p2Socket = s; 
                    p2ReadRunnable = rfc; 
                    p2WriteRunnable = wtc; 
                    p1WriteRunnable.sendStartMessage();
                    p2WriteRunnable.sendStartMessage();
                    
                    Thread readThread1 = new Thread(p1ReadRunnable);
                    Thread readThread2 = new Thread(p2ReadRunnable);
                    readThread1.start();
                    readThread2.start();

                    Thread writeThread1 = new Thread(p1WriteRunnable);
                    Thread writeThread2 = new Thread(p2WriteRunnable);
                    writeThread1.start();
                    writeThread2.start();


                }

                /* if(numPlayers == 1) {
                    playerOne = ssc; 
                } else {
                    playerTwo = ssc; 
                } 

                Thread t = new Thread(ssc);
                t.start(); */
            }
            System.out.println("We now have 2 players. No longer accepting connections.");

        } catch(IOException ex) {
            System.out.println("IOException from acceptConnections() GameServer");
        }
    }
  /*   
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
    } */

    private class ReadFromClient implements Runnable {
        private int playerID;
        private DataInputStream dataIn; 
        
        public ReadFromClient(int pid, DataInputStream in) {
            playerID = pid;
            dataIn = in; 
            System.out.println(" RFC" + playerID + " Runnable created");
        }

        @Override
        public void run() {
            try {
                while(true) {
                    if(playerID == 1) {
                        p1GameState = dataIn.readInt();
                        
                    } else {
                        p2GameState = dataIn.readInt();
                    }
                }

            } catch(IOException ex) {
                System.out.println("IOException from RFC run()");
            }
        }
    }

    private class WriteToClient implements Runnable {
        private int playerID;
        private DataOutputStream dataOut; 

        public WriteToClient(int pid, DataOutputStream out) {
            playerID = pid;
            dataOut = out; 
            System.out.println(" WTC" + playerID + " Runnable created");
        }

        @Override
        public void run() {
            try {
                while(true) {
                    if(playerID == 1) {
                        dataOut.writeInt(p2GameState);
                        dataOut.flush();
                    
                    } else {
                        dataOut.writeInt(p1GameState);
                    }

                    try {
                        Thread.sleep(25);
                    } catch(InterruptedException ex) {
                        System.out.println("InterruptedException from WTC run()");
                    }

                }

            } catch(IOException ex) {
                System.out.println("IOException from WTC run()");
            }
        }

        public void sendStartMessage() {
            try {
                dataOut.writeUTF("We now have 2 players.");

            } catch(IOException ex) {
                System.out.println("IOException from sendStartMessage()");
            }
        }
    }

    public static void main(String[] args) {
        GameServer gs = new GameServer();
        gs.acceptConnections();
    }
}