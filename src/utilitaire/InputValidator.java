package utilitaire;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class InputValidator {

    public static boolean validateTitle(String title) {
        return title != null && !title.trim().isEmpty() && title.trim().length() >= 3;
    }


    public static boolean validateAuthor(String author) {
        String nameRegex = "^[\\p{L} .'-]+$";
        return author != null && !author.trim().isEmpty() && author.trim().length() >= 3 && Pattern.matches(nameRegex, author);
    }

    public static boolean validatePublicationDate(String date) {
        try {
            LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean validatePageCount(int pageCount) {
        return pageCount > 0;
    }

    public static boolean validateId(int id) {
        return id > 0;
    }

    public static boolean validateDomaineRecherche(String domaine) {
        return domaine != null && !domaine.trim().isEmpty();
    }

    public static boolean validateEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email != null && Pattern.matches(emailRegex, email);
    }

    public static boolean validateISBN(String isbn) {
        // ISBN-10: 10 chiffres ou ISBN-13: 13 chiffres
        String isbn10Regex = "^(?:[0-9]{9}X|[0-9]{10})$";
        String isbn13Regex = "^(?:[0-9]{13})$";
        return isbn != null && (Pattern.matches(isbn10Regex, isbn) || Pattern.matches(isbn13Regex, isbn));
    }

    // Valider un numéro pour Magazine et Thèse (doit être un entier positif)
    public static boolean validateNumero(int numero) {
        return numero > 0;
    }

    // Valider le domaine universitaire pour Thèse (ne doit pas être vide)
    public static boolean validateDomaineUniversitaire(String domaine) {
        return domaine != null && !domaine.trim().isEmpty();
    }
}
