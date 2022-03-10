package we_are_hiring;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Experience implements Comparable<Experience>{
	public Date startDate, finishDate;
	public String company, position;
	
	public Experience(String inceput, String sfarsit, String companie, String pozitie) throws InvalidDatesException{
		this.company = companie;
		this.position = pozitie;
		
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
	public int compareTo(Experience e) {
		if(this.finishDate != null && e.finishDate != null) {
			if(this.finishDate.compareTo(e.finishDate) > 0) {
				return -1;
			} else if(this.finishDate.compareTo(e.finishDate) < 0) {
				return 1;
			} else {
				// not sure if ok
				return this.company.compareTo(e.company);
			}
		} else {
			return this.company.compareTo(e.company);
		}
	}
}
