import java.io.IOException;
import java.time.LocalDateTime;

import org.json.simple.JSONObject; // currently the json simple libary is not working not sure why

final public class SendingData implements DataInterface, ObserverInterface {
    
    static public void update(String LED, boolean doorsOpen, int speed, String clientID, String changed){
        JSONObject jsonObject = new JSONObject();
        if(changed.equals("doorsOpen")){
            jsonObject.put("Door", doorsOpen);
            jsonObject.put("LED", LED);
        }
        if(changed.equals("speed")){
            jsonObject.put("Speed", speed);
            jsonObject.put("LED", LED);    
            
        }
    }
    static public void directUpdate(String m, String clientID, String Status){
        JSONObject jsonObject = new JSONObject();
        LocalDateTime now = LocalDateTime.now();
        if(m.equals("CCIN")){
            jsonObject.put("client_type", "ccp");
            jsonObject.put("message", "CCIN");
            jsonObject.put("client_id", clientID);
            jsonObject.put("Timestamp", now);
        }
        if(m.equals("STAT")){
            jsonObject.put("client_type", "ccp");
            jsonObject.put("message", "STAT");
            jsonObject.put("client_id", clientID);
            jsonObject.put("Timestamp", now);
            jsonObject.put("status", Status);
        }
        if(m.equals("STAT_STATION")) {
            jsonObject.put("client_type", "ccp");
            jsonObject.put("message", "STAT");
            jsonObject.put("client_id", clientID);
            jsonObject.put("Timestamp", now);
            jsonObject.put("status", "STOPPED_AT_STATION");
            jsonObject.put("station_id", "STXX");
        } 
        
  
        try {
            UDPClient client = new UDPClient();
            client.send(jsonObject.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}



        // try {
        //    FileWriter file = new FileWriter("E:/output.json");
        //    file.write(jsonObject.toJSONString());
        //    file.close();
        // } catch (IOException e) {
        //    e.printStackTrace();
        // }
        // System.out.println("JSON file created: "+jsonObject);
