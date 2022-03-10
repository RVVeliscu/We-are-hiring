package we_are_hiring;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Information {
	private String nume, prenume, email, telefon, sex;
	private Date nastere;
	private HashMap<String,String> limbi;
	
	{
		limbi = new HashMap<String, String>();
	}
	
	public Information() {
		this.nume = null;
		this.prenume = null;
		this.email = null;
		this.telefon = null;
		this.sex = null;
		this.nastere = null;
	}
	
	public Information(String nume, String prenume, String email, String telefon, String data, String sex) {
		this.nume = nume;
		this.prenume = prenume;
		this.email = email;
		this.telefon = telefon;
		this.sex = sex;
		
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		
		try {
			this.nastere = df.parse(data);			
		} catch(ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void addLanguage(String lang, String level) {
		this.limbi.put(lang, level);
	}
	
	public String getNume() {
		return this.nume;
	}
	
	public String getPrenume() {
		return this.prenume;
	}
	
	public String getEmail() {
		return this.email;
	}

	public String getTelefon() {
		return this.telefon;
	}
	
	public String getSex() {
		return this.sex;
	}
	
	public String getData() {
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		return df.format(this.nastere);
	}
	
	public void setNume(String nume) {
		this.nume = nume;
	}
	
	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	
	public void setData(String data) {
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		
		try {
			this.nastere = df.parse(data);			
		} catch(ParseException e) {
			e.printStackTrace();
		}
	}
	
	
}