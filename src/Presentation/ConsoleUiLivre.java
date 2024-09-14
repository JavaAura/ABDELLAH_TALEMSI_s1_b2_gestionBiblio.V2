package Presentation;

import Metier.Livre;
import Persistance.LivreDaoImp;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import utilitaire.InputValidator;

public class ConsoleUiLivre {
    private LivreDaoImp livreDaoImp;
    private Scanner scanner;
    private HashMap<String, Livre> livreMap;

    // Functional interfaces for input validation
    @FunctionalInterface
    interface Validator<T> {
        boolean validate(T input);
    }

    public ConsoleUiLivre(LivreDaoImp livreDaoImp) {
        this.livreDaoImp = livreDaoImp;
        this.scanner = new Scanner(System.in);
        this.livreMap = new HashMap<>();
        populateLivreMap();
    }

    public void gereBooks() {
        int choice;

        do {
            System.out.println("Library Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Update Book");
            System.out.println("3. Display Book");
            System.out.println("4. Display All Books");
            System.out.println("5. Delete Book");
            System.out.println("6. Search Book");
            System.out.println("7. Exit");
            System.out.println("Enter your option (1-7): ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    ajouterLivre();
                    break;
                case 2:
                    System.out.println("Enter Livre ID to update:");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    modifierLivre(id);
                    break;
                case 3:
                    afficherLivre();
                    break;
                case 4:
                    afficherTousLesLivres();
                    break;
                case 5:
                    supprimerLivre();
                    break;
                case 6:
                    searchLivre();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 7);
    }

    private void ajouterLivre() {
        String title = getValidatedInput("Enter Livre title (minimum 3 characters):", InputValidator::validateTitle);
        String author = getValidatedInput("Enter Livre author (minimum 3 characters):", InputValidator::validateAuthor);
        String isbn = getValidatedInput("Enter ISBN(minimum 10 number with X like this (123456789X)):", InputValidator::validateISBN);
        int pages = getValidatedIntegerInput("Enter number of pages (positive integer):", InputValidator::validatePageCount);
        String datePublication = getValidatedInput("Enter publication date (YYYY-MM-DD):", InputValidator::validatePublicationDate);

        Livre livre = new Livre(title, author, LocalDate.parse(datePublication), pages, isbn, livreDaoImp);
        livre.ajouterDocument();
        System.out.println("Livre added successfully.");
    }

    private void modifierLivre(int id) {
        Livre livre = livreDaoImp.getLivreById(id);

        if (livre != null) {
            String newTitle = getUpdatedValue("Enter new Livre title (leave blank to keep current):", livre.getTitle(), InputValidator::validateTitle);
            if (!newTitle.isBlank()) livre.setTitle(newTitle);

            String newAuthor = getUpdatedValue("Enter new author (leave blank to keep current):", livre.getAuthor(), InputValidator::validateAuthor);
            if (!newAuthor.isBlank()) livre.setAuthor(newAuthor);

            String newDateInput = getUpdatedValue("Enter new publication date (YYYY-MM-DD) (leave blank to keep current):", livre.getDate_publication().toString(), InputValidator::validatePublicationDate);
            if (!newDateInput.isBlank()) livre.setDate_publication(LocalDate.parse(newDateInput));

            int newNombrePages = getUpdatedIntegerValue("Enter new number of pages (leave 0 to keep current):", livre.getNombre_of_pages(), InputValidator::validatePageCount);
            if (newNombrePages > 0) livre.setNombre_of_pages(newNombrePages);

            String newIsbn = getUpdatedValue("Enter new ISBN (leave blank to keep current):", livre.getIsbn(), InputValidator::validateISBN);
            if (!newIsbn.isBlank()) livre.setIsbn(newIsbn);

            livreDaoImp.updateLivre(livre, id); // Update Livre in the database
            System.out.println("Livre updated successfully.");
        } else {
            System.out.println("Livre not found.");
        }
    }

    private String getValidatedInput(String prompt, Validator<String> validator) {
        String input;
        do {
            System.out.println(prompt);
            input = scanner.nextLine();
        } while (!validator.validate(input));
        return input;
    }

    private int getValidatedIntegerInput(String prompt, Validator<Integer> validator) {
        int input;
        do {
            System.out.println(prompt);
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Clear the invalid input
                System.out.println(prompt);
            }
            input = scanner.nextInt();
            scanner.nextLine(); // Consume newline
        } while (!validator.validate(input));
        return input;
    }

    private String getUpdatedValue(String prompt, String currentValue, Validator<String> validator) {
        System.out.println(prompt + " (current value: " + currentValue + "):");
        String input = scanner.nextLine();
        return input.isBlank() ? currentValue : getValidatedInput("", validator);
    }

    private int getUpdatedIntegerValue(String prompt, int currentValue, Validator<Integer> validator) {
        System.out.println(prompt + " (current value: " + currentValue + "):");
        int input = getValidatedIntegerInput("", validator);
        return input == 0 ? currentValue : input;
    }

    public void afficherLivre() {
        System.out.println("Enter Livre ID to display:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Livre livre = livreDaoImp.getLivreById(id);
        if (livre != null) {
            livre.afficher();
        } else {
            System.out.println("Book not found.");
        }
    }

    public void afficherTousLesLivres() {
        List<Livre> livres = livreDaoImp.getLivres();

        System.out.println("========== Livres ==========");

        livres.stream()
                .map(livre -> String.format(
                        "ID: %d%n" +
                                "Title: %s%n" +
                                "Author: %s%n" +
                                "Publication Date: %s%n" +
                                "Number of Pages: %d%n" +
                                "ISBN: %s%n" +
                                "Emprunter: %s%n" +
                                "Reserver: %s%n" +
                                "---------------",
                        livre.getId(),
                        livre.getTitle(),
                        livre.getAuthor(),
                        livre.getDate_publication(),
                        livre.getNombre_of_pages(),
                        livre.getIsbn(),
                        livre.isEstEmprunter() ? "Non disponible" : "Disponible",
                        livre.isEstReserver() ? "Non disponible" : "Disponible"
                ))
                .forEach(System.out::println);
    }

    private void supprimerLivre() {
        System.out.println("Enter Livre ID to delete:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Livre livre = livreDaoImp.getLivreById(id);
        if (livre != null) {
            livreDaoImp.deleteLivre(id);
            System.out.println("Livre deleted successfully!");
        } else {
            System.out.println("Livre not found.");
        }
    }

    private void populateLivreMap() {
        livreDaoImp.getLivres().forEach(livre ->
                livreMap.put(livre.getTitle().toLowerCase(), livre)
        );
    }

    public void searchLivre() {
        System.out.println("Enter the title of the book to search:");
        String title = scanner.nextLine().toLowerCase();  // Convert input to lowercase for case-insensitive search
        if (livreMap.containsKey(title)) {
            Livre foundLivre = livreMap.get(title);
            foundLivre.afficher();  // Display the found book
        } else {
            System.out.println("Book not found.");
        }
    }
}
