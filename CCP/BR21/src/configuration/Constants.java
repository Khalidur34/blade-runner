package configuration;

public class Constants {
    // Network settings
    public static final String LOCAL_HOST = "localhost";
    public static final String HOST_IP = "10.20.30.1";
    public static final int MCP_PORT = 2000;
    public static final int CCP_PORT = 3010;

    public static final String BR_IP = "10.20.30.121";
    public static final int BR_PORT = 10000;

    public static final int MAX_MESSAGE_SIZE = 1024;

    // BR Details
    public static final String CLIENT_ID = "BR21";
    public static final String CLIENT_TYPE = "CCP";

    // Messages
    public static final String START_COMMAND = "STOP";
    public static final String ESTOP_COMMAND = "STOP_ALL";
    public static final String FAST_COMMAND = "FAST";
    public static final String SLOW_COMMAND = "SLOW";
    public static final String OPEN_COMMAND = "OPEN";
    public static final String CLOSE_COMMAND = "CLOSE";
    public static final String CCIN_COMMAND = "CCIN";
    public static final String AKIN_COMMAND = "AKIN";
    public static final String STAT_COMMAND = "STAT";

}