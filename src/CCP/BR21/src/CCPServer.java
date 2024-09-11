import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class CCPServer extends Thread {

    private DatagramSocket socket;
    private InetSocketAddress socketAddress;

    private byte[] buf;
    private boolean running;

    // Create socket address from port number and IP,
    // Create port to listen on socket address,
    // Set message size
    public CCPServer(int portNumber, String address, int messageSize)
            throws SocketException, UnknownHostException {
        this.socketAddress = new InetSocketAddress(InetAddress.getByName(address), portNumber);
        this.socket = new DatagramSocket(this.socketAddress);
        this.buf = new byte[messageSize];
    }

    public void run() {
        running = true;
        System.out.println("SERVER STARTED");
        while (running) {
            try {
                // Recieve DatagramPacket and extract JSON payload
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                System.out.println("PACKAGE RECIEVED");

                // Extract Payload from packet
                String jsonPayload = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received JSON: " + jsonPayload);

                // Pass JSON payload to Message Reader
                MessagePraser.readJsonMessage(jsonPayload);

            } catch (IOException e) {
                e.printStackTrace();
                running = false; // Stop Server
            }
        }
        socket.close();
    }
}