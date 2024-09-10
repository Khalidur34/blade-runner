import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class IncomingData implements DataInterface {

    public void readJsonMessage(String jsonString) {
        try {
            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();
            // Convert JSON string to Map
            Map<String, Object> jsonData = objectMapper.readValue(jsonString, Map.class);

            if (jsonData.get("message").equals("STATE")) {

            }
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