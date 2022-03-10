package we_are_hiring;

public class Marketing extends Department{
	@Override
	public double getTotalSalaryBudget() {
		double buget = 0;
		
		for(Employee e : this.getEmployees()) {
			if(e.getSalary() > 50000) {
				buget += e.getSalary() + 10/100*e.getSalary();
			} else if(e.getSalary() < 30000) {
				buget += e.getSalary();
			} else {
				buget += e.getSalary() + 16/100*e.getSalary();
			}
		}
//		for(Job job : this.getJobs()) {
//			if(job.getSalariu() > 5000) {
//				buget += job.getSalariu() + 10/100*job.getSalariu();
//			} else if(job.getSalariu() < 30000) {
//				buget += job.getSalariu();
//			} else {
//				buget += job.getSalariu() + 16/100*job.getSalariu();
//			}
//		}
		
		return buget;
	}
}
