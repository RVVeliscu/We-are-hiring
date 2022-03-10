package we_are_hiring;

import java.util.ArrayList;

public class Job {
	public String numeCompanie;
	public boolean disponibilitate;
	private Constraint anAbsolvire, aniExperienta, gpa;
	private ArrayList<User> candidati;
	private int numarNecesar, salariu;
	
	{
		this.candidati = new ArrayList<User>();
	}
	
	public Job(String numeCompanie, int numarNecesar, int salariu,
				Constraint anAbsolvire, Constraint aniExperienta, Constraint gpa) {
		this.numeCompanie = numeCompanie;
		this.numarNecesar = numarNecesar;
		this.salariu = salariu;
		this.anAbsolvire = anAbsolvire;
		this.aniExperienta = aniExperienta;
		this.gpa = gpa;
		this.disponibilitate = true;
	}
	
	public void apply(User user) {
		Application.getInstance().getCompany(this.numeCompanie).addObserver(user);
		
		if(this.meetsRequirments(user)) {
			Recruiter recruiter = Application.getInstance().getCompany(numeCompanie).getRecruiter(user);
			recruiter.evaluate(this, user);
			this.candidati.add(user);
		}
	}
	
	public boolean meetsRequirments(User user) {
		if(this.anAbsolvire != null && this.anAbsolvire.check(user.getGraduationYear()) && 
			this.aniExperienta != null && this.aniExperienta.check(user.getTotalExperience()) && 
			this.gpa != null && this.gpa.check(user.meanGPA()) && this.disponibilitate )
			return true;
		return false;
	}
	
	public int getNumarNecesar() {
		return this.numarNecesar;
	}
	
	public int getSalariu() {
		return this.salariu;
	}
	
	public void close() {
		this.disponibilitate = false;
		for(User u : candidati) {
			u.update(new Notification(this, 1));
		}
	}
	
}
