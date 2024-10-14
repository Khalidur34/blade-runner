import java.io.IOException;

public class BladerRunner implements BladerRunnerInterface, SubjectInterface {
    boolean moving;
    String LED;
    boolean doorsOpen;
    int speed;
    String clientID;

    public BladerRunner(String L, boolean d, int s, String c) {
        LED = L;
        doorsOpen = d;
        speed = s;
        clientID = c;
    }

    public void updateDoorsOpen(boolean d) {
        doorsOpen = d;
        LED = "#a432a8"; // purple
        notifyObservers("doorsOpen");
    }

    public void updateSpeed(int s) {
        speed = s;
        if (speed > 0)
            LED = "#b00c0c"; // red
        else
            LED = "#27b00c"; // green

        notifyObservers("speed");
    }

    public void updateClientID(String c) {
        clientID = c;
        notifyObservers("clientID");
    }

    public void notifyObservers(String a) {
        SendingData.update(LED, doorsOpen, speed, clientID, a);
    }
}
