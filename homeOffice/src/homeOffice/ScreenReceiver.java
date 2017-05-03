package homeOffice;

import homeOfficeGUI.SlaveScreenForm;
import java.awt.Graphics;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ScreenReceiver
  extends Thread
{
  ObjectInputStream objectInputStream;
  ImageIcon image;
  Image img;
  
  public ScreenReceiver(ObjectInputStream ois)
  {
    objectInputStream = ois;
    start();
  }
  
  public void run()
  {
      int i=0;
    for (;;)
    {
      try
      {
          image = ((ImageIcon)objectInputStream.readObject());
      }
      catch (IOException ex)
      {
        Logger.getLogger(ScreenReceiver.class.getName()).log(Level.SEVERE, null, ex);
      }
      catch (ClassNotFoundException ex)
      {
        Logger.getLogger(ScreenReceiver.class.getName()).log(Level.SEVERE, null, ex);
      }
      img = image.getImage();
      img = img.getScaledInstance(SlaveScreenForm.jPanel1.getWidth(), SlaveScreenForm.jPanel1.getHeight(), Image.SCALE_FAST);
      Graphics graphics = SlaveScreenForm.jPanel1.getGraphics();
      graphics.drawImage(img, 0, 0, SlaveScreenForm.jPanel1.getWidth(), SlaveScreenForm.jPanel1.getHeight(), SlaveScreenForm.jPanel1);
      System.out.println(i);
      i++;
    }
  }
}
