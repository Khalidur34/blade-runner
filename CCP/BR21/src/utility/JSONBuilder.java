package utility;

import org.json.simple.JSONObject;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class JSONBuilder {
    private JSONObject jsonObject;

    public JSONBuilder() {
        jsonObject = new JSONObject();
    }

    public String toString() {
        return jsonObject.toString();
    }

    public JSONBuilder forMaster() {
        jsonObject.put("client_type", "ccp");
        jsonObject.put("client_id", Constants.CLIENT_ID);
        return this;
    }

    public JSONBuilder forArduino() {
        return this;
    }

    public JSONBuilder addTime() {
        ZonedDateTime now = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mmXXX");
        String formattedDateTime = now.format(formatter);
        jsonObject.put("timestamp", formattedDateTime);
        return this;
    }

    public JSONBuilder addCommand(String command) {
        jsonObject.put("message", "STAT");
        return this;
    }

    public JSONBuilder addState(String state) {
        jsonObject.put("status", state);
        return this;
    }

    public JSONBuilder addStationID(String stationID) {
        if (stationID != null) {
            jsonObject.put("station_id", stationID);
        }
        return this;
    }

    public JSONObject addField(String key, String value) {
        jsonObject.put(key, value);
        return this.jsonObject;
    }

}
