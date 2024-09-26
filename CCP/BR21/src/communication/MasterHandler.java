package communication;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import carriage.CarriageState;
import utility.JSONBuilder;

import java.net.SocketException;
import java.net.UnknownHostException;

// HANDLE MESSAGES FROM MASTER
public class MasterHandler extends UDPHandler {

    public MasterHandler(int port, String addr, CarriageState br, UDPSender mSender, UDPSender aSender)
            throws SocketException, UnknownHostException {
        super(port, addr, br, mSender, aSender);
    }

    public void processMessage(String jsonString) {
        System.out.println("!!! PROCESSING MASTER MESSAGE !!!");
        try {
            // parse message and read
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(jsonString);
            System.out.println(json.toString());

            // build json to send to arduino
            JSONBuilder buildJSON = new JSONBuilder();

            String message = (String) json.get("message");
            if (message.equals("EXEC") || message.equals("DOOR")) {

                String action = (String) json.get("action");
                if (!bladerunner.isValidCommand(action)) {
                    System.out.println("Carriage is already in state");
                    return;
                }

                buildJSON = buildJSON.forArduino().addTime().addCommand(action);
                arduinoSender.send(buildJSON.toString());

                switch (action) {
                    case "SLOW":
                        bladerunner.moveSlow(action);
                        break;
                    case "FAST":
                        bladerunner.moveFast(action);
                        break;
                    case "STOP":
                        bladerunner.stop(action);
                        break;
                    case "STOP_ALL":
                        bladerunner.stop(action);
                        break;
                    case "OPEN":
                        bladerunner.openDoor(action);
                        break;
                    case "CLOSE":
                        bladerunner.closeDoor(action);
                        break;
                }

            } else if (message.equals("STAT")) {
                String state = bladerunner.getState();
                buildJSON = buildJSON
                        .forMaster()
                        .addTime()
                        .addCommand("STAT")
                        .addState(state)
                        .addStationID(bladerunner.getStationID());
                masterSender.send(buildJSON.toString());
            } else {
                System.out.println("Message type unknown");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}