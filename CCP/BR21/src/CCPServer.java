import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class CCPServer extends Thread {

    private DatagramSocket socket;
    // private InetSocketAddress socketAddress;

    private byte[] buf;
    private boolean running;

    public CCPServer(int portNumber,  int messageSize)
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
            
            try {
                System.out.println("tt");
                // Recieve DatagramPacket and extract JSON payload
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                System.out.println("PACKAGE RECIEVED");

                // Extract Payload from packet
                String jsonPayload = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received JSON: " + jsonPayload);

            } catch (IOException e) {
                e.printStackTrace();
                running = false; // Stop Server
            }
        }
        socket.close();
    }
}