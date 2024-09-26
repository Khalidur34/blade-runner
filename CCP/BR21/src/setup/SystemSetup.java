package setup;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

import org.json.simple.JSONObject;

import utility.JSONBuilder;
import utility.JSONReader;

public class SystemSetup {

    private static final int TIMEOUT_MS = 5000;

    public static boolean checkPortConnection(int port, String ip) {
        try (DatagramSocket socket = new DatagramSocket(port)) {
            InetAddress address = InetAddress.getByName(ip);

            // Build JSON CCIN Object and convert to bytes
            JSONBuilder json = new JSONBuilder();
            json = json.addCommand("CCIN");
            byte[] buffer = json.toString().getBytes();

            // Send packet and set timeout
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
            socket.send(packet);
            socket.setSoTimeout(TIMEOUT_MS);

            // recieve packet
            buffer = new byte[1024];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            socket.receive(reply);

            // extract and check payload
            String replyPayload = new String(reply.getData(), 0, reply.getLength());
            JSONObject replyJSON = JSONReader.getJson(replyPayload);
            String message = (String) replyJSON.get("message");

            if (message.equals("AKIN"))
                return true;
        } catch (SocketTimeoutException e) {
            System.err.println("Timeout: No packet received from port: " + port + " at " + ip);
        } catch (IOException e) {
            System.err.println("Error checking port " + port + ": " + e.getMessage());
        }
        return false;
    }

}
