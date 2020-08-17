package lrsystemsweb.com.br.savepetapp.core.util;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DataUtil {

    private static DateTimeFormatter dateTimeFormater
            = DateTimeFormat.forPattern("dd/MM/yyyy hh:mm:ss");
    private static DateTimeFormatter dateFormater
            = DateTimeFormat.forPattern("dd/MM/yyyy");

    public static LocalDateTime toLocalDateTimeBr(String dataString) {
        if (dataString != null) {
            return LocalDateTime.parse(dataString, dateTimeFormater);
        }
        return null;
    }

    public static LocalDate toLocalDate(String dataString) {
        if (dataString != null) {
            return LocalDate.parse(dataString, dateFormater);
        }
        return null;
    }

    public static String toString(LocalDateTime dateTime) {
        if (dateTime != null) {
            return dateTime.toString(dateTimeFormater);
        }
        return null;
    }

    public static String toString(LocalDate date) {
        if (date != null) {
            return date.toString(dateFormater);
        }
        return null;
    }

}
