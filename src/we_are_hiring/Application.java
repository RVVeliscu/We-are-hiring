package we_are_hiring;

import java.util.ArrayList;
import java.util.List;

public class Application {
	private ArrayList<Company> companii;
	private ArrayList<User> utilizatori;
	private static Application obj = null;
	
	{
		this.companii = new ArrayList<Company>();
		this.utilizatori = new ArrayList<User>();
	}
	
	public static Application getInstance() {
		if(obj == null)
			obj = new Application();
		return obj;
	}
	
	public ArrayList<Company> getCompanies(){
		return this.companii;
	}
	
	public Company getCompany(String name) {
		for(Company companie : this.companii) {
			if(companie.numeCompanie.equals(name))
				return companie;
		}
		return null;
	}
	
	public void add(Company company) {
		this.companii.add(company);
	}
	
	public void add(User user) {
		this.utilizatori.add(user);
	}
	
	public boolean remove(Company company) {
		return this.companii.remove(company);
	}
	
	public boolean remove(User user) {
		return this.utilizatori.remove(user);
	}
	
	public ArrayList<Job> getJobs(List<String> companies){
		ArrayList<Job> joburi = new ArrayList<Job>();
		
		for(String numeCompanie : companies) {
			joburi.addAll(this.getCompany(numeCompanie).getJobs());
		}
		
		return joburi;
	}
}
