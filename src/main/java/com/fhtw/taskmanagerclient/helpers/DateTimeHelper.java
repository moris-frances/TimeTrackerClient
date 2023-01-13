package com.fhtw.taskmanagerclient.helpers;

import java.text.SimpleDateFormat;
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
}
