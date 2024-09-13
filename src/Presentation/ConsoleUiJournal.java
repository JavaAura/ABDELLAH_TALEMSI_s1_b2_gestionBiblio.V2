package Presentation;

import Metier.JournalScientifique;
import Persistance.JournalScientifiqueDaoImp;

import java.time.LocalDate;
import java.util.Scanner;

public class ConsoleUiJournal {
    private JournalScientifiqueDaoImp journalSciDaoImp;
    private Scanner scanner;

    public ConsoleUiJournal(JournalScientifiqueDaoImp journalSciDaoImp) {
        this.journalSciDaoImp = journalSciDaoImp;
        this.scanner = new Scanner(System.in);
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
        System.out.println("Enter Livre title:");
        String title = scanner.nextLine();
        System.out.println("Enter Livre author:");
        String author = scanner.nextLine();
        System.out.println("Enter ID journal:");
        int id_journal = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Domaine Recherche:");
        String domaineRecherche = scanner.nextLine();
        System.out.println("Enter number of pages:");
        int pages = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.println("Enter publication date (YYYY-MM-DD):");
        LocalDate datePublication = LocalDate.parse(scanner.nextLine());
        JournalScientifique journalScientifique = new JournalScientifique(title, author, datePublication, pages,domaineRecherche,id_journal,journalSciDaoImp );
        journalScientifique.ajouterDocument();
    }

    private void modifierJournal(int id) {
        JournalScientifique journalScientifique = journalSciDaoImp.getJournalScientifiqueById(id);  // Retrieve the existing Livre

        if (journalScientifique != null) {
            System.out.println("Enter new Livre title (leave blank to keep current): ( current value :" + journalScientifique.getTitle()+") :");
            String newTitle = scanner.nextLine();
            if (!newTitle.isBlank()) journalScientifique.setTitle(newTitle);

            System.out.println("Enter new author (leave blank to keep current): ( current value :" + journalScientifique.getAuthor()+") :");
            String newAuthor = scanner.nextLine();
            if (!newAuthor.isBlank()) journalScientifique.setAuthor(newAuthor);

            // Publication Date
            System.out.println("Enter new publication date (YYYY-MM-DD) (leave blank to keep current) (current value: " + journalScientifique.getDate_publication() + "):");
            String newDateInput = scanner.nextLine();
            if (!newDateInput.isBlank()) {
                LocalDate newDatePublication = LocalDate.parse(newDateInput);
                journalScientifique.setDate_publication(newDatePublication);
            }

            // Number of Pages
            System.out.println("Enter new number of pages (leave 0 to keep current) (current value: " + journalScientifique.getNombre_of_pages() + "):");
            int newNombrePages = scanner.nextInt();
            scanner.nextLine();// Consume newline
            if (newNombrePages > 0) journalScientifique.setNombre_of_pages(newNombrePages);

            // Id journal
            System.out.println("Enter new Id Journal (leave 0 to keep current) (current value: " + journalScientifique.getIdJournal() + "):");
            int newIdJournal = scanner.nextInt();
            scanner.nextLine();
            if (newIdJournal > 0) journalScientifique.setIdJournal(newIdJournal);

            //Domaine Recherche

            System.out.println("Enter new author (leave blank to keep current): ( current value :" + journalScientifique.getDomaineRechercher()+") :");
            String newDomaineRecherche = scanner.nextLine();
            if (!newDomaineRecherche.isBlank()) journalScientifique.setAuthor(newDomaineRecherche);


            journalScientifique.modifierDocument(id);  // Calls the method implemented in Livre class
            System.out.println("Book updated successfully!");
        } else {
            System.out.println("Book not found.");
        }
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
        System.out.println("==========Journal==========");
        journalSciDaoImp.getJournalScientifiques().forEach(journalScientifique -> {
            System.out.println("ID : "+journalScientifique.getId());
            System.out.println("Title: " + journalScientifique.getTitle());
            System.out.println("Author: " + journalScientifique.getAuthor());
            System.out.println("Publication Date: " + journalScientifique.getDate_publication());
            System.out.println("Number of Pages: " + journalScientifique.getNombre_of_pages());
            System.out.println("Id Journal: " + journalScientifique.getIdJournal());
            System.out.println("Domaine Recherche: " + journalScientifique.getDomaineRechercher());
            System.out.println("Emprunter: " + (journalScientifique.isEstEmprunter() ? "Non disponible" : "Disponible"));
            System.out.println("Reserver: " + (journalScientifique.isEstReserver() ? "Non disponible" : "Disponible"));
            System.out.println("---------------");
        });
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


}
