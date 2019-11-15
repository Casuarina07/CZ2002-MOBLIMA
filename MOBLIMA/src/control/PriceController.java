package control;

import java.time.LocalDate;
import java.util.Calendar;

public class PriceController {

	public enum RATES {
		CHILDREN(-0.2d), SENIOR(-0.5d), WEEKEND(0.5d), HOLIDAY(0.7d);

		private final double rate;

		RATES(final double rate) {
			this.rate = rate;
		}

		public double getRate() {
			return rate;
		}
	}

	private static PriceController controller;

	public static PriceController getInstance() {
		if (controller == null) {
			controller = new PriceController();
		}
		return controller;
	}

	private double calculateTicketPrice(double standardPrice) {
		double addtional = 0d;

		if (isWeekend()) {
			// Calculates the additional amount to pay during the weekends
			addtional = standardPrice * RATES.WEEKEND.getRate();
		}

//		 if (isHoliday) {
//			// Calculates the additional amount to pay during the holidays
//			addtional = standardPrice * RATES.HOLIDAY.getRate();
//		} else if (isWeekend()) {
//			// Calculates the additional amount to pay during the weekends
//			addtional = standardPrice * RATES.WEEKEND.getRate();
//		} else if (!isWeekend()) {
//			// Calculates the additional amount to pay for children and seniors during the
//			// weekdays
//			if (isChildren()) {
//				addtional = standardPrice * RATES.CHILDREN.getRate();
//			} else if (isSenior()) {
//				addtional = standardPrice * RATES.SENIOR.getRate();
//			}
//		}
		return standardPrice + addtional;
	}

	public boolean isWeekend() {
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
