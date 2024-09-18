import java.io.IOException;

public class testKevin {
    UDPClient client;

    public static void main(String[] args) throws IOException {
        System.out.println("!!! Program Started !!!");

        CCPServer masterListener = new CCPServer(4000, "localhost");
        CCPServer arduinoListener = new CCPServer(3000, "localhost");
        masterListener.start();
        arduinoListener.start();

        System.out.println("!!! SENDING MESSAGE !!!");
        SendingData.directUpdate("CCIN", "BR21", "that one");
    }

}
