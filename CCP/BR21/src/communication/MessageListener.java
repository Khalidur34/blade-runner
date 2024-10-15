package communication;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.BlockingQueue;

import configuration.Constants;

public class MessageListener implements Runnable {
    private final String address;
    private final int port;

    private final BlockingQueue<String> messageQueue;
    private boolean running;

    public MessageListener(int port, String address, BlockingQueue<String> messageQueue)
            throws SocketException, UnknownHostException {
        this.port = port;
        this.address = address;
        this.messageQueue = messageQueue;
    }

    public void run() {
        System.out.println("SERVER STARTED");
        running = true;
        try {

            InetAddress ipAddress = InetAddress.getByName(address);
            DatagramSocket socket = new DatagramSocket(port, ipAddress);

            while (running) {
                byte[] buffer = new byte[Constants.MAX_MESSAGE_SIZE];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                // Extract Payload from packet
                String payload = new String(packet.getData(), 0, packet.getLength());
                // System.out.println(" Package Received -> Payload: " + payload);
                messageQueue.put(payload);
            }
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
            stopListener();
        }
    }

    public void stopListener() {
        this.running = false;
    }
}