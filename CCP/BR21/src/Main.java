import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import communication.MessageListener;
import utility.Constants;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("!!!Program Started!!!");
        BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>(10);

        MessageListener messageListener = new MessageListener(Constants.CCP_PORT, Constants.LOCAL_HOST, messageQueue);
        messageListener.run();

    }
}
