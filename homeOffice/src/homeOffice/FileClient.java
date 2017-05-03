package homeOffice;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileClient
  extends Thread
{
  private Scanner sc;
  private final Socket skt;
  private DataInputStream dis;
  private FileOutputStream fos;
  private BufferedOutputStream bos;
  private File file;
  private static String fileDest;
  private static String fileName;
  private InputStream is;
  
  public FileClient(Socket socket) throws IOException{
      skt = socket;
      this.initiator();
  }
  
  public void initiator()
    throws IOException
  {
    dis = new DataInputStream(skt.getInputStream());
    fileName = dis.readUTF();
    fileDest = "/home/hafeez/Documents/";
    fileDest += fileName;
    start();
  }
  
  public void run()
  {
    file = new File(fileDest);
    try
    {
      int bytesleft = dis.readInt();
      System.out.println(bytesleft);
      fos = new FileOutputStream(file);
      bos = new BufferedOutputStream(fos);
      byte[] array = new byte[8192];
      is = skt.getInputStream();
      int total = 0;
      while (bytesleft > 0)
      {
        int n = is.read(array, 0, Math.min(bytesleft, array.length));
        System.out.println("Writing file...");
        total += n;
        System.out.println(total);
        bos.write(array, 0, n);
        bytesleft -= n;
      }
      bos.close();
      skt.close();
    }
    catch (IOException ex)
    {
      Logger.getLogger(FileClient.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}