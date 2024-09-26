package communication;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/*
 * Class for sending to a specific port & address
 * Uses MessageBuilder to create a JSON payload and send UDP packet to set destination
 */

public class UDPSender {
    private int port;
    private String ip;

    public UDPSender(int port, String ipAddress) throws SocketException, UnknownHostException {
        this.port = port;
        this.ip = ipAddress;
    }

    public void send(String payload) throws IOException {

        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName(ip);

        System.out.println("SENDING MESSAGE");
        System.out.println(payload);
        byte[] byt = payload.getBytes();
        DatagramPacket packet = new DatagramPacket(byt, byt.length, address, port);
        socket.send(packet);
        socket.close();
    }

}
