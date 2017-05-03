package homeOffice;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.Socket;

public class RemoteSlaveInitiator
{
  private static Socket socket;
  private static Rectangle rect;
  private static Robot robot;
  private static ScreenSender scrSender;
  private static CommandExecutor commExec;
  private static FileClient fileClient;
  private static Chat chat;

  
  public static void initialize(String server, int port,int num)
    throws IOException, AWTException
  {
    socket = new Socket(server, port);
      switch (num) {
          case 1:
              GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
              GraphicsDevice gDev = gEnv.getDefaultScreenDevice();
              Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
              rect = new Rectangle(dim);
              robot = new Robot(gDev);
              scrSender = new ScreenSender(robot, socket, rect);
              commExec = new CommandExecutor(robot, socket);
              break;
          case 2:
              fileClient = new FileClient(socket);
              break;
          case 3:
              chat = new Chat(socket);
              break;
          default:
              break;
      }
  }
  
  public void Connector(String server, int port, int num)
    throws IOException, AWTException
  {
      initialize(server, port, num);
  }
}