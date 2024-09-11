import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import org.json.simple.JSONObject;

public class testKevin {
    UDPClient client;

    public static void main(String[] args) throws IOException {
        System.out.println("!!! Program Started !!!");
        CCPServer server = new CCPServer(2000, "localhost", 1024);
        server.start();

        SendingData.update("womp", false, 0, "BR20", "that one");
    }

}
