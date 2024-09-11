import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class MessagePraser implements DataInterface {

    public void MessagePraser() {

    }

    public void readJsonMessage(String jsonString) {
        try {

            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(jsonString);

            String client_id = (String) json.get("client_id");
            String message = (String) json.get("message");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

// "client_type": "ccp",
// "message": "STAT",
// "client_id": "BRXX",
// "timestamp": "2019-09-07T15:50+00Z",
// "status":"STOPPED_AT_STATION",
// "station_id": "STXX"