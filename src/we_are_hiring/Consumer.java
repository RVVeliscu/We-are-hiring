package we_are_hiring;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

public abstract class Consumer {
	class Resume{
		public Information info;
		public ArrayList<Education> educatie;
		public ArrayList<Experience> experienta;
		public ArrayList<Consumer> cunoscuti;
		
		{
			this.educatie = new ArrayList<Education>();
			this.experienta = new ArrayList<Experience>();
			this.cunoscuti = new ArrayList<Consumer>();
		}
		
		public Resume() {
			info = new Information();
		}
		
		public Resume(String nume, String prenume, String email, String telefon, String data, String sex) {
			info = new Information(nume, prenume, email, telefon, data, sex);
		}
	}
	
	protected Resume resume;
	
	public Consumer() {
		this.resume = new Resume();
	}
	
	public Consumer(String nume, String prenume, String email, String telefon, String data, String sex) {
		this.resume = new Resume(nume, prenume, email, telefon, data, sex);
	}
	
	public void add(Education education) {
		resume.educatie.add(education);
	}
	
	public void add(Experience experience) {
		resume.experienta.add(experience);
	}
	
	public void add(Consumer consumer) {
		resume.cunoscuti.add(consumer);
	}
	
	
	public int getDegreeInFriendship(Consumer consumer) {
		Vector<Pair<Consumer, Integer>> coada = new Vector<>();
		Vector<Consumer> vizitat = new Vector<>();
//		vizitat.add(this);
		coada.add(new Pair<Consumer, Integer>(this, 0));
		
		while(!coada.isEmpty()) {
			if(coada.firstElement().getKey().equals(consumer)) {
				return coada.firstElement().getValue();
			} else {
				if(!vizitat.contains(coada.firstElement().getKey())) {
					vizitat.add(coada.firstElement().getKey());
					for(Consumer cunoscut : coada.firstElement().getKey().resume.cunoscuti) {
						if(!vizitat.contains(cunoscut))
							coada.add(new Pair<Consumer, Integer>(cunoscut, coada.firstElement().getValue()+1));
					}
				}
				coada.remove(0);
			}
		}
		return 0;
	}
	
	public void remove(Consumer consumer) {
		resume.cunoscuti.remove(consumer);
	}
	
	public Integer getGraduationYear() {
		Date absolvire  = resume.educatie.get(0).finishDate;
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");  
		String dateParts[] = df.format(absolvire).split("-");
		return Integer.parseInt(dateParts[2]);
	}
	
	public Double meanGPA() {
		Double sum = 0.0;
		for(Education e : resume.educatie) {
			sum += e.medie;
		}
		return (sum/resume.educatie.size());
	}
	
	public Integer getYear(Date date) {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");  
		String dateParts[] = df.format(date).split("-");
		return Integer.parseInt(dateParts[2]);
	}
	
	public Integer getMonth(Date date) {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");  
		String dateParts[] = df.format(date).split("-");
		return Integer.parseInt(dateParts[1]);
	}
	
//	public Integer getDay(Date date) {
//		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");  
//		String dateParts[] = df.format(date).split(".");
//		return Integer.parseInt(dateParts[0]);
//	}
	
	public int getTotalExperience() {
		int luni = 0, ani = 0;
		for(Experience e : this.resume.experienta) {
			ani += this.getYear(e.finishDate) - this.getYear(e.startDate);
			luni += this.getMonth(e.finishDate) - this.getMonth(e.startDate) + 12;
		}
		while(luni >= 3) {
			ani++;
			luni -= 12;
		}
		return ani;
	}
}
