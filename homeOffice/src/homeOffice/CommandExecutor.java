package homeOffice;

import java.awt.Robot;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandExecutor
  extends Thread
{
  private Robot rbt;
  private Socket skt;
  
  CommandExecutor(Robot robot, Socket socket)
  {
    this.rbt = robot;
    this.skt = socket;
    start();
  }
  
  public void run(){
      Scanner scanner = null;
      try {
          scanner = new Scanner(skt.getInputStream());
      } catch (IOException ex) {
          Logger.getLogger(CommandExecutor.class.getName()).log(Level.SEVERE, null, ex);
      }
      while(true){
          int command = scanner.nextInt();
          switch(command){
              case -1:
                  rbt.mousePress(scanner.nextInt());
                  break;
              case -2:
                  rbt.mouseRelease(scanner.nextInt());
                  break;
              case -3:
                  int x = scanner.nextInt();
                  rbt.keyPress(x);
                  rbt.keyRelease(x);
                  break;
              case -4:
                  //rbt.keyRelease(scanner.nextInt());
                  break;
              case -5:
                  rbt.mouseMove(scanner.nextInt(), scanner.nextInt());
                  break;
//              case -6:
//                  System.out.println("mouseClicked");
//                  rbt.mousePress(scanner.nextInt());
//                  rbt.mouseRelease(scanner.nextInt());
//                  break;
//              case -7:
//                  rbt.keyPress(scanner.nextInt());
//                  rbt.keyRelease(scanner.nextInt());
//              case -8:
//                  System.out.println("mouseDoubleClicked");
//                  rbt.mousePress(scanner.nextInt());
//                  rbt.mouseRelease(scanner.nextInt());
//                  rbt.mousePress(scanner.nextInt());
//                  rbt.mouseRelease(scanner.nextInt());
          }
      }
  }
}
