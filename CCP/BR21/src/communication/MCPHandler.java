package communication;

import com.google.gson.JsonObject;

import configuration.Constants;
import configuration.SystemVariables;

interface MCPHandler {
    void handle(JsonObject json);
}

class AKINHandler implements MCPHandler {
    @Override
    public void handle(JsonObject json) {

    }
}

class AKSTHandler implements MCPHandler {
    @Override
    public void handle(JsonObject json) {
        SystemVariables.deleteExpectedMessage("AKST");
    }
}

class STRQHandler implements MCPHandler {
    @Override
    public void handle(JsonObject json) {
        SystemVariables.setMcpOnline(true);

        JsonObject newMsg = new JsonObject();
        newMsg.addProperty("client_type", Constants.CLIENT_TYPE);
        newMsg.addProperty("client_id", Constants.CLIENT_ID);
        newMsg.addProperty("sequence_number", SystemVariables.getAndIncrementMSequence());
        newMsg.addProperty("message", "STAT");
        newMsg.addProperty("status", SystemVariables.getCarriageState());

        UDPSender udpSender = new UDPSender(Constants.HOST_IP, Constants.MCP_PORT);
        udpSender.sendMessage(newMsg.toString());
        System.out.println(newMsg.toString());
    }
}

class EXECHandler implements MCPHandler {
    @Override
    public void handle(JsonObject json) {
        JsonObject newMsg = new JsonObject();
        newMsg.addProperty("client_type", Constants.CLIENT_TYPE);
        newMsg.addProperty("client_id", Constants.CLIENT_ID);
        newMsg.addProperty("sequence_number", SystemVariables.getAndIncrementMSequence());
        newMsg.addProperty("message", "AKEX");
        UDPSender udpSender = new UDPSender(Constants.HOST_IP, Constants.MCP_PORT);
        udpSender.sendMessage(newMsg.toString());

        String action = json.get("action").getAsString();
        udpSender = new UDPSender(Constants.BR_IP, Constants.BR_PORT);
        udpSender.sendMessage("EXEC:" + action);

    }
}

class NOIPHandler implements MCPHandler {
    @Override
    public void handle(JsonObject json) {
        JsonObject newMsg = new JsonObject();
        newMsg.addProperty("client_type", Constants.CLIENT_TYPE);
        newMsg.addProperty("client_id", Constants.CLIENT_ID);
        newMsg.addProperty("sequence_number", SystemVariables.getAndIncrementMSequence());
        newMsg.addProperty("message", "NOIP");

        UDPSender udpSender = new UDPSender(Constants.HOST_IP, Constants.MCP_PORT);
        udpSender.sendMessage(newMsg.toString());
        System.out.println(newMsg.toString());
    }
}