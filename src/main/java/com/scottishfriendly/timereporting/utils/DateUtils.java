/**
 * Copyright (c) 2012 Scottish Friendly Assurance. All Rights Reserved.
 */
package com.scottishfriendly.timereporting.utils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.lang.time.DateFormatUtils;

/**
 * @author Adam Wilk
 * 
 */
public class DateUtils {

	public static final int TEN_DAYS_IN_MS = 864000000;
	
	public static final int TEN_DAYS_IN_S = 864000;

	public static enum Period {
		ONE_MONTH(2628000);
		private long periodInMilliSecs;
		private Period(long periodInSecs) {
			this.periodInMilliSecs = periodInSecs * 1000;
		}
		public Date incrementDate(Date date) {
			return new Date(date.getTime()+periodInMilliSecs);
		}
	}

	private DateUtils() {
		// Hide default constructor
	}
	
	public static Map<String, String> getDaysOfMonths() {
		int size = 31;
		return buildDateDropdownValues(size);
	}

	private static Map<String, String> buildDateDropdownValues(int size) {
		HashMap<String, String> daysOfMonth = new LinkedHashMap();
		for (int i = 0; i < size; i++) {
			String value = String.valueOf(i+1);
			if(i<9){
				value = "0"+value;
			}
			daysOfMonth.put(String.valueOf(i+1),value);			
		}
		return daysOfMonth;
	}
	
	public static Map<String, String> getMonthsOfYear() {
		int size = 12;
		return buildDateDropdownValues(size);
	}

	public static Date parseDate(String date) {

		try {
			if (date != null) {
				return org.apache.commons.lang.time.DateUtils
						.parseDateStrictly(date, new String[] { "dd-MM-yyyy",
								"ddMMyyyy", "yyyy-MM-dd" });
			}
		} catch (ParseException ignore) {
			// Ignore parsing error
		}

		return null;
	}

	public static String formatDate(Date date) {
		return date == null ? null : DateFormatUtils.format(date, "dd-MM-yyyy");
	}

	public static Date today() {
		return org.apache.commons.lang.time.DateUtils.truncate(new Date(),
				Calendar.DATE);
	}
	
	public static Date now() {
		return new Date();
	}

	public static String ordinal(Date date) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		int day = cal.get(Calendar.DATE);

		if (day == 1 || day == 21 || day == 31) {
			return "st";
		}

		if (day == 2 || day == 22) {
			return "nd";
		}

		if (day == 3 || day == 23) {
			return "rd";
		}

		return "th";
	}

	public static boolean isSameDayNullSafe(Date date1, Date date2) {
        if (date1 != null && date2 != null) {
            return org.apache.commons.lang.time.DateUtils.isSameDay(date1, date2);
        }

        if (date1 == null && date2 != null) {
            return false;
        }

        return date1 == null;

    }

	public static String createDateString(String day,
			String month, String year) {
		return day + "/" + month + "/" + year;
	}
	
	public static Date incrementDateByDaysIgnoringWeekends(Date dateToCheck, int days) {
		Calendar calendarPlusFiveDays = Calendar.getInstance();
		calendarPlusFiveDays.setTime(dateToCheck);
		for (int i = 0; i < days; i++) {
			skipWeekendDays(calendarPlusFiveDays);
			calendarPlusFiveDays.add(Calendar.DATE, 1);
		}

		Date fiveDaysFromDateToCheck = calendarPlusFiveDays.getTime();
		return fiveDaysFromDateToCheck;
	}

	public static Date findClosestWorkingDay(Date paymentDate) {
		Calendar paymentCal = Calendar.getInstance();
		paymentCal.setTime(paymentDate);
		skipWeekendDays(paymentCal);
		return paymentCal.getTime();
	}

	public static void skipWeekendDays(Calendar calendarPlusFiveDays) {
		if (calendarPlusFiveDays.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			calendarPlusFiveDays.add(Calendar.DAY_OF_WEEK, 1);
		}
		if (calendarPlusFiveDays.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			calendarPlusFiveDays.add(Calendar.DAY_OF_WEEK, 1);
		}
	}

    public static boolean isDateInTheFuture(Date directDebitDatePlusDaysLimit) {
        return System.currentTimeMillis() < directDebitDatePlusDaysLimit.getTime();
    }
    
    public static Date getMidnightTonight() {
        GregorianCalendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }
    
    public static int getDayOfMonth(Date date) {
        GregorianCalendar nextPaymentDate = new GregorianCalendar();
        nextPaymentDate.setTime(date);
        return nextPaymentDate.get(Calendar.DAY_OF_MONTH);
    }

	public static Date futureDate(Period period) {		
		return period.incrementDate(now());
	}
}
