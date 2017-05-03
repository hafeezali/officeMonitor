package homeOffice;

import java.awt.Rectangle;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class CommandSender
{
    private Socket socket;
    private static PrintWriter writer;
    private static Rectangle clientScreenDim;
    
  public CommandSender(Rectangle clientScreenDimension, Socket skt) throws IOException{
      socket = skt;
      clientScreenDim = clientScreenDimension;
      writer = new PrintWriter(socket.getOutputStream());
  }
  
  public static void mouseMoved(int x,int y,int width,int height){
      double xScale = clientScreenDim.getWidth()/width;
      double yScale = clientScreenDim.getHeight()/height;
      writer.println(EnumCommands.MOVE_MOUSE.getAbbrev());
      writer.println((int)(x*xScale));
      writer.println((int)(y*yScale));
      writer.flush();
  }
  
  public static void mousePressed(int button){
      System.out.println("mousePressed");
      writer.println(EnumCommands.PRESS_MOUSE.getAbbrev());
      int xButton = 16;
      if(button == 3){
          xButton =4;
      }
      writer.println(xButton);
      writer.flush();
  }
  
  public static void mouseReleased(int button){
      System.out.println("mouseReleased");
      writer.println(EnumCommands.RELEASE_MOUSE.getAbbrev());
      int xButton = 16;
      if(button == 3){
          xButton = 4;
      }
      writer.println(xButton);
      writer.flush();
  }
  
//  public static void mouseClicked(int button){
//      System.out.println("mouseClicked");
//      mousePressed(button);
//      mouseReleased(button);
//  }
//  
//  public static void mouseDoubleClicked(int button){
//      System.out.println("mouseDoubleCLicked");
//      mouseClicked(button);
//      mouseClicked(button);
//  }
  
  public static void keyPressed(int keyCode){
      System.out.println("keyPressed");
      writer.println(EnumCommands.PRESS_KEY.getAbbrev());
      writer.println(keyCode);
      writer.flush();
  }
  
  public static void keyReleased(int keyCode){
      System.out.println("keyReleased");
      writer.println(EnumCommands.RELEASE_KEY.getAbbrev());
      writer.println(keyCode);
      writer.flush();
  }
  
//  public static void keyTyped(int keycode){
//      System.out.println("KeyTyped");
//      writer.println(EnumCommands.PRESS_KEY.getAbbrev());
//      writer.println(EnumCommands.RELEASE_KEY.getAbbrev());
//      writer.flush();
//  }
}
