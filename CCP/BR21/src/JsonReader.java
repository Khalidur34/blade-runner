import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

// "client_type": "ccp",
// "message": "STAT",
// "client_id": "BRXX",
// "timestamp": "2019-09-07T15:50+00Z",
// "status":"STOPPED_AT_STATION",
// "station_id": "STXX"

public class JsonReader implements DataInterface {

    /*
     * - Arduino Carriage
     * 1. Verify Timestamp Not Expired
     * 2. Check Message
     * 
     * OR
     * 
     * - MCP
     * 1. Verify Timestamp Not Expired
     * 2. Check Message
     * IF AKIN => Do Nothing
     * IF STAT => Either send STAT to MCP or Arduino (reply or update)
     * IF EXEC => check ACTION field => change BladeRunner state
     * ELSE MESSAGE INVALID
     */

    public static void readMasterMessage(String jsonString) {
        System.out.println("!!! READING MASTER MESSAGE !!!");
        try {
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(jsonString);

            // check if timestamp is valid

            String message = (String) json.get("message");
            System.out.println(json.toString());

            if (message.equals("EXEC") || message.equals("DOOR")) {
                String action = (String) json.get("action");
                switch (action) {
                    case "SLOW":
                        break;
                    case "FAST":
                        break;
                    case "STOP":
                        break;
                    case "STOP_ALL":
                        break;
                    case "OPEN":
                        break;
                    case "CLOSE":
                        break;
                }
            } else if (message.equals("STAT")) {
                System.out.println("STAT Received");
                // inset code to send stat
            } else if (message.equals("AKIN")) {
                System.out.println("AKIN ACKNOWLEDGED BY MCP");
            } else {
                System.out.println("Message type unknown");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readArduinoMessage(String jsonString) {
        System.out.println("!!! READING Arduino MESSAGE !!!");
        try {
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(jsonString);

            // WAITING FOR ARDUINO DEVELOPMENT

            System.out.println(json.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
