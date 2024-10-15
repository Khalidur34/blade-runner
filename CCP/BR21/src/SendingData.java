import java.io.IOException;
import java.time.LocalDateTime;

import org.json.simple.JSONObject; // currently the json simple libary is not working not sure why

final public class SendingData implements DataInterface, ObserverInterface {
    
//    @SuppressWarnings("unchecked")
    static public void update(String LED, boolean doorsOpen, int speed, String clientID, String changed){
        JSONObject jsonObject = new JSONObject();
        // if(changed.equals("doorsOpen")){
        //     jsonObject.put("Door", doorsOpen);
        //     jsonObject.put("LED", LED);
        // }
        // if(changed.equals("speed")){
        //     jsonObject.put("Speed", speed);
        //     jsonObject.put("LED", LED);    
            
        // }

        // all with the master 
        // for connection / Awk (replace CCIN with AKEX)
        jsonObject.put("client_type", "CCP");
        jsonObject.put("message", "CCIN");
        jsonObject.put("client_id", "BRXX");
        jsonObject.put("sequence_number", "s_ccp");
      
        //for status
        jsonObject.put("client_type", "CCP");
        jsonObject.put("message", "STAT");
        jsonObject.put("client_id", "BRXX");
        jsonObject.put("sequence_number", "s_ccp");
        jsonObject.put("status","---------------------------------------------");
        //STOPC, STOPO, FSLOWC, FFASTC, RSLOWC, ERR, OFLN

        //
        jsonObject.put("", "");
        jsonObject.put("", "");
        jsonObject.put("", "");
        jsonObject.put("", "");
      
        



        jsonObject.put("AKIN","test");
        // jsonObject.put("client_type", "CCP");
        // jsonObject.put("message", "CCIN");
        // jsonObject.put("client_id", "BR20");
        // jsonObject.put("sequence_number", "000001");  
        try {
            
            UDPClient client = new UDPClient();
            client.send("THE ROOOOOOOOOOKIE FR A FIRE SHOW CUZ");
            //client.send(jsonObject.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    static public void directUpdate(String m, String clientID, String Status){
        JSONObject jsonObject = new JSONObject();
        LocalDateTime now = LocalDateTime.now();
        now.toString();
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
            client.send("AKIN");

      //      client.send(jsonObject.toString());
            
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
