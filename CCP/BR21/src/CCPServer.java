import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class CCPServer extends Thread {

    private final int MESSAGE_SIZE = 512;
    private int portNumber;
    private DatagramSocket socket;
    private InetSocketAddress socketAddress;

    private byte[] buf;
    private boolean running;

    // Create socket address from port number and IP,
    // Create port to listen on socket address,
    // Set message size
    public CCPServer(int portNumber, String address)
            throws SocketException, UnknownHostException {
        this.portNumber = portNumber;
        this.socketAddress = new InetSocketAddress(InetAddress.getByName(address), portNumber);
        this.socket = new DatagramSocket(this.socketAddress);
        this.buf = new byte[MESSAGE_SIZE];
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

                if (this.portNumber == 3000) {
                    JsonReader.readMasterMessage(jsonPayload);
                } else if (this.portNumber == 4000) {
                    JsonReader.readArduinoMessage(jsonPayload);
                } else {
                    System.out.println("Message from unknown port");
                }
            } catch (IOException e) {
                e.printStackTrace();
                running = false; // Stop Server
            }
        }
        socket.close();
    }

}