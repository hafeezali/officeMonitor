package robotclient;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.ImageIcon;

/**
 *
 * @author hafeez
 */
public class RobotClient {
    private Socket client;
    private ObjectOutputStream out;
    private final Robot robot;
    private BufferedImage bi;
    private ImageIcon image;
    private final Rectangle rect;
    private int count;
    /**
     *
     * @throws java.awt.AWTException
     */
    public RobotClient() throws AWTException{
        count = 0;
        rect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        robot = new Robot();
    }
    
    public void send(String serverName,int port) throws IOException, InterruptedException{
        System.out.println("Connecting to " + serverName + " on port " + port);
        client = new Socket(serverName, port);
        System.out.println("Just connected to " + client.getRemoteSocketAddress());
        out = new ObjectOutputStream(client.getOutputStream());
        while(true/*count < 20*/){
            bi = robot.createScreenCapture(rect);
            image = new ImageIcon(bi);
            out.writeObject(image);
            out.flush();
            out.reset();
            count = count + 1;
            System.out.println(count);
            //Thread.sleep(50);
        }
        //client.close();
        
    }

    public static void main(String[] args) throws IOException, InterruptedException, AWTException {
        String serverName = args[0];
        int port = Integer.parseInt(args[1]);
        RobotClient obj = new RobotClient();
        obj.send(serverName, port);
    }
}
