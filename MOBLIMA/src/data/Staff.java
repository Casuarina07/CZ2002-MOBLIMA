package data;

public class Staff {

	private String staffID, staffName, staffPwd;

	public Staff(String staffID, String staffName, String staffPwd) {
		super();
		this.staffID = staffID;
		this.staffName = staffName;
		this.staffPwd = staffPwd;
	}

	public String getStaffID() {
		return staffID;
	}

	public String getStaffName() {
		return staffName;
	}

	public String getStaffPwd() {
		return staffPwd;
	}

	public boolean createMovie() {
		return true;
	}

	public boolean updateMovie() {
		return true;
	}

	public boolean removeMovie() {
		return true;
	}

	public void listTop5Movie() {

	}

	public void configureSystem() {

	}

}
