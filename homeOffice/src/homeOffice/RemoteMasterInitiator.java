package homeOffice;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class RemoteMasterInitiator
{
    private static SlaveHandler slvHandler;
    private static FileServer fileServer;
    private static Chat chat;
    
  public static void initialize(int port,int num)
    throws IOException
  {
    ServerSocket serversocket = new ServerSocket(port);
    Socket socket = serversocket.accept();
    if(num == 1){
        slvHandler = new SlaveHandler(socket);        
    }
    else if(num == 2){
        fileServer = new FileServer(socket);
    }
    else if(num == 3){
        chat = new Chat(socket);
    }
  }
  
  public void portListenerReader(int port, int num)
    throws IOException
  {
      initialize(port,num);
  }
}