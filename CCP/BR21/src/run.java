import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;



public class run {
    
    public  static void main (String[] args) throws IOException{
        
        CCPServer a = new CCPServer(2000,500);
        a.start();
        while (true){
            if(a.messageList.size() != 0){
                IncomingData.sortMessage(a.messageList);
                a.messageList.remove(0);
            } 
        }

        
    }
}
