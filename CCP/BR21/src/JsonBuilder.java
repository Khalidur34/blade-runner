import org.json.simple.JSONObject;

public class JsonBuilder {

    public static JSONObject buildCCIN(
            String client, String message, String client_id, String time) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("client_type", client);
        jsonObject.put("message", message);
        jsonObject.put("client_id", client_id);
        jsonObject.put("Timestamp", time);
        return jsonObject;
    }

    public static JSONObject buildSTAT(
            String client, String message, String client_id, String time, String status) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("client_type", client);
        jsonObject.put("message", message);
        jsonObject.put("client_id", client_id);
        jsonObject.put("Timestamp", time);
        jsonObject.put("status", status);
        return jsonObject;
    }

    public static JSONObject buildStationSTAT(
            String client, String message, String client_id, String time, String status, String station) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("client_type", client);
        jsonObject.put("message", message);
        jsonObject.put("client_id", client_id);
        jsonObject.put("Timestamp", time);
        jsonObject.put("status", status);
        jsonObject.put("station_id", station);
        return jsonObject;
    }

}
