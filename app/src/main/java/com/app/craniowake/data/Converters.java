package com.app.craniowake.data;

import androidx.room.TypeConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Converters {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

    @TypeConverter
    public static LocalDateTime fromTimestamp(String timestamp) {
        return LocalDateTime.parse(timestamp, FORMATTER);
    }

    @TypeConverter
    public static String localDateTimeToTimestamp(LocalDateTime time) {
        return time.format(FORMATTER);
    }
}
