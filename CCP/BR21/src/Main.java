import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import communication.UDPSender;
import configuration.Constants;
import communication.MessageListener;
import communication.MessageProcessor;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("!!!Program Started!!!");
        BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>(10);

        MessageListener messageListener = new MessageListener(Constants.CCP_PORT, Constants.LOCAL_HOST, messageQueue);
        Thread listen = new Thread(messageListener);
        listen.start();
        MessageProcessor processor = new MessageProcessor(messageQueue);
        Thread proccess = new Thread(processor);
        proccess.start();

        UDPSender messageSender = new UDPSender(Constants.LOCAL_HOST, Constants.CCP_PORT);
        System.out.println("SEND");
        messageSender.sendMessage(
                "{\n\"client_type\":\"CCP\",\n\"message\":\"EXEC\",\n\"client_id\":\"BR21\",\n\"sequence_number\":\"0\",\n\"action\":\"CSTOP\"\n}"

        );

    }
}
