package com.app.craniowake.data.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public interface CurrentDateTimable { //TODO: Besser als eine abstrakte Klasse, bei der sowohl das Attribut als auch die Methode zum Setzen final sind. Der defaultConstructor muss diese Methode aufrufen und muss ausgeführt werden!

    /**
     * returns current Date and Time when called.
     */
    default void applyCurrentDateTimeAsStringTo(String dateTime) {
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.systemDefault());
        dateTime = formatDateTime(localDateTime);
    }

    /**
     * @param localDateTime formats generated datetime to: JJJJ-MM-DDT00:00:00.000
     */
    default String formatDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        return localDateTime.format(formatter);
    }
}
