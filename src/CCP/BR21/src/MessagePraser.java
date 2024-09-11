import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

// "client_type": "ccp",
// "message": "STAT",
// "client_id": "BRXX",
// "timestamp": "2019-09-07T15:50+00Z",
// "status":"STOPPED_AT_STATION",
// "station_id": "STXX"

public class MessagePraser implements DataInterface {

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

}
