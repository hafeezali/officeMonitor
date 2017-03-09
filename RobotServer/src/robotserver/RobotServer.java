package robotserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author hafeez
 */
public class RobotServer extends Thread{
    private final ServerSocket serverSocket;
    private Socket server;
    private ObjectInputStream in;
    private ImageIcon image;
    private int count;
    
    public RobotServer(int port) throws IOException{
        this.count = 0;
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(10000);
    }
    
    /**
     *
     */
    @Override
    public void run(){
        System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
        try{
            server = serverSocket.accept();
            System.out.println("Just connected to " + server.getRemoteSocketAddress());
            in = new ObjectInputStream(server.getInputStream());
        }catch (IOException ex) {
            Logger.getLogger(RobotServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(true /*count < 20*/){
            try {
                image = (ImageIcon) in.readObject();
                //count = count + 1;
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(RobotServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        /*try {
            server.close();
        } catch (IOException ex) {
            Logger.getLogger(RobotServer.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    
    public static void main(String[] args) throws IOException {
        int port = Integer.parseInt(args[0]);
        Thread t = new RobotServer(port);
        t.start();
    }
    
}
