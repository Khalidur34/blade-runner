package communication;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import communication.MCPHandler;

public class MessageProcessor implements Runnable {

    private final BlockingQueue<String> messageQueue;
    private final Map<String, MCPHandler> MCPhandlers;
    private final Map<String, MCPHandler> BRhandlers;

    public MessageProcessor(BlockingQueue<String> messageQueue) {
        this.messageQueue = messageQueue;
        this.MCPhandlers = new HashMap<>();
        this.BRhandlers = new HashMap<>();
        initializeHandlers();
    }

    public void initializeHandlers() {
        this.MCPhandlers.put("CCIN", new AKINHandler());
        this.MCPhandlers.put("AKST", new AKSTHandler());
        this.MCPhandlers.put("STRQ", new STRQHandler());
        this.MCPhandlers.put("EXEC", new EXECHandler());
        this.MCPhandlers.put("STRQ", new NOIPHandler());
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

    public JSONObject parseToJSON(String message) {
        try {
            JSONParser parser = new JSONParser();
            return (JSONObject) parser.parse(message);
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void processMessage(String message) {
        MCPHandler handler;
        if (message.startsWith("{")) {
            JSONObject json = parseToJSON(message);
            String messageType = (String) json.get("message");

            handler = MCPhandlers.get(messageType);
            if (handler == null)
                handler = MCPhandlers.get("NOIP");

            handler.handle(json);

        } else {
            String[] messageParts = message.split(":");
            handler = BRhandlers.get(messageParts[0]);
            if (handler != null) {

            } else {
                System.out.println("Handler not found for message: " + message);
            }
        }
    }

}
