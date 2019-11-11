package model;

import java.util.ArrayList;


/**
 * @author Gwyn Bong Xiao Min
 * @since 2019-11-07
 */
public class Staff {

	private String staffID, staffName, staffPwd;
	private static ArrayList<Staff> staffList = new ArrayList<>();

	public Staff(String staffID, String staffName, String staffPwd) {
		super();
		this.staffID = staffID;
		this.staffName = staffName;
		this.staffPwd = staffPwd;
	}
	
	public Staff(String staffName, String staffPwd) {
		super();
		this.staffName = staffName;
		this.staffPwd = staffPwd;
	}

	public Staff() {
	}
	
	/**
	 * 
	 * @return A list of Staff users
	 */
	public static ArrayList<Staff> getStaffList() {
		return staffList;
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

	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public void setStaffPwd(String staffPwd) {
		this.staffPwd = staffPwd;
	}
	
	
}
