package ca.dal.comparify.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateUtils {

    private DateUtils(){}

    private static final ZoneId DEFAULT_ZONE_ID = ZoneId.systemDefault();

    /*------------------  Instant ------------------*/

    public static ZonedDateTime zoneNow(){
        return Instant.now().atZone(DEFAULT_ZONE_ID);
    }

    /*------------------  Date ------------------*/

    public static Date dateNow(){
        return  Date.from(Instant.from(Instant.now().atZone(DEFAULT_ZONE_ID)));
    }

    public static Date addSecondsToDateNow(long seconds){
        return new Date(dateNow().getTime() + seconds);
    }

    /*------------------  Local Date ------------------*/

    public static LocalDate localNow(){
        return zoneNow().toLocalDate();
    }

    public static LocalDate addSecondsToLocalNow(long seconds){
        return localNow().plus(seconds, ChronoUnit.SECONDS);
    }

    public static LocalDate addDaysToLocalNow(long days){
        return localNow().plusDays(days);
    }

    public static boolean isAfterNow(LocalDate localDate){
        return localNow().isAfter(localDate);
    }
}
