package configuration;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicInteger;

public class SystemVariables {

    // Thread-safe HashMap to store string-integer mappings
    private static final ConcurrentHashMap<String, Integer> expectedMessages = new ConcurrentHashMap<>();

    // AtomicBoolean for thread-safe boolean value
    private static final AtomicBoolean mcpOnline = new AtomicBoolean(false);

    // state of the carriage
    private static final AtomicReference<String> carriageState = new AtomicReference<>("CSTOP"); // Default state

    // sequence number sending to MCP
    private static final AtomicInteger toMCPsqn = new AtomicInteger(1);
    private static final AtomicInteger fromMCPsqn = new AtomicInteger(1);

    // Getters and Setters
    public static Integer getVariable(String key) {
        return expectedMessages.get(key);
    }

    public static void setVariable(String key, Integer value) {
        expectedMessages.put(key, value);
    }

    public static void deleteExpectedMessage(String key) {
        expectedMessages.remove(key);
    }

    public static Integer getAndIncrementMSequence() {
        return toMCPsqn.getAndIncrement();
    }

    public static boolean isMcpOnline() {
        return mcpOnline.get();
    }

    public static void setMcpOnline(boolean value) {
        mcpOnline.set(value);
    }

    public static String getCarriageState() {
        return carriageState.get();
    }

    public static void setCarriageState(String state) {
        carriageState.set(state);
    }
}