import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class run {
    UDPClient client;

    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");

        run a = new run();
        a.setup();
        a.whenCanSendAndReceivePacket_thenCorrect();
    }

    public void setup() throws SocketException, UnknownHostException {
        CCPServer a = new CCPServer(4302, "localhost", 1024);
        a.start();
    }

    public void whenCanSendAndReceivePacket_thenCorrect() {
        SendingData.update("womp", false, 0, "BR20", "that one");
    }
}
