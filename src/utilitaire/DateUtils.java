package utilitaire;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter custom_formatter2 = DateTimeFormatter.ofPattern("dd MMM yyyy");

    public static LocalDate date(String date) {
        try {
            return LocalDate.parse(date, formatter);
        }catch (DateTimeParseException e) {
            System.out.println("error dans la forma de date :" + e.getMessage());
            return null;
        }
    }
    public static String date2(LocalDate date) {
        return custom_formatter2.format(date);
    }
}
