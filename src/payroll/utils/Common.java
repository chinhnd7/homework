package payroll.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Common {

	public static float getDuration(String date) {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
	    LocalDate dateParsed = LocalDate.parse(date, dateFormatter);
	    return dateParsed.until(LocalDate.now(), ChronoUnit.DAYS) / 365.2425f;
	}
}
