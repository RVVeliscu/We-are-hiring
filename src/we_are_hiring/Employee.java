package we_are_hiring;

public class Employee extends Consumer{
	private String companie;
	private int salariu;
	
	public Employee() {
		super();
		this.companie = null;
		this.salariu = 0;
	}
	
	public Employee(String companie, int salariu) {
		super();
		this.companie = companie;
		this.salariu = salariu;
	}
	
	public Employee(String nume, String prenume, String email, String telefon, String data, String sex) {
		super(nume, prenume, email, telefon, data, sex);
		this.companie = null;
		this.salariu = 0;
	}
	
	public Employee(String nume, String prenume, String email, String telefon, String data, String sex, String companie, int salariu) {
		super(nume, prenume, email, telefon, data, sex);
		this.companie = companie;
		this.salariu = salariu;
	}
	
	public int getSalary() {
		return this.salariu;
	}
	
	public Employee setSalary(int salariu) {
		this.salariu = salariu;
		return this;
	}
	
	public Employee setCompany(String companie) {
		this.companie = companie;
		return this;
	}
	
	public String getNumeCompanie() {
		return this.companie;
	}
}
