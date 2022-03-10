	package we_are_hiring;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class User extends Consumer implements Observer{
	private List<String> lista;
	private ArrayList<Job> jobs;
	private Vector<Notification> notifications;
	
	{
		this.jobs = new ArrayList<Job>();
		this.lista = new ArrayList<String>();
		this.notifications = new Vector<Notification>();
	}
	
	public User() {
		super();
	}
	
	public User(List<String> companies) {
		super();
		this.lista = companies;
		this.jobs = Application.getInstance().getJobs(this.lista);
	}
	
	public User(String nume, String prenume, String email, String telefon, String data, String sex, List<String> companies) {
		super(nume, prenume, email, telefon, data, sex);
		this.lista = companies;
		this.jobs = Application.getInstance().getJobs(this.lista);
	}
	
	public ArrayList<Job> getJobs() {
		return this.jobs;
	}
	
	public void applyToAll() {
		for(Job j : this.jobs) {
			j.apply(this);
		}
	}
	
	public Employee convert() {
		for(Company c : Application.getInstance().getCompanies()) {
			c.removeObserver(this);
		}
		return new Employee(this.resume.info.getNume(), 
												this.resume.info.getPrenume(),
												this.resume.info.getEmail(),
												this.resume.info.getTelefon(),
												this.resume.info.getData(),
												this.resume.info.getSex());
	}
	
	public Double getTotalScore() {
		return this.meanGPA() + this.getTotalExperience()*1.5;
	}

	@Override
	public void update(Notification notification) {
		// TODO Auto-generated method stub
		this.notifications.add(notification);
	}
}
