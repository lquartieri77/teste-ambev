package br.com.processorders.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class DateUtils {

    public static LocalDateTime convertStringToLocalDateTime(String dateTimeStr) {
        if (dateTimeStr == null || dateTimeStr.isEmpty()) {
            return null;
        }
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        
        // Adicionar tempo padrão para a data
        String dateTimeWithDefaultTime = dateTimeStr + " 00:00:00";
        
        try {
            return LocalDateTime.parse(dateTimeWithDefaultTime, formatter);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
