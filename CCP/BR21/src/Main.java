import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import communication.UDPSender;
import communication.MessageListener;
import utility.Constants;

import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("!!!Program Started!!!");
        BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>(10);

        MessageListener messageListener = new MessageListener(Constants.CCP_PORT, Constants.LOCAL_HOST, messageQueue);
        Thread listen = new Thread(messageListener);
        listen.start();

        UDPSender messageSender = new UDPSender(Constants.LOCAL_HOST, Constants.CCP_PORT);
        System.out.println("SEND");
        messageSender.sendMessage("Hello World");
    }
}
