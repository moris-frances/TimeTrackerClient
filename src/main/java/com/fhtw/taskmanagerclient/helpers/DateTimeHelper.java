package com.fhtw.taskmanagerclient.helpers;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class DateTimeHelper {
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static String getWeekStartDate() {

        Calendar calendar = Calendar.getInstance();
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DATE, -1);
        }

        return dateFormat.format(calendar.getTime()).toString();
    }

    public static String getWeekEndDate() {
        Calendar calendar = Calendar.getInstance();
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DATE, 1);
        }
        calendar.add(Calendar.DATE, -1);
        return dateFormat.format(calendar.getTime()).toString();
    }

    public static String getMonthStartDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return dateFormat.format(calendar.getTime()).toString();
    }
    public static String getMonthEndDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return dateFormat.format(calendar.getTime()).toString();
    }

    public static String getFormattedDateString(java.time.LocalDate date) {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        return dateFormat.format(Date.from(date.atStartOfDay(defaultZoneId).toInstant())).toString();
    }
}
