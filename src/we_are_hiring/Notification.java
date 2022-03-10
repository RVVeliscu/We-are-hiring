package we_are_hiring;

public class Notification {
	public Job job;
	public String text;
	public int type;
	
	public Notification(Job job, int type) {
		this.job = job;
		if(type == 0) {
			this.text = "S-a adaugat un nou job! " + this.job;
		} else if(type == 1){
			this.text = "Jobul " + this.job + " s-a inchis";
		} else {
			this.text = "Din pacate nu ai primit jobul " + this.job;
		}
	}
}
