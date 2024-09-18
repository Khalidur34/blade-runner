import org.json.simple.JSONObject;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class JsonBuilder {
    private JSONObject jsonObject;

    // "client_type": "ccp",
    // "message": "STAT",
    // "client_id": "BRXX",z
    // "timestamp": "2019-09-07T15:50+00Z",
    // "status":"STOPPED_AT_STATION",
    // "station_id": "STXX"

    public JsonBuilder() {
        this.jsonObject = new JSONObject();

        // swap to environment variables
        jsonObject.put("client_type", "ccp");
        jsonObject.put("client_id", "BR21");

        ZonedDateTime now = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mmXXX");
        String formattedDateTime = now.format(formatter);
        jsonObject.put("timestamp", formattedDateTime);
    }

    public String getJsonString() {
        return jsonObject.toString();
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void buildSTAT(String status) {
        this.jsonObject.put("message", "STAT");
        this.jsonObject.put("status", status);
    }

    public void buildStationSTAT(String status, int stationId) {
        this.jsonObject.put("message", "STAT");
        this.jsonObject.put("status", status);
        this.jsonObject.put("status", stationId);
    }

    public void buildCCIN() {
        this.jsonObject.put("message", "CCIN");
    }

}
