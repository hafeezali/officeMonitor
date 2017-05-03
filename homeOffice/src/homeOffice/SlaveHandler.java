package homeOffice;

import java.awt.Rectangle;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SlaveHandler
{
  private Socket skt;
  private Rectangle clientScreenDimension;
  private ObjectInputStream ois;
  
  SlaveHandler(Socket socket) throws IOException
  {
    this.skt = socket;
    handle();
  }
  
  public void handle() throws IOException
  {
    try
    {
      this.clientScreenDimension = new Rectangle();
      this.ois = new ObjectInputStream(this.skt.getInputStream());
      this.clientScreenDimension = ((Rectangle)this.ois.readObject());
    }
    catch (IOException ex)
    {
      Logger.getLogger(SlaveHandler.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (ClassNotFoundException ex)
    {
      Logger.getLogger(SlaveHandler.class.getName()).log(Level.SEVERE, null, ex);
    }
    new CommandSender(this.clientScreenDimension, this.skt);
    new ScreenReceiver(this.ois);
  }
}