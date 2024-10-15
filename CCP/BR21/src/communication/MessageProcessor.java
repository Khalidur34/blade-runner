package communication;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import configuration.Constants;
import configuration.SystemVariables;

public class MessageProcessor implements Runnable {

    private final BlockingQueue<String> messageQueue;
    private final Map<String, MCPHandler> MCPhandlers;
    private final Map<String, BRHandler> BRhandlers;

    public MessageProcessor(BlockingQueue<String> messageQueue) {
        this.messageQueue = messageQueue;
        this.MCPhandlers = new HashMap<>();
        this.BRhandlers = new HashMap<>();
        initializeHandlers();
    }

    private void scheduleChecks() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);
        // BR Heartbeat
        SystemVariables.setVariable("STAT", 0);
        scheduler.scheduleAtFixedRate(() -> {
            int retries = SystemVariables.getVariable("STAT");
            if (retries <= 3) {

                UDPSender udpSender = new UDPSender(Constants.BR_IP, Constants.BR_PORT);
                udpSender.sendMessage("STRQ");

                SystemVariables.setVariable("STAT", retries + 1);
                System.out.println("Increment Retries");

            } else {
                System.out.println("RETRIES MAXXED,  BR IS OFFLINE.");
            }

            System.out.println("Scheduled check performed.");
        }, 0, 2, TimeUnit.SECONDS);

        // MCP Heartbeat
        scheduler.scheduleAtFixedRate(() -> {
            if (SystemVariables.isMcpOnline()) {
                SystemVariables.setMcpOnline(false);
            } else {
                System.out.println("Scheduled check: MCP IS OFFLINE.");

            }
        }, 0, 6, TimeUnit.SECONDS);
    }

    public void initializeHandlers() {
        this.MCPhandlers.put("CCIN", new AKINHandler());
        this.MCPhandlers.put("AKST", new AKSTHandler());
        this.MCPhandlers.put("STRQ", new STRQHandler());
        this.MCPhandlers.put("EXEC", new EXECHandler());
        this.MCPhandlers.put("STRQ", new NOIPHandler());

        this.BRhandlers.put("AK", new AKHandler());
        this.BRhandlers.put("STAT", new STATHandler());
        this.BRhandlers.put("UPDT", new UPDTHandler());
    }

    @Override
    public void run() {
        while (true) {
            try {
                String message = messageQueue.take();
                processMessage(message);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupted status
                System.err.println("Message processor interrupted.");
                break;
            }
        }
    }

    public void processMessage(String message) {

        System.err.println("Message BEING WOKRED ON");
        // handle JSON from MCP
        if (message.startsWith("{")) {
            JsonObject json = JsonParser.parseString(message).getAsJsonObject();

            String messageType = json.get("message").getAsString();
            System.err.println(messageType);
            MCPHandler handler = MCPhandlers.get(messageType);
            if (handler == null)
                handler = MCPhandlers.get("NOIP");
            System.out.println("WORKING");
            handler.handle(json);

        }

        // HANDLE String from BR
        else {
            String[] messageParts = message.split(":");
            BRHandler handler = BRhandlers.get(messageParts[0]);
            if (handler != null) {

            } else {
                System.out.println("Handler not found for message: " + message);
            }
        }
    }

}
