import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class CCPServer extends Thread {

    private DatagramSocket socket;
    // private InetSocketAddress socketAddress;

    private byte[] buf;
    private boolean running;

    public CCPServer(int portNumber,  int messageSize, ArrayList<String> message)
            throws SocketException, UnknownHostException {
        // Create socket address from port number and IP,
        // Create port to listen on socket address,
        // Set message size
        // this.socketAddress = new InetSocketAddress(InetAddress.getByName(address), portNumber);
         this.socket = new DatagramSocket(portNumber);
         this.buf = new byte[messageSize];
    }

    public void run() {
        running = true;
        System.out.println("SERVER STARTED");
        while (running) {
            System.out.println("runs listener thread");
            // try {
            //     DatagramPacket packet = new DatagramPacket(buf, buf.length);
            //     socket.receive(packet);
            //     System.out.println("PACKAGE RECIEVED");

            //     String jsonPayload = new String(packet.getData(), 0, packet.getLength());
            //     System.out.println("Received JSON: " + jsonPayload);


            // } catch (IOException e) {
            //     e.printStackTrace();
            //     running = false;
            // }

        }
        // socket.close();
    }
}