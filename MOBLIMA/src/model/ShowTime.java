package model;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Gwyn Bong Xiao Min
 * @since 2019-11-09
 */
public class ShowTime extends Date{
	private int hours, minutes;
	private int day, month, year;
	private static ArrayList<String> holidayList = new ArrayList<>();
	
	/**
	 * Empty Constructor
	 */
	public ShowTime() {
		super();
	}
	
	/**
	 * Constructor
	 * @param hours
	 * @param minutes
	 * @param day
	 * @param month
	 * @param year
	 */
	public ShowTime(int hours, int minutes, int day, int month, int year) {
		super();
		this.hours = hours;
		this.minutes = minutes;
		this.day = day;
		this.month = month;
		this.year = year;
	}

	/**
	 * @return holidayList : a list of holidays
	 */
	public static ArrayList<String> getHolidayList(){
		return holidayList;
	}
}
