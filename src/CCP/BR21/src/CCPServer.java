import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class CCPServer extends Thread {

    public static void main(String args) {
        System.out.println("Test RUNNING");
    }

    private DatagramSocket socket;
    private boolean running;
    private byte[] buf = new byte[256];

    public CCPServer() throws SocketException {
        socket = new DatagramSocket(3001);
    }

    public void run() {
        running = true;

        while (running) {
            try {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, address, port);
                String received = new String(packet.getData(), 0, packet.getLength());

                if (received.equals("end")) {
                    running = false;
                    continue;
                }
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
                running = false; // Stop the server if there's an IO exception
            }
        }

        socket.close();
    }
}