package we_are_hiring;

public class IT extends Department{
	@Override
	public double getTotalSalaryBudget() {
		double buget = 0;
		
		for(Employee e : this.getEmployees()) {
			buget += e.getSalary();
		}
//		for(Job job : this.getJobs()) {
//			buget += job.getSalariu();
//		}
		
		return buget;
	}
}
