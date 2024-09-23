package carriage;

import java.io.IOException;

import communication.ArduinoHandler;
import communication.MasterHandler;
import communication.UDPSender;
import setup.SystemSetup;
import utility.Constants;

public class CarrierControl {

    private CarriageState bladerunner;
    private MasterHandler master;
    private ArduinoHandler arduino;

    private UDPSender masterSender;
    private UDPSender arduinoSender;

    public CarrierControl() throws IOException {
        System.out.println("!!! Program Started !!!");

        bladerunner = new CarriageState("BR21");
        arduinoSender = new UDPSender(Constants.ARDUINO_PORT, Constants.LOCAL_HOST);
        masterSender = new UDPSender(Constants.MCP_PORT, Constants.LOCAL_HOST);

        arduino = new ArduinoHandler(Constants.CCP_PORT1, "localhost", bladerunner);
        master = new MasterHandler(Constants.CCP_PORT2, "localhost", bladerunner, masterSender, arduinoSender);
    }

    public void run() {
        // if (SystemSetup.checkPort(Constants.MCP_PORT, Constants.HOST_IP)
        // && SystemSetup.checkPort(Constants.ARDUINO_PORT, Constants.ARDUINO_IP)) {
        arduino.start();
        master.start();

        // } else {
        // System.out.println("!!! SETUP FAILED !!!");
        // return;
        // }
    }

    public void stop() {
        master.interrupt();
        arduino.interrupt();
        System.out.println("!!! STOPPING PROGRAM !!!");
    }

}
