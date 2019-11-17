package control;

import java.time.LocalDate;
import java.util.Calendar;

/**
 * This class implements the PriceController controller that controls the the
 * prices of a ticket.
 * 
 * 
 * @author David Loh Shun Hao
 * 
 * @since 2019-11-13
 */
public class PriceController {

	/**
	 * Rates of tickets.
	 * <li>{@link #CHILDREN}</li>
	 * <li>{@link #SENIOR}</li>
	 * <li>{@link #WEEKEND}</li>
	 * <li>{@link #ADULT}</li>
	 * <li>{@link #HOLIDAY}</li>
	 * <li>{@link #STUDENT}</li>
	 * 
	 * @author David Loh Shun Hao
	 *
	 */
	public enum RATES {
		/**
		 * Rate for children's ticket.
		 */
		CHILDREN(0.8d),
		/**
		 * Rate for senior citizen's ticket.
		 */
		SENIOR(0.5d),
		/**
		 * Rate for weekend ticket.
		 */
		WEEKEND(1.2d),
		/**
		 * Rate for holiday ticket.
		 */
		HOLIDAY(1.5d),
		/**
		 * Rate for adult ticket
		 */
		ADULT(0.7d),
		/**
		 * Rate for student ticket
		 */
		STUDENT(0.6d);

		private final double rate;

		RATES(final double rate) {
			this.rate = rate;
		}

		/**
		 * Gets rate of the price.
		 * 
		 * @return The rate of the price.
		 */
		public double getRate() {
			return rate;
		}
	}

	private static PriceController controller;

	/**
	 * Initialise a new PriceController object.
	 * 
	 * @return PriceController object.
	 */
	public static PriceController getInstance() {
		if (controller == null) {
			controller = new PriceController();
		}
		return controller;
	}

	/**
	 * Calculate the price of a ticket.
	 * 
	 * @param standardPrice The standard price of ticket.
	 * @param choice        Integer number indicating if the user is booking for a
	 *                      children, senior or none of them.
	 * 
	 * @return The price of the ticket after applying the rates.
	 */
	public static double calculateTicketPrice(double standardPrice, int choice) {

		boolean isChildren = false, isSenior;
		switch (choice) {
		case 1:
			isSenior = true;
			break;
		case 2:
			isChildren = true;
		default:
			isSenior = false;
			isChildren = false;
			break;
		}

//		if (isHoliday) {
//			// Calculates the additional amount to pay during the holidays
//			return standardPrice * RATES.HOLIDAY.getRate();
//		} else 
		if (isWeekend()) {
			// Calculates the additional amount to pay during the weekends
			return standardPrice * RATES.WEEKEND.getRate();
		} else if (!isWeekend()) {
			// Calculates the additional amount to pay for children and seniors during the
			// weekdays
			if (isChildren) {
				return standardPrice * RATES.CHILDREN.getRate();
			} else if (isSenior) {
				return standardPrice * RATES.SENIOR.getRate();
			}
		}
		return standardPrice;
	}

	private static boolean isWeekend() {
		// Retrieve system's date
		LocalDate date = LocalDate.now();

		// Returns false for weekend, true for weekday
		if (date.getDayOfWeek().getValue() + 1 == Calendar.SATURDAY
				|| date.getDayOfWeek().getValue() + 1 == Calendar.SUNDAY)
			return true;
		else
			return false;
	}
}
