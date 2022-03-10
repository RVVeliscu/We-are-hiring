package we_are_hiring;

public class DepartmentFactory {
	public static Department createDepartment(String type) {
		if(type.equals("IT"))
			return new IT();
		if(type.equals("Marketing"))
			return new Marketing();
		if(type.equals("Management"))
			return new Management();
		if(type.equals("Finance"))
			return new Finance();
		return null;
	}
}
