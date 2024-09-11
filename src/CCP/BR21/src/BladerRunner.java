import java.io.IOException;

public class BladerRunner implements BladerRunnerInterface, SubjectInterface {
    boolean moving;
    String LED;
    boolean doorsOpen;
    int speed;
    String clientID;

    SendingData Observer;

    public BladerRunner(boolean m, String L, boolean d, int s, String c) {
        moving = m;
        LED = L;
        doorsOpen = d;
        speed = s;
        clientID = c;
    }
    public void updateMoving(boolean m){
        moving = m;
        notifyObservers("moving");
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
       Observer.update(moving, LED, doorsOpen, speed, clientID, a);
    }
}
