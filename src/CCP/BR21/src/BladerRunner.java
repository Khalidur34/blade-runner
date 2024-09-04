public class BladerRunner implements BladerRunnerInterface, SubjectInterface {
    boolean moving;
    String LED;
    boolean doorsOpen;
    int speed;
    String clientID;

    public BladerRunner(boolean m, String L, boolean d, int s, String c) {
        moving = m;
        LED = L;
        doorsOpen = d;
        speed = s;
        clientID = c;
    }
    public void updateMoving(boolean m){
        moving = m;
        notifyObservers();
    }
    public void updateLED(String L){
        LED = L;
        notifyObservers();
    }
    public void updateDoorsOpen(boolean d){
        doorsOpen = d;
        notifyObservers();
    }
    public void updateSpeed(int s){
        speed = s;
        notifyObservers();
    }
    public void updateClientID(String c){
        clientID = c;
        notifyObservers();
    }

    public void registerObserver(){}

    public void removeObserver(){}

    public void notifyObservers(){}
}
