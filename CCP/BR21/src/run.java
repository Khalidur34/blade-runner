import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;



public class run {
    UDPClient client;
    public static void main (String[] args) throws IOException {
        System.out.println("Hello World!");
        
        CCPServer a = new CCPServer(2100,500);
        a.run();
        whenCanSendAndReceivePacket_thenCorrect();
        
        
    }

    // static public void setup() throws SocketException, UnknownHostException{
        
    //     
    // }

   
   static public void whenCanSendAndReceivePacket_thenCorrect() {
        SendingData.update("womp",false,0,"BR20","that one");
    }
}