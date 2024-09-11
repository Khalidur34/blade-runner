import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPClient {

    private InetAddress addr;
    private DatagramSocket sock;

    public UDPClient() throws SocketException, UnknownHostException {
        sock = new DatagramSocket();
        addr = InetAddress.getByName("localhost");
    }

    public void send(String msg) throws IOException {
        byte[] byt = msg.getBytes();
        System.out.println(msg);
        DatagramPacket packet = new DatagramPacket(byt, byt.length, addr, 4302);
        sock.send(packet);
    }

    public void close() {
        sock.close();
    }
}
