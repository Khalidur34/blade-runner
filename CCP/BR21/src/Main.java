import java.io.IOException;

import carriage.CarrierControl;
import communication.UDPSender;
import utility.Constants;
import utility.JSONBuilder;

public class Main {
    public static void main(String[] args) {
        try {
            CarrierControl carrierControl = new CarrierControl();
            carrierControl.run();

            System.out.println("!!! SENDING TEST MESSAGE !!!");

            Thread.sleep(3000);
            UDPSender udpSender = new UDPSender(Constants.CCP_PORT2, Constants.LOCAL_HOST);
            JSONBuilder json = new JSONBuilder();
            json = json.addCommand("OPEN");
            udpSender.send(json.toString());

        } catch (IOException io) {
        } catch (InterruptedException oasd) {

        }
    }
}
