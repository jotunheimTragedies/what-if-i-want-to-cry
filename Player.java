import java.net.*;
import java.io.*;

public class Player {
    private Socket clientSocket; 
    private DataInputStream dataIn; 
    private DataOutputStream dataOut; 
    private GameFrame gf; 

    private int playerID;
    private int otherPlayerID;
    //private ClientSideConnection csc;  

    private ReadFromServer rfsRunnable;
    private WriteToServer wtsRunnable; 

    private GameCanvas mePlayer; 
    private GameCanvas companionPlayer; 
    
    //private PlayerSprite me; 
    //private PlayerSprite enemy; 

    public Player() {
        System.out.println("---- CLIENT ----");

        mePlayer = new GameCanvas(0, 0, 0);
        companionPlayer = new GameCanvas(1, 0, 0);

        gf = new GameFrame(playerID, 1920, 1080);
        connectToServer();
        createCanvas();
        gf.setUpGUI();
        gf.changetoRunningState();
        gf.changetoOverState();

    }

    private void connectToServer() {
        try {
            clientSocket = new Socket("localhost", 65000);
            DataInputStream dataIn = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dataOut = new DataOutputStream(clientSocket.getOutputStream());
            
            playerID = dataIn.readInt();
            gf.playerID = playerID;
            System.out.println("Connected to server as Player #" + playerID + ".");

            if(playerID == 1) {
                System.out.println("Waiting for Player #2 to connect...");
            }
            rfsRunnable = new ReadFromServer(dataIn);
            wtsRunnable = new WriteToServer(dataOut);
            rfsRunnable.waitForStartMessage();

        } catch(IOException ex) {
            System.out.println("IOException from connectToServer()");
        }
    }

    private void createCanvas() { // What if i create a new GameCanvas per person?
        if(playerID == 1) {
            //mePlayer.setGameState(0);
            //companionPlayer.setGameState(0);

            mePlayer = new GameCanvas(0, 1920, 1080);
            companionPlayer = new GameCanvas(1, 1920, 1080);

        } else {
            // companionPlayer.setGameState(0);
            // mePlayer.setGameState(0);

            companionPlayer = new GameCanvas(0, 1920, 1080);
            mePlayer = new GameCanvas(1, 1920, 1080);
        }
    }

    private class ReadFromServer implements Runnable {
        private DataInputStream dataIn;

        public ReadFromServer(DataInputStream in) {
            dataIn = in; 
            System.out.println(" RFS Runnable created");

        }

        @Override
        public void run() {
            try {
                while(true) {
                    //int companionState = dataIn.readInt();

                    if(companionPlayer != null) {
                        companionPlayer.setGameState(dataIn.readInt());
                        //companionPlayer.setGameState(companionState);
                    }
                    
                }   
            } catch(IOException ex) {
                System.out.println("IOException from RFS run()");
            }
        }

        public void waitForStartMessage() {
            try {
                String startMessage = dataIn.readUTF();
                System.out.println("Message from server: " + startMessage);

                Thread readThread = new Thread(rfsRunnable);
                Thread writeThread = new Thread(wtsRunnable);
                readThread.start();
                writeThread.start();

            } catch(IOException ex) {
                System.out.println("IOException from waitForStartMessage()");
            }
        }
    }

    private class WriteToServer implements Runnable {
        private DataOutputStream dataOut; 

        public WriteToServer(DataOutputStream out) {
            dataOut = out; 
            System.out.println(" WTS Runnable created");
        }
        
        @Override
        public void run() {
            try {
                while(true) {
                    dataOut.writeInt(mePlayer.getCurrentGameState());
                    System.out.println("mePlayer is in Game State " + mePlayer.getCurrentGameState() );
                    dataOut.flush();

                    /* int companionState = dataIn.readInt();

                    if(companionPlayer != null) {
                        companionPlayer.setGameState(companionState);
                        System.out.println("Companion Player is in Game State " + companionState);
                        // dataOut.writeInt(mePlayer.getCurrentGameState());
                        // dataOut.flush();
                        // dataOut.writeInt(mePlayer.getCurrentGameState()); // me.getX()
                        // dataOut.flush();
                    } */
                    
                    try {
                        Thread.sleep(25);

                    } catch(InterruptedException ex) {
                        System.out.println("InterruptedException from WTS run()");
                    }
                }


            } catch(IOException ex) {
                System.out.println("IOException from  WTS run()");
            }
        }
    }
}
