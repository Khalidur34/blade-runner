import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import communication.UDPSender;
import configuration.Constants;
import communication.MessageListener;
import communication.MessageProcessor;
import communication.Setup;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("!!!Program Started!!!");
        if (!Setup.setupBR() || !Setup.setupMCP())
            return;
        BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>(10);

        MessageListener messageListener = new MessageListener(Constants.CCP_PORT, Constants.LOCAL_HOST, messageQueue);
        Thread listen = new Thread(messageListener);
        listen.start();
        MessageProcessor processor = new MessageProcessor(messageQueue);
        Thread proccess = new Thread(processor);
        proccess.start();

    }
}
