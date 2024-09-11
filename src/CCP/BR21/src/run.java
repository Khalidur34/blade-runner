import java.io.IOException;

import org.json.simple.JSONObject;

public class run {
    public static void main (String[] args) throws IOException {
        System.out.println("Hello World!");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ID", "1");

        
        UDPClient client = new UDPClient();
       client.send(jsonObject.toString());
       System.out.println(jsonObject.toString());
       SendingData.update(true,"womp",false,0,"BR20","that one");
    }
}
