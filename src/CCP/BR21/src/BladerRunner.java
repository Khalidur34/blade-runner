import java.io.IOException;

public class BladerRunner implements BladerRunnerInterface, SubjectInterface {
    boolean moving;
    String LED;
    boolean doorsOpen;
    int speed;
    String clientID;

    SendingData Observer;

    public BladerRunner(String L, boolean d, int s, String c) {
        LED = L;
        doorsOpen = d;
        speed = s;
        clientID = c;
    }
    public void updateLED(String L){
        LED = L;
        notifyObservers("LED");
    }
    public void updateDoorsOpen(boolean d){
        doorsOpen = d;
        notifyObservers("doorsOpen");
    }
    public void updateSpeed(int s){
        speed = s;
        notifyObservers("speed");
    }
    public void updateClientID(String c){
        clientID = c;
        notifyObservers("clientID");
    }

    public void registerObserver(SendingData ob){
        Observer = ob;
    }

    public void removeObserver(){}

    public void notifyObservers(String a){
       Observer.update(LED, doorsOpen, speed, clientID, a);
    }
}
