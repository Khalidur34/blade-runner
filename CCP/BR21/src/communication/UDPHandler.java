package communication;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import carriage.CarriageState;
import utility.Constants;

public abstract class UDPHandler extends Thread {
    protected DatagramSocket socket;
    protected byte[] buf;
    protected boolean running;

    protected CarriageState bladerunner;
    protected UDPSender arduinoSender;
    protected UDPSender masterSender;

    // Create socket address from port number and IP,
    // Create port to listen on socket address,
    // Set message size
    public UDPHandler(int port, String addr, CarriageState bladerunner, UDPSender mSender, UDPSender aSender)
            throws SocketException, UnknownHostException {
        this.bladerunner = bladerunner;
        InetSocketAddress socketAddress = new InetSocketAddress(InetAddress.getByName(addr), port);
        this.socket = new DatagramSocket(socketAddress);
        this.buf = new byte[Constants.MESSAGE_SIZE];
        this.masterSender = mSender;
        this.arduinoSender = aSender;

    }

    @Override
    public void run() {
        running = true;
        System.out.println("!!! HANDLER STARTED !!!");
        while (running) {
            try {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String jsonPayload = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received JSON: " + jsonPayload);
                processMessage(jsonPayload);

            } catch (IOException e) {
                e.printStackTrace();
                stopHandler();
            }
        }
        socket.close();
    }

    public void stopHandler() {
        running = false;
    }

    protected abstract void processMessage(String jsonPayload);

}