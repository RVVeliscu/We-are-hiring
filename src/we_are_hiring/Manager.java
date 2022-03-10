package we_are_hiring;

import java.util.ArrayList;
import java.util.Vector;

public class Manager extends Employee{
	private ArrayList<Request<Job, Consumer>> requests;
	
	{
		this.requests = new ArrayList<Request<Job, Consumer>>();
	}
	
	public Manager() {
		super();
	}
	
	public Manager(String companie, int salariu) {
		super(companie, salariu);
	}
	
	public Manager(String nume, String prenume, String email, String telefon, String data, String sex) {
		super(nume, prenume, email, telefon, data, sex);
	}
	
	public Manager(String nume, String prenume, String email, String telefon, String data, String sex, String companie, int salariu) {
		super(nume, prenume, email, telefon, data, sex, companie, salariu);
	}

	public void process(Job job) {
		Vector<Request<Job, Consumer>> aplicanti = new Vector<Request<Job, Consumer>>();
		
		for(Request<Job, Consumer> req : requests) {
			if(req.getKey().equals(job)) {
				for(Request<Job, Consumer> it : aplicanti) {
					if(req.getScore() > it.getScore()) {
						aplicanti.insertElementAt(req, aplicanti.indexOf(it));
					}
				}
			}
		}
		
		int availablePositions = job.getNumarNecesar();
		for(Request<Job, Consumer> aplicant : aplicanti) {
			if(availablePositions > 0) {
				boolean ok = false;
				for(Company companie : Application.getInstance().getCompanies()) {
					if(companie.contains((Employee)(aplicant.getValue1()))) {
						ok = true;
					}
				}
				if(!ok) {
					for(Company c : Application.getInstance().getCompanies()) {
						for(Department d : c.getDepartamente()) {
							for(Job j : d.getJobs()) {
								if(j.equals(job)) {
									c.add(((User) (aplicant.getValue1())).convert().setCompany(job.numeCompanie).setSalary(job.getSalariu()), d);
									
								}
							}
						}
					}
					availablePositions--;
					for(Company companie : Application.getInstance().getCompanies()) {
						if(companie.contains((Employee)(aplicant.getValue1()))) {
							companie.remove((Employee)(aplicant.getValue1()));
						}
					}
				}
			} else {
				((User) (aplicant.getValue1())).update(new Notification(aplicant.getKey(), 2));
			}
		}
		this.requests.clear();
		job.close();
	}
	
	public void addRequest(Request<Job, Consumer> request) {
		this.requests.add(request);
	}
}
