public abstract interface SubjectInterface {
    public void registerObserver(SendingData ob);

    public void removeObserver();

    public void notifyObservers(String a);
}