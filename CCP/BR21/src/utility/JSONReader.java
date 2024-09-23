package utility;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONReader {

    public static JSONObject getJson(String payload) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(payload);
            return json;
        } catch (Exception pe) {
            System.out.println("Parse Exception");
        }
        return null;
    }
}
