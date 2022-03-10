package we_are_hiring;

import java.util.ArrayList;

public abstract class Department implements Subject{
	private ArrayList<Job> jobs;
	private ArrayList<Employee> angajati;
	private ArrayList<Observer> observers;
	
	{
		this.jobs = new ArrayList<Job>();
		this.angajati = new ArrayList<Employee>();
		this.observers = new ArrayList<Observer>();
	}
	
	public abstract double getTotalSalaryBudget();
	
	public ArrayList<Job> getJobs() {
		return this.jobs;
	}
	
	public void add(Employee employee) {
		this.angajati.add(employee);
	}
	
	public void remove(Employee employee) {
		this.angajati.remove(employee);
	}
	
	public void add(Job job) {
		this.jobs.add(job);
		this.notifyObservers(new Notification(job, 0));
	}
	
	public void remove(Job job) {
		this.jobs.remove(job);
		this.notifyObservers(new Notification(job, 1));
	}
	
	public ArrayList<Employee> getEmployees() {
		return this.angajati;
	}
	
	public ArrayList<Observer> getObservers() {
		return this.observers;
	}
	
   public void addObserver(Observer observer) {
	   this.observers.add(observer);
   }
   
   public void removeObserver(Observer observer) {
	   if(this.observers.contains(observer))
		   this.observers.remove(observer);
   }
   
   public void notifyObservers(Notification notificare) {
	   for(Observer o: this.observers) {
		   o.update(notificare);
	   }
   }
}
