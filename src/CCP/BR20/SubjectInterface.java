package CCP.BR20;

public abstract interface SubjectInterface {
    public void registerObserver();
    public void removeObserver();
    public void notifyObservers();
}