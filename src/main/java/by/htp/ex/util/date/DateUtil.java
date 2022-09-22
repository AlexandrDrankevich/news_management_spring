package by.htp.ex.util.date;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public final class DateUtil {
    private DateUtil() {
    }

    private static final String dateFormatPattern = "yyyy-MM-dd";

    public static java.sql.Date getDate() {
        Date current = Date.from(Instant.now());
        java.sql.Date date = new java.sql.Date(current.getTime());
        return date;
    }

    public static java.sql.Date convertStrToDate(String dateStr) {
        return java.sql.Date.valueOf(dateStr);
    }

    public static String convertDateToStr(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);
        return dateFormat.format(date.getTime());
    }
}
