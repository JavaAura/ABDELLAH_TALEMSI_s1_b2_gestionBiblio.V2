package Presentation;

import Metier.Magazine;
import Metier.TheseUniversitaire;
import Persistance.TheseUniversitaireDaoImp;
import utilitaire.InputValidator;

import java.util.List;
import java.util.stream.Collectors;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

public class ConsoleUiThese {
    private TheseUniversitaireDaoImp theseUniversitaireDaoImp;
    private Scanner scanner;
    private HashMap<String, TheseUniversitaire> theseUniversitaireHashMap;

    public ConsoleUiThese(TheseUniversitaireDaoImp theseUniversitaireDaoImp) {
        this.theseUniversitaireDaoImp = theseUniversitaireDaoImp;
        scanner = new Scanner(System.in);
        this.theseUniversitaireHashMap = new HashMap<>();
        populateTheseUniversitaireMap();
    }

    public void gereThere() {
        int choice;

        do {
            System.out.println("Library Management System");
            System.out.println("1. Add These");
            System.out.println("2. Update These");
            System.out.println("3. Display These");
            System.out.println("4. Display All These");
            System.out.println("5. Delete These");
            System.out.println("6. Exit");
            System.out.println("Enter your option (1-6): ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    ajouterThese();
                    break;
                case 2:
                    System.out.println("Enter These ID to update:");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    modifierThese(id);
                    break;
                case 3:
                    afficherThese();
                    break;
                case 4:
                    afficherToutesLesTheses();
                    break;
                case 5:
                    supprimerThese();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 6);
    }

    private void ajouterThese() {
        String title = getValidatedTitle();
        String author = getValidatedAuthor();
        int idThese = getValidatedIdThese();
        String domaine = getValidatedDomaine();
        String universite = getValidatedUniversite();
        int pages = getValidatedNumberOfPages();
        String datePublication = getValidatedPublicationDate();

        TheseUniversitaire these = new TheseUniversitaire(title, author, LocalDate.parse(datePublication), pages, idThese, domaine, universite, theseUniversitaireDaoImp);
        these.ajouterDocument();
        System.out.println("These added successfully.");
    }

    private void modifierThese(int id) {
        TheseUniversitaire these = theseUniversitaireDaoImp.getTheseUniversitaireById(id);

        if (these != null) {
            System.out.println("Enter new These title (leave blank to keep current) (current value: " + these.getTitle() + "):");
            String newTitle = scanner.nextLine();
            if (!newTitle.isBlank()) these.setTitle(getValidatedTitle());

            System.out.println("Enter new author (leave blank to keep current) (current value: " + these.getAuthor() + "):");
            String newAuthor = scanner.nextLine();
            if (!newAuthor.isBlank()) these.setAuthor(getValidatedAuthor());

            System.out.println("Enter new publication date (YYYY-MM-DD) (leave blank to keep current) (current value: " + these.getDate_publication() + "):");
            String newDateInput = scanner.nextLine();
            if (!newDateInput.isBlank()) {
                these.setDate_publication(LocalDate.parse(getValidatedPublicationDate()));
            }

            System.out.println("Enter new number of pages (leave 0 to keep current) (current value: " + these.getNombre_of_pages() + "):");
            int newNombrePages = getValidatedNumberOfPages();
            if (newNombrePages > 0) these.setNombre_of_pages(newNombrePages);

            System.out.println("Enter new Domaine (leave blank to keep current) (current value: " + these.getDomaine() + "):");
            String newDomaine = scanner.nextLine();
            if (!newDomaine.isBlank()) these.setDomaine(getValidatedDomaine());

            System.out.println("Enter new Universite (leave blank to keep current) (current value: " + these.getUniversitaire() + "):");
            String newUniversite = scanner.nextLine();
            if (!newUniversite.isBlank()) these.setUniversitaire(getValidatedUniversite());

            System.out.println("Enter new ID These (leave 0 to keep current) (current value: " + these.getId_theseUniversitaire() + "):");
            int newIdThese = getValidatedIdThese();
            if (newIdThese > 0) these.setId_theseUniversitaire(newIdThese);

            these.modifierDocument(id);
            System.out.println("These updated successfully!");
        } else {
            System.out.println("These not found.");
        }
    }

    private String getValidatedTitle() {
        String title;
        do {
            System.out.println("Enter These title (minimum 3 characters):");
            title = scanner.nextLine();
            if (!InputValidator.validateTitle(title)) {
                System.out.println("Invalid title! Please enter at least 3 characters.");
            }
        } while (!InputValidator.validateTitle(title));
        return title;
    }

    private String getValidatedAuthor() {
        String author;
        do {
            System.out.println("Enter These author (minimum 3 characters):");
            author = scanner.nextLine();
            if (!InputValidator.validateAuthor(author)) {
                System.out.println("Invalid author! Please enter at least 3 characters.");
            }
        } while (!InputValidator.validateAuthor(author));
        return author;
    }

    private int getValidatedIdThese() {
        int idThese;
        do {
            System.out.println("Enter ID These (positive integer):");
            idThese = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            if (!InputValidator.validateNumero(idThese)) {
                System.out.println("Invalid ID These! It must be a positive integer.");
            }
        } while (!InputValidator.validateNumero(idThese));
        return idThese;
    }

    private String getValidatedDomaine() {
        String domaine;
        do {
            System.out.println("Enter Domaine (minimum 3 characters):");
            domaine = scanner.nextLine();
            if (!InputValidator.validateTitle(domaine)) { // Assuming similar validation as title
                System.out.println("Invalid Domaine! Please enter at least 3 characters.");
            }
        } while (!InputValidator.validateTitle(domaine));
        return domaine;
    }

    private String getValidatedUniversite() {
        String universite;
        do {
            System.out.println("Enter Universite (minimum 3 characters):");
            universite = scanner.nextLine();
            if (!InputValidator.validateTitle(universite)) { // Assuming similar validation as title
                System.out.println("Invalid Universite! Please enter at least 3 characters.");
            }
        } while (!InputValidator.validateTitle(universite));
        return universite;
    }

    private int getValidatedNumberOfPages() {
        int pages;
        do {
            System.out.println("Enter number of pages (positive integer):");
            pages = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            if (!InputValidator.validatePageCount(pages)) {
                System.out.println("Invalid number of pages! It must be a positive integer.");
            }
        } while (!InputValidator.validatePageCount(pages));
        return pages;
    }

    private String getValidatedPublicationDate() {
        String datePublication;
        do {
            System.out.println("Enter publication date (YYYY-MM-DD):");
            datePublication = scanner.nextLine();
            if (!InputValidator.validatePublicationDate(datePublication)) {
                System.out.println("Invalid date! Please enter a valid past date in the format YYYY-MM-DD.");
            }
        } while (!InputValidator.validatePublicationDate(datePublication));
        return datePublication;
    }

    public void afficherThese() {
        System.out.println("Enter These ID to display:");
        int id = scanner.nextInt();
        TheseUniversitaire these = theseUniversitaireDaoImp.getTheseUniversitaireById(id);
        if (these != null) {
            these.afficher();
        } else {
            System.out.println("These not found.");
        }
    }

    public void afficherToutesLesTheses() {
        List<TheseUniversitaire> theses = theseUniversitaireDaoImp.getTheseUniversitaires();
        System.out.println("========== Theses ==========");
        theses.stream()
                .map(these -> String.format(
                        "ID Document: %d%n" +
                                "Title: %s%n" +
                                "Author: %s%n" +
                                "Publication Date: %s%n" +
                                "Number of Pages: %d%n" +
                                "ID These: %d%n" +
                                "Domaine: %s%n" +
                                "Universite: %s%n" +
                                "Emprunter: %s%n" +
                                "Reserver: %s%n" +
                                "---------------",
                        these.getId(),
                        these.getTitle(),
                        these.getAuthor(),
                        these.getDate_publication(),
                        these.getNombre_of_pages(),
                        these.getId_theseUniversitaire(),
                        these.getDomaine(),
                        these.getUniversitaire(),
                        these.isEstEmprunter() ? "Non disponible" : "Disponible",
                        these.isEstReserver() ? "Non disponible" : "Disponible"
                ))
                .forEach(System.out::println);
    }

    private void supprimerThese() {
        System.out.println("Enter These ID to delete:");
        int id = scanner.nextInt();
        TheseUniversitaire these = theseUniversitaireDaoImp.getTheseUniversitaireById(id);
        if (these != null) {
            theseUniversitaireDaoImp.deleteTheseUniversitaire(id);
            System.out.println("These deleted successfully!");
        } else {
            System.out.println("These not found.");
        }
    }

    private void populateTheseUniversitaireMap() {
        theseUniversitaireDaoImp.getTheseUniversitaires().forEach(theseUniversitaire ->
                theseUniversitaireHashMap.put(theseUniversitaire.getTitle().toLowerCase(), theseUniversitaire)
        );
    }

    public void searchThese() {
        System.out.println("Enter the title of the book to search:");
        String title = scanner.nextLine().toLowerCase();  // Convert input to lowercase for case-insensitive search
        if (theseUniversitaireHashMap.containsKey(title)) {
            TheseUniversitaire foundThese = theseUniversitaireHashMap.get(title);
            foundThese.afficherThese();
        } else {
            System.out.println("Book not found.");
        }
    }

}
