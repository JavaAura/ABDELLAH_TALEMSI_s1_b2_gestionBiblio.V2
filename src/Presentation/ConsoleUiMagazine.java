package Presentation;

import Metier.Magazine;
import Persistance.MagazineDaoImp;
import utilitaire.InputValidator;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;

public class ConsoleUiMagazine {
    private MagazineDaoImp magazineDaoImp;
    private Scanner scanner;
    private HashMap<String, Magazine> magazineMap;

    // Functional interface for validation
    @FunctionalInterface
    interface Validator<T> {
        boolean validate(T input);
    }

    // Functional interface for input retrieval
    @FunctionalInterface
    interface InputRetriever<T> {
        T retrieve();
    }

    public ConsoleUiMagazine(MagazineDaoImp magazineDaoImp) {
        this.magazineDaoImp = magazineDaoImp;
        this.scanner = new Scanner(System.in);
        this.magazineMap = new HashMap<>();
        populateMagazineMap();
    }

    public void gereMagazine() {
        int choice;

        do {
            System.out.println("Library Management System");
            System.out.println("1-Add Magazine");
            System.out.println("2-Update Magazine");
            System.out.println("3-Display Magazine");
            System.out.println("4-Display All Magazine");
            System.out.println("5. Delete Magazine");
            System.out.println("6. Exit");
            System.out.println("Enter your option (1-6): ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    ajouterMagazine();
                    break;
                case 2:
                    System.out.println("Enter Magazine ID to update:");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    modifierMagazine(id);
                    break;
                case 3:
                    afficherMagazine();
                    break;
                case 4:
                    afficherTousLesMagazines();
                    break;
                case 5:
                    supprimerMagazine();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 6);
    }

    private void ajouterMagazine() {
        String title = getValidatedInput("Enter Magazine title (minimum 3 characters):", InputValidator::validateTitle, scanner::nextLine);
        String author = getValidatedInput("Enter Magazine author (minimum 3 characters):", InputValidator::validateAuthor, scanner::nextLine);
        int numero = getValidatedInput("Enter Numero (positive integer):", InputValidator::validateNumero, scanner::nextInt);
        int pages = getValidatedInput("Enter number of pages (positive integer):", InputValidator::validatePageCount, scanner::nextInt);
        scanner.nextLine(); // Consume newline
        String datePublication = getValidatedInput("Enter publication date (YYYY-MM-DD):", InputValidator::validatePublicationDate, scanner::nextLine);

        Magazine magazine = new Magazine(title, author, LocalDate.parse(datePublication), pages, numero, magazineDaoImp);
        magazine.ajouterDocument();
        System.out.println("Magazine added successfully.");
    }

    private void modifierMagazine(int id) {
        Magazine magazine = magazineDaoImp.getMagazineById(id);

        if (magazine != null) {
            System.out.println("Enter new Magazine title (leave blank to keep current) (current value: " + magazine.getTitle() + "):");
            String newTitle = getUpdatedInput(magazine.getTitle(), InputValidator::validateTitle, scanner::nextLine);
            magazine.setTitle(newTitle);

            System.out.println("Enter new author (leave blank to keep current) (current value: " + magazine.getAuthor() + "):");
            String newAuthor = getUpdatedInput(magazine.getAuthor(), InputValidator::validateAuthor, scanner::nextLine);
            magazine.setAuthor(newAuthor);

            System.out.println("Enter new publication date (YYYY-MM-DD) (leave blank to keep current) (current value: " + magazine.getDate_publication() + "):");
            String newDateInput = getUpdatedInput(magazine.getDate_publication().toString(), InputValidator::validatePublicationDate, scanner::nextLine);
            magazine.setDate_publication(LocalDate.parse(newDateInput));

            System.out.println("Enter new number of pages (leave 0 to keep current) (current value: " + magazine.getNombre_of_pages() + "):");
            int newNombrePages = getUpdatedIntegerInput(magazine.getNombre_of_pages(), InputValidator::validatePageCount, scanner::nextInt);
            magazine.setNombre_of_pages(newNombrePages);

            System.out.println("Enter new Numero (leave 0 to keep current) (current value: " + magazine.getNumero() + "):");
            int newNumero = getUpdatedIntegerInput(magazine.getNumero(), InputValidator::validateNumero, scanner::nextInt);
            magazine.setNumero(newNumero);

            magazineDaoImp.updateMagazine(magazine, id);
            System.out.println("Magazine updated successfully.");
        } else {
            System.out.println("Magazine not found.");
        }
    }

    private <T> T getValidatedInput(String prompt, Validator<T> validator, InputRetriever<T> retriever) {
        T input;
        do {
            System.out.println(prompt);
            input = retriever.retrieve();
            if (!validator.validate(input)) {
                System.out.println("Invalid input! Please try again.");
            }
        } while (!validator.validate(input));
        return input;
    }

    private String getUpdatedInput(String currentValue, Validator<String> validator, InputRetriever<String> retriever) {
        System.out.println("Enter new value (leave blank to keep current) (current value: " + currentValue + "):");
        String input = retriever.retrieve();
        return input.isBlank() ? currentValue : getValidatedInput("", validator, () -> input);
    }

    private int getUpdatedIntegerInput(int currentValue, Validator<Integer> validator, InputRetriever<Integer> retriever) {
        System.out.println("Enter new value (leave 0 to keep current) (current value: " + currentValue + "):");
        int input = retriever.retrieve();
        return input == 0 ? currentValue : getValidatedInput("", validator, () -> input);
    }

    public void afficherMagazine() {
        System.out.println("Enter Magazine ID to display:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Magazine magazine = magazineDaoImp.getMagazineById(id);
        if (magazine != null) {
            magazine.afficher();
        } else {
            System.out.println("Magazine not found.");
        }
    }

    public void afficherTousLesMagazines() {
        List<Magazine> magazines = magazineDaoImp.getMagazines();

        System.out.println("========== Magazines ==========");

        magazines.stream()
                .map(magazine -> String.format(
                        "ID: %d%n" +
                                "Title: %s%n" +
                                "Author: %s%n" +
                                "Publication Date: %s%n" +
                                "Number of Pages: %d%n" +
                                "Numero: %d%n" +
                                "---------------",
                        magazine.getId(),
                        magazine.getTitle(),
                        magazine.getAuthor(),
                        magazine.getDate_publication(),
                        magazine.getNombre_of_pages(),
                        magazine.getNumero()
                ))
                .forEach(System.out::println);
    }

    private void supprimerMagazine() {
        System.out.println("Enter Magazine ID to delete:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Magazine magazine = magazineDaoImp.getMagazineById(id);
        if (magazine != null) {
            magazineDaoImp.deleteMagazine(id);
            System.out.println("Magazine deleted successfully!");
        } else {
            System.out.println("Magazine not found.");
        }
    }

    private void populateMagazineMap() {
        magazineDaoImp.getMagazines().forEach(magazine ->
                magazineMap.put(magazine.getTitle().toLowerCase(), magazine)
        );
    }

    public void searchMagazine() {
        System.out.println("Enter the title of the magazine to search:");
        String title = scanner.nextLine().toLowerCase();  // Convert input to lowercase for case-insensitive search
        if (magazineMap.containsKey(title)) {
            Magazine foundMagazine = magazineMap.get(title);
            foundMagazine.afficherMagazine();  // Display the found magazine
        } else {
            System.out.println("Magazine not found.");
        }
    }
}
