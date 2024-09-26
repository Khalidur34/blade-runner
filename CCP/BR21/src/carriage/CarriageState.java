package carriage;

public class CarriageState {
    String state;
    int speed;
    boolean doorsOpen;
    String LED;

    String stationID; // keep this null unless stopped at station
    String clientID;

    public CarriageState(String id) {
        state = "STOP";
        speed = 0;
        doorsOpen = false;
        LED = "red";
        clientID = id;
    }

    public CarriageState(String st, String L, boolean d, int s, String c) {
        state = st;
        LED = L;
        doorsOpen = d;
        speed = s;
        clientID = c;
    }

    public String getState() {
        return this.state;
    }

    public String getStationID() {
        return stationID;
    }

    public void moveSlow(String command) {
        state = command;
        speed = 1000;
        LED = "yellow";
        stationID = null;
    }

    public void moveFast(String command) {
        state = command;
        speed = 2000;
        LED = "green";
    }

    public void stop(String command) {
        state = command;
        speed = 0;
        LED = "red";
    }

    public void openDoor(String command) {
        state = command;
        doorsOpen = true;
    }

    public void closeDoor(String command) {
        state = command;
        doorsOpen = false;
    }

    // if already in the state return false
    // if door open and not stopped return false
    public boolean isValidCommand(String command) {
        if (state.equals(command))
            return false;
        if (command.equals("OPEN") && speed > 0)
            return false;
        return true;
    }

    // old code

    // public void updateDoorsOpen(boolean d) {
    // doorsOpen = d;
    // LED = "#a432a8"; // purple
    // notifyObservers("doorsOpen");
    // }

    // public void updateSpeed(int s) {
    // speed = s;
    // if (speed > 0)
    // LED = "#b00c0c"; // red
    // else
    // LED = "#27b00c"; // green
    // notifyObservers("speed");
    // }

    // public void updateClientID(String c) {
    // clientID = c;
    // notifyObservers("clientID");
    // }

    // public void notifyObservers(String a) {
    // SendingData.update(LED, doorsOpen, speed, clientID, a);
    // }
}
