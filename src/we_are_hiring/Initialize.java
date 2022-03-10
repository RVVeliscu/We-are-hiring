package we_are_hiring;

import java.util.ArrayList;
import java.io.FileReader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class Initialize{
		public static void Init() throws Exception {
			Object obj = new JSONParser().parse(new FileReader("consumers.json"));
			JSONObject db = (JSONObject) obj;
			//getting the managers
			JSONArray ja = (JSONArray) db.get("managers");
			Iterator itrArr = ja.iterator();
			
			while(itrArr.hasNext()) {
				JSONObject current = (JSONObject) itrArr.next();
				
				String name = (String) current.get("name");
				StringTokenizer strArr = new StringTokenizer(name);
				String prenume = strArr.nextToken();
				String nume = strArr.nextToken();
				
                String email = (String) current.get("email");
                String telefon = (String) current.get("phone");
                String data = (String) current.get("date_of_birth");
                String sex = (String) current.get("genre");
                int salariu = ((Long) current.get("salary")).intValue();
                
                Manager m = new Manager(nume, prenume, email, telefon, data, sex);
                m.setSalary(salariu);
                
                @SuppressWarnings("unchecked")
				List<String> languages = (List<String>) current.get("languages");
                @SuppressWarnings("unchecked")
				List<String> languagesLevel = (List<String>) current.get("languages_level");
                for(int i = 0; i < languages.size(); i++) {
                	m.resume.info.addLanguage(languages.get(i), languagesLevel.get(i));
                }
                
                JSONArray jEducation = (JSONArray) current.get("education");
                Iterator itrEducation = jEducation.iterator();
                
                while (itrEducation.hasNext()) {
                	JSONObject educatie = (JSONObject) itrEducation.next();
                	String sfarsit;
                	try {
                		sfarsit = (String) educatie.get("end_date");
                	} catch (NullPointerException exception) {
                		sfarsit = null;
                	}
                	String inceput = (String) educatie.get("start_date");
                    String institutie = (String) educatie.get("name");
                    String nivel = (String) educatie.get("level");
                    Double medie = (Double) educatie.get("grade");
                    try {
                    	m.add(new Education(inceput, sfarsit, institutie, nivel, medie));
                    } catch (InvalidDatesException exception) {
                    	exception.printStackTrace();
            		}
                }
                
                JSONArray jExperience = (JSONArray) current.get("experience");
                Iterator itrExperience = jExperience.iterator();
                
                while (itrExperience.hasNext()) {
                	JSONObject experienta = (JSONObject) itrExperience.next();
                	
                	String inceput = (String) experienta.get("start_date");
                    String companie = (String) experienta.get("company");
                    String pozitie = (String) experienta.get("position");
                    
                    m.add(new Experience(inceput, null, companie, pozitie));
                    m.setCompany(companie);
                    Application.getInstance().add(new Company(companie, m)); 
                    Application.getInstance().getCompany(companie).add(DepartmentFactory.createDepartment("Management"));
                    Application.getInstance().getCompany(companie).add( (Employee) m, Application.getInstance().getCompany(companie).getDepartamente().firstElement());
                    }
			}
			Application.getInstance().getCompany("Google").add(DepartmentFactory.createDepartment("IT"));
			Application.getInstance().getCompany("Amazon").add(DepartmentFactory.createDepartment("IT"));
			//adding some jobs
			for(Department dep : Application.getInstance().getCompany("Google").getDepartamente()) {
				if(dep.getClass() == DepartmentFactory.createDepartment("IT").getClass()) {
//					Job(String numeCompanie, int numarNecesar, int salariu, Constraint anAbsolvire, Constraint aniExperienta, Constraint gpa)
    				dep.add(new Job("Google", 1, 10000, new Constraint(2002, 2020), new Constraint(2, 6), new Constraint(8, 11)));
    				dep.add(new Job("Google", 1, 5000, null, new Constraint(0, 2), new Constraint(9, 11)));
    			}
			}
			
			for(Department dep : Application.getInstance().getCompany("Amazon").getDepartamente()) {
				if(dep.getClass() == DepartmentFactory.createDepartment("IT").getClass()) {
//					Job(String numeCompanie, int numarNecesar, int salariu, Constraint anAbsolvire, Constraint aniExperienta, Constraint gpa)
    				dep.add(new Job("Amazon", 1, 12000, new Constraint(2014, 2020), new Constraint(1, 100), new Constraint(9, 11)));
    				dep.add(new Job("Amazon", 1, 6000, null, new Constraint(0, 2), new Constraint(9.35, 11.0)));
    			}
			}
			
			//getting the employees
			ja = (JSONArray) db.get("employees");
			itrArr = ja.iterator();
			
			while(itrArr.hasNext()) {
				JSONObject current = (JSONObject) itrArr.next();
				
				String name = (String) current.get("name");
				StringTokenizer strArr = new StringTokenizer(name);
				String prenume = strArr.nextToken();
				String nume = strArr.nextToken();
				
                String email = (String) current.get("email");
                String telefon = (String) current.get("phone");
                String data = (String) current.get("date_of_birth");
                String sex = (String) current.get("genre");
                int salariu = ((Long) current.get("salary")).intValue();
                
                Employee e = new Employee(nume, prenume, email, telefon, data, sex);
                
                @SuppressWarnings("unchecked")
				List<String> languages = (List<String>) current.get("languages");
                @SuppressWarnings("unchecked")
				List<String> languagesLevel = (List<String>) current.get("languages_level");
                for(int i = 0; i < languages.size(); i++) {
                	e.resume.info.addLanguage(languages.get(i), languagesLevel.get(i));
                }
                
                JSONArray je = (JSONArray) current.get("education");
                Iterator itr = je.iterator();
                
                while (itr.hasNext()) {
                	//Education(String inceput, String sfarsit, String institutie, String nivel, double medie) 
                	JSONObject educatie = (JSONObject) itr.next();
                	String sfarsit;
                	try {
                		sfarsit = (String) educatie.get("end_date");
                	} catch (NullPointerException exception) {
                		sfarsit = null;
                	}
                	String inceput = (String) educatie.get("start_date");
                    String institutie = (String) educatie.get("name");
                    String nivel = (String) educatie.get("level");
                    Double medie = (Double) educatie.get("grade");
                    try {
                    	e.add(new Education(inceput, sfarsit, institutie, nivel, medie));
                    } catch (InvalidDatesException exception) {
                    	exception.printStackTrace();
            		}
                }
                
                JSONArray jExperience = (JSONArray) current.get("experience");
                Iterator itrExperience = jExperience.iterator();
                
                while (itrExperience.hasNext()) {
                	JSONObject experienta = (JSONObject) itrExperience.next();
                	String sfarsit;
                	try {
                		sfarsit = (String) experienta.get("end_date");
                	} catch (NullPointerException exception) {
                		sfarsit = null;
                	}
                	
                	String inceput = (String) experienta.get("start_date");
                    String companie = (String) experienta.get("company");
                    String pozitie = (String) experienta.get("position");
                    if(sfarsit != null) {
	                    try {
	                    	e.add(new Experience(inceput, sfarsit, companie, pozitie));
	                    } catch (InvalidDatesException exception) {
	                    	exception.printStackTrace();
	            		}
                    } else {
                    	String departament = (String) experienta.get("department");
                    	e.setSalary(salariu);
                    	e.setCompany(companie);
                    	try {
                    		boolean ok = false;
                    		for (Department dep : Application.getInstance().getCompany(companie).getDepartamente()) {
                    			if(dep.getClass() == DepartmentFactory.createDepartment(departament).getClass()) {
                    				ok = true;
                    				dep.add(e);
                    			}
                    		}
                    		
                    		if(!ok) {
                    			Application.getInstance().getCompany(companie).add(DepartmentFactory.createDepartment(departament));
                    			Application.getInstance().getCompany(companie).getDepartamente().lastElement().add(e);
                    			Application.getInstance().getCompany(companie).add(e, Application.getInstance().getCompany(companie).getDepartamente().lastElement());
                    		}
                    	} catch (Exception exceptie) {
                    		exceptie.printStackTrace();
                    	}
                    }
                }
			}
			
			//getting the recruiters
			ja = (JSONArray) db.get("recruiters");
			itrArr = ja.iterator();
			
			while(itrArr.hasNext()) {
				JSONObject current = (JSONObject) itrArr.next();
				
				String name = (String) current.get("name");
				StringTokenizer strArr = new StringTokenizer(name);
				String prenume = strArr.nextToken();
				String nume = strArr.nextToken();
				
                String email = (String) current.get("email");
                String telefon = (String) current.get("phone");
                String data = (String) current.get("date_of_birth");
                String sex = (String) current.get("genre");
                int salariu = ((Long) current.get("salary")).intValue();
                
                Recruiter e = new Recruiter(nume, prenume, email, telefon, data, sex);
                
                @SuppressWarnings("unchecked")
				List<String> languages = (List<String>) current.get("languages");
                @SuppressWarnings("unchecked")
				List<String> languagesLevel = (List<String>) current.get("languages_level");
                for(int i = 0; i < languages.size(); i++) {
                	e.resume.info.addLanguage(languages.get(i), languagesLevel.get(i));
                }
                
                JSONArray je = (JSONArray) current.get("education");
                Iterator itr = je.iterator();
                
                while (itr.hasNext()) {
                	//Education(String inceput, String sfarsit, String institutie, String nivel, double medie) 
                	JSONObject educatie = (JSONObject) itr.next();
                	String sfarsit;
                	try {
                		sfarsit = (String) educatie.get("end_date");
                	} catch (NullPointerException exception) {
                		sfarsit = null;
                	}
                	String inceput = (String) educatie.get("start_date");
                    String institutie = (String) educatie.get("name");
                    String nivel = (String) educatie.get("level");
                    Double medie = (Double) educatie.get("grade");
                    try {
                    	e.add(new Education(inceput, sfarsit, institutie, nivel, medie));
                    } catch (InvalidDatesException exception) {
                    	exception.printStackTrace();
            		}
                }
                
                JSONArray jExperience = (JSONArray) current.get("experience");
                Iterator itrExperience = jExperience.iterator();
                
                while (itrExperience.hasNext()) {
                	JSONObject experienta = (JSONObject) itrExperience.next();
                	String sfarsit;
                	try {
                		sfarsit = (String) experienta.get("end_date");
                	} catch (NullPointerException exception) {
                		sfarsit = null;
                	}
                	
                	String inceput = (String) experienta.get("start_date");
                    String companie = (String) experienta.get("company");
                    String pozitie = (String) experienta.get("position");
                    if(sfarsit != null) {
	                    try {
	                    	e.add(new Experience(inceput, sfarsit, companie, pozitie));
	                    } catch (InvalidDatesException exception) {
	                    	exception.printStackTrace();
	            		}
                    } else {
                    	e.setSalary(salariu);
                    	e.setCompany(companie);
                    	try {
                    		boolean ok = false;
                    		for (Department dep : Application.getInstance().getCompany(companie).getDepartamente()) {
                    			if(dep.getClass() == IT.class) {
                    				ok = true;
                    				dep.add(e);
                    			}
                    		}
                    		
                    		if(!ok) {
                    			Application.getInstance().getCompany(companie).add(DepartmentFactory.createDepartment("IT"));
                    			Application.getInstance().getCompany(companie).getDepartamente().lastElement().add(e);
                    		}
                    	} catch (Exception exceptie) {
                    		exceptie.printStackTrace();
                    	}
                    	Application.getInstance().getCompany(companie).add(e);
                    }
                }
			}
			
			//getting the users
			ja = (JSONArray) db.get("users");
			itrArr = ja.iterator();
			
			while(itrArr.hasNext()) {
				JSONObject current = (JSONObject) itrArr.next();
				
				String name = (String) current.get("name");
				StringTokenizer strArr = new StringTokenizer(name);
				String prenume = strArr.nextToken();
				String nume = strArr.nextToken();
				
                String email = (String) current.get("email");
                String telefon = (String) current.get("phone");
                String data = (String) current.get("date_of_birth");
                String sex = (String) current.get("genre");
                
                @SuppressWarnings("unchecked")
				List<String> interests = (List<String>) current.get("interested_companies");
                User e = new User(nume, prenume, email, telefon, data, sex, interests);
                
                @SuppressWarnings("unchecked")
				List<String> languages = (List<String>) current.get("languages");
                @SuppressWarnings("unchecked")
				List<String> languagesLevel = (List<String>) current.get("languages_level");
                for(int i = 0; i < languages.size(); i++) {
                	e.resume.info.addLanguage(languages.get(i), languagesLevel.get(i));
                }
                
                JSONArray je = (JSONArray) current.get("education");
                Iterator itr = je.iterator();
                
                while (itr.hasNext()) {
                	//Education(String inceput, String sfarsit, String institutie, String nivel, double medie) 
                	JSONObject educatie = (JSONObject) itr.next();
                	String sfarsit;
                	try {
                		sfarsit = (String) educatie.get("end_date");
                	} catch (NullPointerException exception) {
                		sfarsit = null;
                	}
                	String inceput = (String) educatie.get("start_date");
                    String institutie = (String) educatie.get("name");
                    String nivel = (String) educatie.get("level");
                    Double medie = (Double) educatie.get("grade");
                    try {
                    	e.add(new Education(inceput, sfarsit, institutie, nivel, medie));
                    } catch (InvalidDatesException exception) {
                    	exception.printStackTrace();
            		}
                }
                
                JSONArray jExperience = (JSONArray) current.get("experience");
                Iterator itrExperience = jExperience.iterator();
                
                while (itrExperience.hasNext()) {
                	JSONObject experienta = (JSONObject) itrExperience.next();
                	String sfarsit = (String) experienta.get("end_date");
                	
                	String inceput = (String) experienta.get("start_date");
                    String companie = (String) experienta.get("company");
                    String pozitie = (String) experienta.get("position");
                    try {
	                    	e.add(new Experience(inceput, sfarsit, companie, pozitie));
	                    } catch (InvalidDatesException exception) {
	                    	exception.printStackTrace();
	            		}
                    }
               e.applyToAll();
                
//               System.out.println(((User)Application.getInstance().getCompany(interests.get(0)).getObservers().get(0)).resume.info.getNume());
			}
		}
}