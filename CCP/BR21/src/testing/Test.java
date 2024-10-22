package testing;

import java.util.concurrent.TimeUnit;

import communication.UDPSender;
import configuration.Constants;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("!!!Test Program Started!!!");

        System.out.println("SENDING TEST MESSAGE#1");
        UDPSender.sendMessage(Constants.LOCAL_HOST, Constants.CCP_PORT, "BRIN");

        Thread.sleep(2000);

        System.out.println("SENDING TEST MESSAGE#2");
        UDPSender.sendMessage(Constants.LOCAL_HOST, Constants.CCP_PORT,
                "{\"client_type\":\"CCP\",\"message\":\"AKIN\",\"client_id\":\"BR21\",\"sequence_number\":\"1000\"}");

        System.out.println("SENDING TEST MESSAGE#1");
        UDPSender.sendMessage(Constants.LOCAL_HOST, Constants.CCP_PORT,
                "{\n\"client_type\":\"CCP\",\n\"message\":\"EXEC\",\n\"client_id\":\"BR21\",\n\"sequence_number\":\"0\",\n\"action\":\"CSTOP\"\n}");

    }
}
