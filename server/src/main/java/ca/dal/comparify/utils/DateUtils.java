package ca.dal.comparify.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;

public class DateUtils {

    private DateUtils(){}

    private static final ZoneId DEFAULT_ZONE_ID = ZoneId.systemDefault();

    public static Date now(){
        return  Date.from(localNow().atStartOfDay(DEFAULT_ZONE_ID).toInstant());
    }

    public static Date addSecondsToNow(long seconds){
        return new Date(now().getTime() + seconds);
    }

    public static boolean isAfterNow(Date localDate){
        return localNow().isAfter(localDate.toInstant().atZone(DEFAULT_ZONE_ID).toLocalDate());
    }

    /*------------------  Local Date ------------------*/

    public static LocalDate localNow(){
        return LocalDate.now(DEFAULT_ZONE_ID);
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
