package communication;

import com.google.gson.JsonObject;

import configuration.Constants;
import configuration.SystemVariables;

interface BRHandler {
    void handle(String[] message);
}

class STATHandler implements BRHandler {
    @Override
    public void handle(String[] message) {
        SystemVariables.setVariable(message[0], 0);
        SystemVariables.setCarriageState(message[1]);
    }
}

class AKHandler implements BRHandler {
    @Override
    public void handle(String[] message) {
        SystemVariables.deleteExpectedMessage("EXEC");
    }
}

class UPDTHandler implements BRHandler {
    @Override
    public void handle(String[] message) {

        SystemVariables.setCarriageState(message[1]);

        JsonObject newMsg = new JsonObject();
        newMsg.addProperty("client_type", Constants.CLIENT_TYPE);
        newMsg.addProperty("client_id", Constants.CLIENT_ID);
        newMsg.addProperty("sequence_number", SystemVariables.getAndIncrementMSequence());
        newMsg.addProperty("message", "STAT");
        newMsg.addProperty("status", SystemVariables.getCarriageState());

        UDPSender udpSender = new UDPSender(Constants.HOST_IP, Constants.MCP_PORT);
        udpSender.sendMessage(newMsg.toString());
        System.out.println(newMsg.toString());

        SystemVariables.setVariable("AKST", 0);

    }
}
