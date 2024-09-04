import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject; // currently the json simple libary is not working not sure why

public class SendingData implements DataInterface, ObserverInterface {
    public void update(boolean moving, String LED, boolean doorsOpen, int speed, String clientID, String changed){
        if(changed.equals("moving")){

        }
        if(changed.equals("LED")){

        }
        if(changed.equals("doorsOpen")){

        }
        if(changed.equals("speed")){

        }
        if(changed.equals("clientID")){

        }
        // need to create a jason file that communicates with the hardware to indecate what needs to happen
        JSONObject jsonObject = new JSONObject();
        //Inserting key-value pairs into the json object
        jsonObject.put("ID", "1");
        jsonObject.put("First_Name", "test");
        jsonObject.put("Last_Name", "test2");
        jsonObject.put("Date_Of_Birth", "1981-12-05");
        jsonObject.put("Place_Of_Birth", "test3");
        jsonObject.put("Country", "test4");
        try {
           FileWriter file = new FileWriter("E:/output.json");
           file.write(jsonObject.toJSONString());
           file.close();
        } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
        }
        System.out.println("JSON file created: "+jsonObject);

//         "client_type": "ccp",
// "message": "STAT",
// "client_id": "BRXX",
// "timestamp":
// "2019-09-07T15:50+00Z",
// "status":
// "STOPPED_AT_STATION",
// "station_id": "STXX"
    }
}
