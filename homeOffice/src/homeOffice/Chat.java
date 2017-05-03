package homeOffice;

import homeOfficeGUI.ChatForm;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Chat extends Thread{
    private Socket skt;
    private DataInputStream dis;
    private static DataOutputStream dos;
    private ChatForm chat;
    private String reply;
    
    public Chat(Socket socket) throws IOException{
        skt = socket;
        chat = new ChatForm();
        chat.main();
        dis = new DataInputStream(skt.getInputStream());
        dos = new DataOutputStream(skt.getOutputStream());
        start();
    }
    
    public void run(){
        while(true){
            try {
                reply = dis.readUTF();
                chat.jTextArea1.setText(chat.jTextArea1.getText() + '\n' + "Friend: " + reply);
            } catch (IOException ex) {
                Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    public static void write() throws IOException{
        if(ChatForm.jTextField3.getText() != ""){
            ChatForm.jTextArea1.setText(ChatForm.jTextArea1.getText() + '\n' + "Me: " + ChatForm.jTextField3.getText());
            dos.writeUTF(ChatForm.jTextField3.getText());
            dos.flush();
            ChatForm.jTextField3.setText("");
        }
    }
}
