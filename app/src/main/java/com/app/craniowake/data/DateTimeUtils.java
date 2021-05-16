package com.app.craniowake.data;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {
    private DateTimeUtils() {}

    public static String formatDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        return localDateTime.format(formatter);
    }

    public static String getCurrentDateTimeAsFormattedString() {
        return formatDateTime(LocalDateTime.now(ZoneId.systemDefault()));
    }
}
