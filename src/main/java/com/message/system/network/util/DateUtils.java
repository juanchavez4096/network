package com.message.system.network.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {

    private static String dateFormat = "yyyyMMdd HH:mm:ss";
    private static Logger log = LogManager.getLogger();

    public static Date dateFromUtc(Date date, String timezone) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        SimpleDateFormat sdfNewTimezone = new SimpleDateFormat(dateFormat);
        TimeZone tzNewTimezone = TimeZone.getTimeZone(timezone);
        sdfNewTimezone.setTimeZone(tzNewTimezone);

        String sDateNewTimezone = sdfNewTimezone.format(date); // Convert to String first

        Date dateNewTimezone = formatter.parse(sDateNewTimezone); // Create a new Date object

        return dateNewTimezone;
    }
}
