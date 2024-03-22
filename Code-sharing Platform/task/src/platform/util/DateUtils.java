package platform.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
	public static LocalDateTime formatDate(LocalDateTime date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		return LocalDateTime.parse(date.format(formatter), formatter);
	}
}
