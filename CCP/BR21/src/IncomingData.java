import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.json.simple.JSONObject;

final public class IncomingData {
    static public void sortMessage(ArrayList<String> messageList) throws IOException{
        String message = messageList.get(0);
        if (message.equals("stat")){
            SendingData.dSend("stat");
        }
    }
}
