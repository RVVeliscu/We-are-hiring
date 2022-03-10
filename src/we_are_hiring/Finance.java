package we_are_hiring;

public class Finance extends Department{
	@Override
	public double getTotalSalaryBudget() {
		double buget = 0;
		
		for(Employee e : this.getEmployees()) {
			if(e.getTotalExperience() < 1) {
				buget += e.getSalary() + 10/100*e.getSalary();
			} else {
				buget += e.getSalary() + 16/100*e.getSalary();
			}
		}
//		for(Job job : this.getJobs()) {
//			buget += job.getSalariu();
//		}
		
		return buget;
	}
}
