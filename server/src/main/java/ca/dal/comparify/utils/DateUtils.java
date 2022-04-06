package ca.dal.comparify.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateUtils {

    private static final ZoneId DEFAULT_ZONE_ID = ZoneId.systemDefault();

    private DateUtils() {
    }

    /*------------------  Instant ------------------*/

    /**
     * @return
     * @author Harsh Shah
     */
    public static ZonedDateTime zoneNow() {
        return Instant.now().atZone(DEFAULT_ZONE_ID);
    }

    /*------------------  Date ------------------*/

    /**
     * @return
     * @author Harsh Shah
     */
    public static Date dateNow() {
        return Date.from(Instant.from(Instant.now().atZone(DEFAULT_ZONE_ID)));
    }

    /**
     * @param seconds
     * @return
     * @author Harsh Shah
     */
    public static Date addSecondsToDateNow(long seconds) {
        return new Date(dateNow().getTime() + seconds);
    }

    /*------------------  Local Date ------------------*/

    /**
     * @return
     * @author Harsh Shah
     */
    public static LocalDate localNow() {
        return zoneNow().toLocalDate();
    }

    /**
     * @param seconds
     * @return
     * @author Harsh Shah
     */
    public static LocalDate addSecondsToLocalNow(long seconds) {
        return localNow().plus(seconds, ChronoUnit.SECONDS);
    }

    /**
     * @param days
     * @return
     * @author Harsh Shah
     */
    public static LocalDate addDaysToLocalNow(long days) {
        return localNow().plusDays(days);
    }

    /**
     * @param localDate
     * @return
     * @author Harsh Shah
     */
    public static boolean isAfterNow(LocalDate localDate) {
        return localNow().isAfter(localDate);
    }


    /**
     * @param date yyyy-MM-dd
     * @return
     * @author Harsh Shah
     */
    public static LocalDate parse(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
    }
}
