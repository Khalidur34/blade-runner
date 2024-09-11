import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class CCPServer implements Runnable {

    private DatagramSocket socket;
    private byte[] buf;
    private boolean running;

    public CCPServer(DatagramSocket socket, int messageSize) throws SocketException {
        this.socket = socket;
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

                String jsonPayload = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received JSON: " + jsonPayload);

                // Pass JSON payload to Message Reader
                MessagePraser.readJsonMessage(jsonPayload);
            } catch (IOException e) {
                e.printStackTrace();
                running = false; // Stop the server if there's an IO exception
            }
        }
        socket.close();
    }
}