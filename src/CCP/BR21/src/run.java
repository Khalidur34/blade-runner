import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.json.simple.JSONObject;

public class run {
    UDPClient client;
    public static void main (String[] args) throws IOException {
        System.out.println("Hello World!");

       run a = new run();
       a.setup();
        a.whenCanSendAndReceivePacket_thenCorrect();
    }

    public void setup() throws SocketException, UnknownHostException{
        //  CCPServer a = new CCPServer(new DatagramSocket(4302),500);
        //  a.start();

        // client = new UDPClient();
    }

   
    public void whenCanSendAndReceivePacket_thenCorrect() {
        SendingData.update(true,"womp",false,0,"BR20","that one");
    }
}
