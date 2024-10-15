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
    public volatile ArrayList<String> messageList;

    public CCPServer(int portNumber,  int messageSize)
            throws SocketException, UnknownHostException {
        // Create socket address from port number and IP,
        // Create port to listen on socket address,
        // Set message size
        // this.socketAddress = new InetSocketAddress(InetAddress.getByName(address), portNumber);
        messageList = new ArrayList<String>();
         this.socket = new DatagramSocket(portNumber);
         this.buf = new byte[messageSize];
    } 

    public  void run() {
        running = true;
        System.out.println("SERVER STARTED");
        while (running) {
            System.out.println("runs listener thread");
            try {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                System.out.println("PACKAGE RECIEVED");

                String message = new String(packet.getData(), 0, packet.getLength());
                messageList.add(message);
               // Thread.sleep(1000);
                


            } catch (IOException e) {
                e.printStackTrace();
                running = false;
             } 
             //catch (InterruptedException e) {
            //     // TODO Auto-generated catch block
            //     e.printStackTrace();
            // }

        }
        // socket.close();
    }
}