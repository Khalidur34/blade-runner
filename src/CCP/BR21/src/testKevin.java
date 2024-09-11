import java.io.IOException;

public class testKevin {
    UDPClient client;

    public static void main(String[] args) throws IOException {
        System.out.println("!!! Program Started !!!");
        CCPServer server = new CCPServer(4302, "localhost", 1024);
        server.start();

        SendingData.directUpdate("CCIN", "BR20", "that one");
    }

}
