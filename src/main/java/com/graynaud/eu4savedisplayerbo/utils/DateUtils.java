package com.graynaud.eu4savedisplayerbo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private DateUtils() {}

    public static String convertDateToStringDTO(Date date) {
        if (date == null) {
            return null;
        }

        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
}
