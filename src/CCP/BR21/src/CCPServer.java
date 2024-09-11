import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;


public class CCPServer extends Thread {

    private DatagramSocket socket;
    private byte[] buf;
    private boolean running;

    public CCPServer(DatagramSocket socket, int messageSize) throws SocketException {
       this.socket = socket;
       this.buf = new byte[messageSize];
    }

    public void run() {
        running = true;
        while (running) {
            try {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                
                String jsonPayload = new String(packet.getData(), 0, packet.getLength());

                System.out.println("Received JSON: " + jsonPayload);

                MessagePraser praser = new MessagePraser();
                praser.readJsonMessage(jsonPayload);
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
                running = false; // Stop the server if there's an IO exception
            }
        }
        socket.close();
    }
}