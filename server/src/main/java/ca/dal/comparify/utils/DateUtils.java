package ca.dal.comparify.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {

    private DateUtils(){}

    private static final ZoneId defaultZoneId = ZoneId.systemDefault();

    public static Date now(){
        return  Date.from(LocalDate.now().atStartOfDay(defaultZoneId).toInstant());
    }

    public static Date addToNow(long toAdd){
        return new Date(now().getTime() + toAdd);
    }

}
