package we_are_hiring;

public interface Subject {
	   void addObserver(Observer observer);
	   void removeObserver(Observer observer);
	   void notifyObservers(Notification notificare);
}