import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

// "client_type": "ccp",
// "message": "STAT",
// "client_id": "BRXX",
// "timestamp": "2019-09-07T15:50+00Z",
// "status":"STOPPED_AT_STATION",
// "station_id": "STXX"

public class MessagePraser implements DataInterface {

    /*
     * Notes: DELETE LATER
     * 1. Determine the Sender
     * - Arduino Carriage
     * 2. Verify Timestamp Not Expired
     * 3. Check Message
     * 
     * 
     * OR
     * 
     * - MCP
     * 2. Verify Timestamp Not Expired
     * 3. Check Message
     * IF AKIN => Do Nothing
     * IF STAT => Either send STAT to MCP or Arduino (reply or update)
     * IF EXEC => check ACTION field => change BladeRunner state
     * ELSE MESSAGE INVALID
     */

    // remove this method later
    public static void readJsonMessage(String jsonString) {
        System.out.println("!!! Prasing Message !!!");
        try {
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(jsonString);

            String message = (String) json.get("message");

            System.out.println(message);
            System.out.println(json.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readMcpJson(String jsonString) {
        System.out.println("!!! Prasing MCP Message !!!");

        try {
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(jsonString);

            String message = (String) json.get("message");

            System.out.println(message);
            System.out.println(json.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readArduinoJson(String jsonString) {
        System.out.println("!!! Prasing Arduino Message !!!");

    }

}
