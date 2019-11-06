package model;

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
}
