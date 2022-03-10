package we_are_hiring;

public class Constraint {
	double minim_d, maxim_d;
	int minim, maxim;
	
	public Constraint(double minim, double maxim) {
		this.minim_d = minim;
		this.maxim_d = maxim;
	}
	
	public Constraint(int minim, int maxim) {
		this.minim = minim;
		this.maxim = maxim;
	}
	
	public boolean check(int val) {
		if(val < maxim && val > minim)
			return true;
		return false;
	}
	
	public boolean check(double val) {
		if(val < maxim_d && val > minim_d)
			return true;
		return false;
	}
}
