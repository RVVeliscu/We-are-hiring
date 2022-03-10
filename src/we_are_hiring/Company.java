package we_are_hiring;

import java.util.ArrayList;
import java.util.Vector;

public class Company {
	public String numeCompanie;
	private Manager manager;
	private Vector<Department> departamente;
	private Vector<Recruiter> recruiters;
	private ArrayList<Observer> observers;
//	private IT it;
//	private Management management;
//	private Marketing marketing;
//	private Finance finance;
	{
		this.departamente = new Vector<Department>();
		this.recruiters = new Vector<Recruiter>();
		this.observers = new ArrayList<Observer>();
	}
	
	//de adaugat constructori if necessary
	public Company(String numeCompanie, Manager manager) {
		this.numeCompanie = numeCompanie;
		this.manager = manager;
	}
	
	public void add(Department department) {
		this.departamente.add(department);
	}
	
	public void add(Recruiter recruiter) {
		this.recruiters.add(recruiter);
//		de incercat dupa
//		for(Department d : this.departamente) {
//			if(d.getClass() == (new IT()).getClass()) {
//				d.add((Employee)recruiter);
//			}
//		}
	}
	
	public void add(Employee employee, Department department) {
		if(this.departamente.contains(department)) {
			department.add(employee);
		}
	}
	
	public void remove(Employee employee) {
		for(Department departament : this.departamente) {
			for(Employee e : departament.getEmployees()) {
				if(e.equals(employee)) {
					departament.remove(employee);
				}
			}
		}
		
		for(Recruiter r : this.recruiters) {
			if(r.equals(employee)) {
				this.remove((Recruiter) employee);
			}
		}
	}
	
	public void remove(Department department) {
		for(Employee e : department.getEmployees()) {
			this.remove(e);
		}
		
		this.departamente.remove(department);
	}
	
	public void remove(Recruiter recruiter) {
		this.recruiters.remove(recruiter);
	}
	
	public void move(Department source, Department destination) {
		for(Employee e : source.getEmployees()) {
			destination.add(e);
			source.remove(e);
		}
		
		ArrayList<Job> oldJobs = source.getJobs();
		for(Job j : oldJobs) {
			destination.add(j);
			source.remove(j);
		}
//		i could notify users about the moving
//		ArrayList<Observer> oldObservers = source.getObservers();
//		for(Observer o : oldObservers) {
//		}
	}
	
	public void move(Employee employee, Department newDepartment) {
		for(Department departament : this.departamente) {
			for(Employee e : departament.getEmployees()) {
				if(e.equals(employee)) {
					departament.remove(employee);
				}
			}
		}
		
		newDepartment.add(employee);
	}
	
	public boolean contains(Department department) {
		return this.departamente.contains(department);	
	}
	
	public boolean contains(Employee employee) {
		for(Department departament : this.departamente) {
			for(Employee e : departament.getEmployees()) {
				if(e.equals(employee)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean contains(Recruiter recruiter) {
		return this.recruiters.contains(recruiter);
	}
	
	public Recruiter getRecruiter(User user) {
		Vector<Pair<Recruiter, Integer>> interv = new Vector<Pair<Recruiter, Integer>>();
		
		for(Recruiter rec : this.recruiters) {
			int current = rec.getDegreeInFriendship(user);
			if(interv.isEmpty()) {
				interv.add(new Pair<Recruiter, Integer>(rec, current));
			} else {
				for(Pair<Recruiter, Integer> pereche : interv) {
					if(pereche.getValue() < current || (pereche.getValue() == current && pereche.getKey().getRating() < rec.getRating())) {
						interv.insertElementAt(new Pair<Recruiter, Integer>(rec, current), interv.indexOf(pereche));
					}
				}
			}
		}
		
		return interv.firstElement().getKey();
	}
	
	public ArrayList<Job> getJobs(){
		ArrayList<Job> joburi = new ArrayList<Job>();
		for(Department departament : this.departamente) {
			joburi.addAll(departament.getJobs());
		}
		return joburi;
	}
	
	public Vector<Department> getDepartamente(){
		return this.departamente;
	}
	
	public Manager getManager() {
		return this.manager;
	}
	
	public void addObserver(Observer observer) {
		   this.observers.add(observer);
		   for(Department d : this.getDepartamente())
			   d.addObserver(observer);
   }
   
	public void removeObserver(Observer observer) {
		if(this.observers.contains(observer))
			this.observers.remove(observer);
		for(Department d : this.getDepartamente())
			   d.removeObserver(observer);
	}
	
	public ArrayList<Observer> getObservers() {
		return this.observers;
	}
	
}
