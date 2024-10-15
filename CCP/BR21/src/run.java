import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;



public class run {
    
    public static void main (String[] args) throws IOException {
        ArrayList<String> messages = new ArrayList<String>();
        CCPServer a = new CCPServer(2000,500,messages);
        a.start();
        whenCanSendAndReceivePacket_thenCorrect();
        
        while (true){
            if(messages.size() != 0){
                
            }

        }

        
    }

    // static public void setup() throws SocketException, UnknownHostException{
        
    //     
    // }

   
   static public void whenCanSendAndReceivePacket_thenCorrect() {
        SendingData.update("womp",false,0,"BR20","that one");
    }
}
