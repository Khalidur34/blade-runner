package communication;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketTimeoutException;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import configuration.Constants;
import configuration.SystemVariables;

public class Setup {

    private static final int BR_TIMEOUT = 10000;
    private static final int MCP_TIMEOUT = 5000;
    private static final int MAX_RETRIES = 3;

    public static boolean setupBR() {
        System.out.println("BEGIN BR SETUP");
        try (DatagramSocket socket = new DatagramSocket(Constants.CCP_PORT)) {
            socket.setSoTimeout(BR_TIMEOUT);

            byte[] buffer = new byte[Constants.MAX_MESSAGE_SIZE];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            try {
                // receive the packet
                socket.receive(packet);
                String payload = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Message Received: " + payload);
                
                String[] messageParts = payload.split(":");
                if (messageParts[0].equals("BRIN")) {
                    UDPSender.sendMessage(Constants.BR_IP, Constants.BR_PORT, "AK:BRIN");
                    return true;
                }
                System.out.println("ERROR: Incorrect message received, shutting down");
                return false;
            } catch (SocketTimeoutException e) {
                System.out.println("ERROR: No BRIN received, shutting down");
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean setupMCP() {
        System.out.println("BEGIN MCP SETUP");
        JsonObject newMsg = new JsonObject();
        newMsg.addProperty("client_type", Constants.CLIENT_TYPE);
        newMsg.addProperty("client_id", Constants.CLIENT_ID);
        newMsg.addProperty("sequence_number", SystemVariables.getAndIncrementMSequence());
        newMsg.addProperty("message", "CCIN");

        try (DatagramSocket socket = new DatagramSocket(Constants.CCP_PORT)) {
            int retries = 0;
            socket.setSoTimeout(MCP_TIMEOUT);

            byte[] buffer = new byte[Constants.MAX_MESSAGE_SIZE];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            while (retries < MAX_RETRIES) {
                try {
                    UDPSender.sendMessage(Constants.HOST_IP, Constants.MCP_PORT, newMsg.toString());
                    socket.receive(packet);
                    String response = new String(packet.getData(), 0, packet.getLength());
                    JsonObject json = JsonParser.parseString(response).getAsJsonObject();
                    String messageType = json.get("message").getAsString();
                    if (messageType.equals("AKIN")) {
                        System.out.println("MCP Connected");
                        return true;
                    }

                    System.out.println("ERROR: Incorrect message received, shutting down");
                    return false;
                } catch (SocketTimeoutException e) {
                    retries++;
                    System.out.println("ERROR: No AKIN received");
                }
            }
            System.out.println("ERROR: Could not connect with MCP");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

}