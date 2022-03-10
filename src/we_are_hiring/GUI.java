package we_are_hiring;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

//@SuppressWarnings("serial")
public class GUI extends JFrame{
	public static JPanel prev;
	public static JPanel prevPage;
	{
		prev = null;
	}
	public static User searchUser(String name) {
		User u = null;
		boolean ok = false;
		for(Company c : Application.getInstance().getCompanies()) {
			for(Observer o : c.getObservers()) {
				if(((User)o).resume.info.getNume().equals(name)) {
					u = (User)o;
					ok = true;
					break;
				}
			}
			if(ok) {
				break;
			}
		}
		
		return u;
	}
	
	public static void pagina(JFrame jFrame, String type) {
		JPanel jpanel1 = new JPanel();
		JTextField cauta;
		JButton inputButton;
		JScrollPane scrPane = new JScrollPane(jpanel1);
        jFrame.getContentPane().add(scrPane);
		try {
    		jFrame.remove(prevPage);
    		SwingUtilities.updateComponentTreeUI(jFrame);
    	} catch(NullPointerException exceptie) {
//    		System.out.println("Exceptie");
    		prevPage = null;
    	}
		
		switch(type) {
		case "Admin Page":
			System.out.println("Admin");
			ArrayList<Company> companii = Application.getInstance().getCompanies();
			ArrayList<String> nume = new ArrayList<String>();
			nume.add("Select Company");
			for(Company c : companii) {
				nume.add(c.numeCompanie);
			}
			String[] simpleArray = new String[ nume.size() ];
			nume.toArray( simpleArray );
			
			JComboBox<String> jComboBox = new JComboBox<>(simpleArray);
	        jpanel1.add(jComboBox);
	        jFrame.add(jpanel1, BorderLayout.CENTER);
			SwingUtilities.updateComponentTreeUI(jFrame);
			
			jComboBox.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	String comp = jComboBox.getItemAt(jComboBox.getSelectedIndex()).toString();
	            	JPanel jpanel2 = new JPanel();
	                jpanel2.setMaximumSize(new Dimension(450, 700));;
	            	try {
	            		jpanel1.remove(prev);
	            		SwingUtilities.updateComponentTreeUI(jpanel1);
	            		SwingUtilities.updateComponentTreeUI(jpanel2);
	            		SwingUtilities.updateComponentTreeUI(jFrame);
	            	} catch(NullPointerException exceptie) {
	            		prev = null;
	            	}
	            	jpanel1.add(jpanel2, BorderLayout.SOUTH);
	                JLabel jl = null;
	                StringBuilder sb = new StringBuilder("<html>");
	                Vector<Department> departamente = Application.getInstance().getCompany(comp).getDepartamente();
	                for(Department d : departamente) {
	                	ArrayList<Employee> employees = d.getEmployees();
	                	ArrayList<Observer> observers = d.getObservers();
	                	ArrayList<Job> jobs = d.getJobs();
	                	
	                	sb.append("Employees:<br />");
	                	for(Employee emp : employees) {
	                		sb.append(emp.resume.info.getNume() + " " + emp.resume.info.getPrenume() + "<br />");
	                	}
	                	
	                	sb.append("<br />Users:<br />");
	                	for(Observer obs : observers) {
	                		sb.append(((User)obs).resume.info.getNume() + " " + ((User)obs).resume.info.getPrenume() + "<br />");
	                	}
	                	
//	                	public int getNumarNecesar() {
//	                		return this.numarNecesar;
//	                	}
//	                	
//	                	public int getSalariu() {
//	                		return this.salariu;
//	                	}
	                	//public boolean disponibilitate;
	                	
	                	sb.append("<br />Jobs:<br />");
	                	for(Job job : jobs) {
	                		sb.append( "Disponibility: " + job.disponibilitate+
                								" Positions available: " + job.getNumarNecesar() +
                									" Salary: " + job.getSalariu() + "<br />");
	                	}
	                	
	                }
	                sb.append("</html>");
	                jl = new JLabel(sb.toString(),  SwingConstants.CENTER);
	                jpanel2.add(jl);
	                
	                SwingUtilities.updateComponentTreeUI(jpanel1);
	                SwingUtilities.updateComponentTreeUI(jpanel2);
	                SwingUtilities.updateComponentTreeUI(jFrame);
	                prev = jpanel2;
	                
	            }
	        });
			break;
			
		case "Manager Page":
			System.out.println("Manager");
			break;
			
		case "Profile Page":
			cauta = new JTextField("Type the name of the user");
			cauta.setColumns(18);
			inputButton = new JButton("Search!");
			jpanel1.add(cauta);
			jpanel1.add(inputButton);
			jpanel1.setMaximumSize(new Dimension(500, 100));
			jFrame.add(jpanel1, BorderLayout.CENTER);
			SwingUtilities.updateComponentTreeUI(jFrame);
			
			cauta.addMouseListener(new MouseAdapter() {
				  @Override
				  public void mouseClicked(MouseEvent e) {
					  cauta.setText("");
				  }
			});
			
			inputButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                User found = GUI.searchUser(cauta.getText());
	                JPanel jpanel2 = new JPanel();
	                jpanel2.setMaximumSize(new Dimension(450, 700));;
	            	try {
	            		jpanel1.remove(prev);
	            		SwingUtilities.updateComponentTreeUI(jpanel1);
	            		SwingUtilities.updateComponentTreeUI(jpanel2);
	            		SwingUtilities.updateComponentTreeUI(jFrame);
	            	} catch(NullPointerException exceptie) {
	            		prev = null;
	            	}

	                jpanel1.add(jpanel2, BorderLayout.SOUTH);
	                JLabel jl = null;
	                if(found == null) {
	                	jl = new JLabel("User not found!");
	                	jpanel2.add(jl);
	                	prev = jpanel2;
	                	SwingUtilities.updateComponentTreeUI(jpanel1);
	                	SwingUtilities.updateComponentTreeUI(jpanel2);
	                	SwingUtilities.updateComponentTreeUI(jFrame);
	                } else {
	                	StringBuilder sb = new StringBuilder("<html>Name: " + found.resume.info.getNume() +"<br />" +
															    							"Prenume: " + found.resume.info.getPrenume() + "<br />" +
															    							"Email: " + found.resume.info.getEmail() + "<br />" +
															    							"Telefon: " + found.resume.info.getTelefon()+ "<br />" +
															    							"Data nasterii: " + found.resume.info.getData()+ "<br />" +
															    							"Sex: " + found.resume.info.getSex() + "<br /><br />");
	                	if(!found.resume.experienta.isEmpty()) {
	                		sb.append("Experienta:<br />");
	                	}
	                	for(Experience experienta : found.resume.experienta) {
	                		sb.append("&emsp Companie - " + experienta.company + ";<br /> &emsp Pozitie - " + experienta.position + "<br /><br />");
	                	}
	                	
	                	if(!found.resume.educatie.isEmpty()) {
	                		sb.append("Educatie:<br />");
	                	}
	                	for(Education educatie : found.resume.educatie) {
	                		sb.append("&emsp Institution - " + educatie.institution + ";<br /> &emsp Level - " + educatie.level + ";<br /> &emsp Grade -  " + educatie.medie + "<br /><br />");
	                	}
	                	sb.append("</html>");
	                	
	                	jl = new JLabel(sb.toString(),  SwingConstants.CENTER);
	                	jpanel2.add(jl);
	                	
	                	SwingUtilities.updateComponentTreeUI(jpanel1);
	                	SwingUtilities.updateComponentTreeUI(jpanel2);
	                	SwingUtilities.updateComponentTreeUI(jFrame);
	                	prev = jpanel2;
	                }
	            }
	        });
			
			break;
			
		default:
			System.out.println("Default");
			break;
		}
		prevPage = jpanel1;
}
	
	public GUI(String titlu) {
		String[] optionsToChoose = {"Select Page", "Admin Page", "Manager Page", "Profile Page"};

        JFrame jFrame = new JFrame(titlu);
        JPanel jpanel = new JPanel();
        JComboBox<String> jComboBox = new JComboBox<>(optionsToChoose);
        
        jpanel.add(jComboBox);
        jFrame.add(jpanel, BorderLayout.NORTH);
        jFrame.setSize(550, 750);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
//        GUI.pagina(jFrame, "Admin Page");
        
        jComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String type = jComboBox.getItemAt(jComboBox.getSelectedIndex()).toString();
            	GUI.pagina(jFrame, type);
            }
        });
		pack ();
//		show ();
	}
	
	public static void main(String[] args) {
		try {
			Initialize.Init();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		new GUI ("We are hiring");
		
	}
}
