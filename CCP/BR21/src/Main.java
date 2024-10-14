import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import communication.MessageListener;
import utility.Constants;
import org.json.simple.JSONObject;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("!!!Program Started!!!");
        BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>(10);

        MessageListener messageListener = new MessageListener(Constants.CCP_PORT, Constants.LOCAL_HOST, messageQueue);
        messageListener.run();

    }
}
