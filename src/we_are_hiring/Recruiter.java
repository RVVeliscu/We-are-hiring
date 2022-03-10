package we_are_hiring;

public class Recruiter extends Employee{
	private double rating;
	
	public Recruiter() {
		super();
		this.rating = 5;
	}
	
	public Recruiter(String companie, int salariu) {
		super(companie, salariu);
		this.rating = 5;
	}
	
	public Recruiter(String nume, String prenume, String email, String telefon, String data, String sex) {
		super(nume, prenume, email, telefon, data, sex);
		this.rating = 5;
	}
	
	public Recruiter(String nume, String prenume, String email, String telefon, String data, String sex, String companie, int salariu) {
		super(nume, prenume, email, telefon, data, sex, companie, salariu);
		this.rating = 5;
	}
	
	public int evaluate(Job job, User user) {
		double evaluare = this.rating*user.getTotalScore();
		this.rating += 0.1;
		Request<Job, Consumer> req = new Request<Job, Consumer>(job, user, this, evaluare);
		
		Application.getInstance().getCompany(this.getNumeCompanie()).getManager().addRequest(req);
		
		return (int)evaluare;
	}
	
	public double getRating(){
		return this.rating;
	}
}
