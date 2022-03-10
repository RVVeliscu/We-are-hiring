package we_are_hiring;

import java.text.*;
import java.util.Date;

public class Education implements Comparable<Education>{
	public Date startDate, finishDate;
	public String institution, level;
	public Double medie;
	
	public Education(String inceput, String sfarsit, String institutie, String nivel, Double medie) throws InvalidDatesException{
		this.institution = institutie;
		this.level = nivel;
		this.medie = medie;
		
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		
		try {
			this.startDate = df.parse(inceput);
			this.finishDate = df.parse(sfarsit);
		} catch(ParseException e) {
			e.printStackTrace();
		} catch(NullPointerException e) {
			this.finishDate = null;
		}
		
		if(this.finishDate != null && this.finishDate.compareTo(this.startDate) < 0) {
			throw new InvalidDatesException("Data de sfarsit este inaintea celei de inceput!");
		}
	}
	
	@Override
	public int compareTo(Education e) {
		if(this.finishDate != null && e.finishDate != null) {
			if(this.finishDate.compareTo(e.finishDate) > 0) {
				return -1;
			} else if(this.finishDate.compareTo(e.finishDate) < 0) {
				return 1;
			} else {
				return (int)(this.medie - e.medie);
			}
		} else {
			if(this.startDate.compareTo(e.startDate) > 0) {
				return 1;
			} else {
				return -1;
			}
		}
	}
}
