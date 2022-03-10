package we_are_hiring;

public class Management extends Department{
	@Override
	public double getTotalSalaryBudget() {
		// TODO Auto-generated method stub
		double buget = 0;
		
		for(Employee e : this.getEmployees()) {
			buget += e.getSalary() + 16/100*e.getSalary();
		}
//		for(Job job : this.getJobs()) {
//			buget += job.getSalariu() + 16/100*job.getSalariu();
//		}
//		
		return buget;
	}
}
