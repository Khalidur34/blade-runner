package communication;

import java.net.SocketException;
import java.net.UnknownHostException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import carriage.CarriageState;

public class ArduinoHandler extends UDPHandler {

    public ArduinoHandler(int port, String addr, CarriageState br, UDPSender mSender, UDPSender aSender)
            throws SocketException, UnknownHostException {
        super(port, addr, br, mSender, aSender);
    }

    public void processMessage(String jsonString) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(jsonString);
            System.out.println(json.toString());

            String message = (String) json.get("message");

            if (message.equals("ESTOP")) {
                bladerunner.stop(message);
                System.out.println("EMERGENCY STOP: SENDING ????");
            } else if (message.equals("STAT")) {
                System.out.println("STAT Received: SENDING STAT");
            } else {
                System.out.println("Message type unknown");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}