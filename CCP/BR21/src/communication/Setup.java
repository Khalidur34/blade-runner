package communication;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

import com.google.gson.JsonObject;

import configuration.Constants;
import configuration.SystemVariables;
import communication.UDPSender;

public class Setup {

    private static final int TIMEOUT_MS = 5000;
    public boolean setupBR() {
        // wait -> timeout returne false
        // receieve
        // send acknowledgement
        // return true
        return true;
    }

    public boolean setupMCP() {

        JsonObject newMsg = new JsonObject();
        newMsg.addProperty("client_type", Constants.CLIENT_TYPE);
        newMsg.addProperty("client_id", Constants.CLIENT_ID);
        newMsg.addProperty("sequence_number", SystemVariables.getAndIncrementMSequence());
        newMsg.addProperty("message", "CCIN");

        UDPSender udpSender = new UDPSender(Constants.HOST_IP, Constants.MCP_PORT);
        udpSender.sendMessage(newMsg.toString());

        return false;
        // send CCIN
        // wait timeout -> return false
        // receive acknowledgement 
        // return true
    }

}