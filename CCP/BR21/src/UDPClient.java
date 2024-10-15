import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

final public class UDPClient {
   
    // private InetAddress addr;
    // private  sock;
    

    // public UDPClient() throws SocketException, UnknownHostException {
        
    // }

    static public void send(String msg) throws IOException {
        DatagramSocket sock = new DatagramSocket();
        InetAddress addr = InetAddress.getByName("172.17.16.1");
        byte[] byt = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(byt, byt.length, addr, 2200);
        System.out.println("test");
        sock.send(packet);
    }

    // public void close() { 
    //     sock.close();
    // }
}
