package homeOffice;

import homeOfficeGUI.FileChooser;
import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileServer
  extends Thread
{
  private static Socket skt;
  public static String fileToSend;
  public static String fileName;
  private File file;
  private FileInputStream fis;
  private BufferedInputStream bis;
  private OutputStream os;
  private DataOutputStream dos;
  
  public FileServer(Socket socket){
      skt = socket;
      FileChooser.main(this);
  }
  
  public void initiator()
    throws IOException
  {
    start();
  }
  
  public void run()
  {
    file = new File(fileToSend);
    byte[] array = new byte[8192];
    try
    {
      fis = new FileInputStream(file);
      dos = new DataOutputStream(skt.getOutputStream());
      dos.writeUTF(fileName);
      dos.flush();
      Thread.sleep(100);
      dos.writeInt((int)file.length());
      System.out.println((int)file.length());
      dos.flush();
      Thread.sleep(100);
      bis = new BufferedInputStream(fis);
      os = skt.getOutputStream();
      int total = 0;
      int n;
      while ((n = bis.read(array)) > 0)
      {
        os.write(array, 0, n);
        System.out.println("Sending file...");
        total += n;
        System.out.println(total);
      }
      os.flush();
      skt.close();
    }
    catch (IOException ex)
    {
      Logger.getLogger(FileServer.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (InterruptedException ex)
    {
      Logger.getLogger(FileServer.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}