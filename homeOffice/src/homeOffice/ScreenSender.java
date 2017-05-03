package homeOffice;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class ScreenSender
  extends Thread
{
  private final Robot rbt;
  private final Socket skt;
  private final Rectangle rect;
  private ObjectOutputStream oos;
  private BufferedImage image;
  private ImageIcon img;
  private Image nextImage;
  private Image currentImage;
  private ImageComparison iCmp;
  
  ScreenSender(Robot robot, Socket socket, Rectangle rectangle)
  {
    rbt = robot;
    skt = socket;
    rect = rectangle;
    start();
  }
  
  public void run()
  {
    try
    {
        iCmp = new ImageComparison();
      oos = new ObjectOutputStream(skt.getOutputStream());
      oos.writeObject(rect);
    }
    catch (IOException ex)
    {
      Logger.getLogger(ScreenSender.class.getName()).log(Level.SEVERE, null, ex);
    }
    int i=0;
    for (;;)
    {
      nextImage = rbt.createScreenCapture(rect);
      if(!iCmp.compareImage(currentImage,nextImage,1)){
          image = (BufferedImage)nextImage;
          img = new ImageIcon(image);
        try
        {
            oos.writeObject(img);
            oos.reset();
            System.out.println(i++);
        } catch (IOException ex) {
              Logger.getLogger(ScreenSender.class.getName()).log(Level.SEVERE, null, ex);
          }
        currentImage = nextImage;
      }
      try {
            Thread.sleep(50);
      } catch (InterruptedException ex) {
            Logger.getLogger(ScreenSender.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
}