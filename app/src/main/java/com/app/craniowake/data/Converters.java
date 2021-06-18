package com.app.craniowake.data;

import android.graphics.Path;

import androidx.room.TypeConverter;

import com.app.craniowake.data.model.gameModels.Equation;
import com.app.craniowake.data.model.gameModels.Operator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.IllegalFormatException;
import java.util.StringTokenizer;

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

    @TypeConverter
    public static String equationToString(Equation equation) {
        return equation.toString();
    }

    @TypeConverter
    public static Equation fromString(String e) {
        return Equation.fromString(e);
    }
}
