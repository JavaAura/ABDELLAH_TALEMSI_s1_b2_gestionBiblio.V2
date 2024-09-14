package Presentation;

import Metier.JournalScientifique;
import Persistance.JournalScientifiqueDaoImp;
import utilitaire.InputValidator;

import java.util.List;
import java.util.stream.Collectors;



import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

public class ConsoleUiJournal {
    private JournalScientifiqueDaoImp journalSciDaoImp;
    private Scanner scanner;
    private HashMap<String, JournalScientifique> journalScientifiqueHashMap;

    public ConsoleUiJournal(JournalScientifiqueDaoImp journalSciDaoImp) {
        this.journalSciDaoImp = journalSciDaoImp;
        this.scanner = new Scanner(System.in);
        this.journalScientifiqueHashMap = new HashMap<>();
        populateJournalScientifiqueMap();
    }

    public void gereJournal(){

        int choice;

        do {
            System.out.println("Library Management System");
            System.out.println("1. Add Journal");
            System.out.println("2. Update Journal");
            System.out.println("3. Display Journal");
            System.out.println("4. Display All Journal");
            System.out.println("5. Delete Journal");
            System.out.println("6. Exit");
            System.out.println("Enter your option (1-6): ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    ajouterJournal();
                    break;
                case 2:
                    System.out.println("Enter Journal ID to update:");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    modifierJournal(id);
                    break;
                case 3:
                    afficherJournal();
                    break;
                case 4:
                    afficherTousLesJournals();
                    break;
                case 5:
                    supprimerJournal();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 6);
    }

    private void ajouterJournal() {
        String title = getValidatedTitle();
        String author = getValidatedAuthor();
        int idJournal = getValidatedIdJournal();
        String domaineRecherche = getValidatedDomaineRecherche();
        int pages = getValidatedNumberOfPages();
        String datePublication = getValidatedPublicationDate();

        JournalScientifique journalScientifique = new JournalScientifique(title, author, LocalDate.parse(datePublication), pages, domaineRecherche, idJournal, journalSciDaoImp);
        journalScientifique.ajouterDocument();
        System.out.println("Journal added successfully.");
    }

    private void modifierJournal(int id) {
        JournalScientifique journalScientifique = journalSciDaoImp.getJournalScientifiqueById(id);

        if (journalScientifique != null) {
            System.out.println("Enter new Journal title (leave blank to keep current) (current value: " + journalScientifique.getTitle() + "):");
            String newTitle = scanner.nextLine();
            if (!newTitle.isBlank()) {
                journalScientifique.setTitle(getValidatedTitle());
            }

            System.out.println("Enter new author (leave blank to keep current) (current value: " + journalScientifique.getAuthor() + "):");
            String newAuthor = scanner.nextLine();
            if (!newAuthor.isBlank()) {
                journalScientifique.setAuthor(getValidatedAuthor());
            }

            System.out.println("Enter new publication date (YYYY-MM-DD) (leave blank to keep current) (current value: " + journalScientifique.getDate_publication() + "):");
            String newDateInput = scanner.nextLine();
            if (!newDateInput.isBlank()) {
                journalScientifique.setDate_publication(LocalDate.parse(getValidatedPublicationDate()));
            }

            System.out.println("Enter new number of pages (leave 0 to keep current) (current value: " + journalScientifique.getNombre_of_pages() + "):");
            int newNombrePages = getValidatedNumberOfPages();
            if (newNombrePages > 0) {
                journalScientifique.setNombre_of_pages(getValidatedNumberOfPages());
            }

            System.out.println("Enter new Id Journal (leave 0 to keep current) (current value: " + journalScientifique.getIdJournal() + "):");
            int newIdJournal = getValidatedIdJournal();
            if (newIdJournal > 0) {
                journalScientifique.setIdJournal(getValidatedIdJournal());
            }

            System.out.println("Enter new Domaine Recherche (leave blank to keep current) (current value: " + journalScientifique.getDomaineRechercher() + "):");
            String newDomaineRecherche = scanner.nextLine();
            if (!newDomaineRecherche.isBlank()) {
                journalScientifique.setDomaineRechercher(getValidatedDomaineRecherche());
            }

            journalScientifique.modifierDocument(id);
            System.out.println("Journal updated successfully.");
        } else {
            System.out.println("Journal not found.");
        }
    }

    private String getValidatedTitle() {
        String title;
        do {
            System.out.println("Enter Journal title (minimum 3 characters):");
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
            System.out.println("Enter Journal author (minimum 3 characters):");
            author = scanner.nextLine();
            if (!InputValidator.validateAuthor(author)) {
                System.out.println("Invalid author! Please enter at least 3 characters.");
            }
        } while (!InputValidator.validateAuthor(author));
        return author;
    }

    private int getValidatedIdJournal() {
        int idJournal;
        do {
            System.out.println("Enter ID Journal (positive integer):");
            idJournal = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            if (!InputValidator.validateNumero(idJournal)) {
                System.out.println("Invalid ID Journal! It must be a positive integer.");
            }
        } while (!InputValidator.validateNumero(idJournal));
        return idJournal;
    }

    private String getValidatedDomaineRecherche() {
        String domaineRecherche;
        do {
            System.out.println("Enter Domaine Recherche (minimum 3 characters):");
            domaineRecherche = scanner.nextLine();
            if (!InputValidator.validateTitle(domaineRecherche)) {  // Assuming similar validation as title
                System.out.println("Invalid Domaine Recherche! Please enter at least 3 characters.");
            }
        } while (!InputValidator.validateTitle(domaineRecherche));
        return domaineRecherche;
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

    public void afficherJournal() {
        System.out.println("Enter Livre ID to display:");
        int id = scanner.nextInt();
        JournalScientifique journalScientifique = journalSciDaoImp.getJournalScientifiqueById(id);
        if (journalScientifique != null) {
            journalScientifique.afficher();
        } else {
            System.out.println("Book not found.");
        }
    }

    public void afficherTousLesJournals() {
        List<JournalScientifique> journals = journalSciDaoImp.getJournalScientifiques();

        System.out.println("========== Journal ==========");

        journals.stream()
                .map(journal -> String.format(
                        "ID: %d%n" +
                                "Title: %s%n" +
                                "Author: %s%n" +
                                "Publication Date: %s%n" +
                                "Number of Pages: %d%n" +
                                "Id Journal: %d%n" +
                                "Domaine Recherche: %s%n" +
                                "Emprunter: %s%n" +
                                "Reserver: %s%n" +
                                "---------------",
                        journal.getId(),
                        journal.getTitle(),
                        journal.getAuthor(),
                        journal.getDate_publication(),
                        journal.getNombre_of_pages(),
                        journal.getIdJournal(),
                        journal.getDomaineRechercher(),
                        journal.isEstEmprunter() ? "Non disponible" : "Disponible",
                        journal.isEstReserver() ? "Non disponible" : "Disponible"
                ))
                .forEach(System.out::println);
    }


    private void supprimerJournal() {
        System.out.println("Enter Journal ID to delete:");
        int id = scanner.nextInt();
        JournalScientifique journalScientifique = journalSciDaoImp.getJournalScientifiqueById(id);
        if (journalScientifique != null) {
            journalSciDaoImp.deleteJournalScientifique(id);
            System.out.println("Journal deleted successfully!");
        }else {
            System.out.println("Livre not found.");
        }
    }

    private void populateJournalScientifiqueMap() {
        journalSciDaoImp.getJournalScientifiques().forEach(journalScientifique ->
                journalScientifiqueHashMap.put(journalScientifique.getTitle().toLowerCase(), journalScientifique)
        );
    }

    public void searchJournalScientifique() {
        System.out.println("Enter the title of the book to search:");
        String title = scanner.nextLine().toLowerCase();
        if (journalScientifiqueHashMap.containsKey(title)) {
            JournalScientifique foundJournal = journalScientifiqueHashMap.get(title);
            foundJournal.afficherJounale();
        } else {
            System.out.println("Book not found.");
        }
    }


}
