package Presentation;

import Metier.TheseUniversitaire;
import Persistance.TheseUniversitaireDaoImp;

import java.time.LocalDate;
import java.util.Scanner;

public class ConsoleUiThese {
    private TheseUniversitaireDaoImp theseUniversitaireDaoImp;
    private Scanner scanner;

    public ConsoleUiThese(TheseUniversitaireDaoImp theseUniversitaireDaoImp) {
        this.theseUniversitaireDaoImp = theseUniversitaireDaoImp;
        scanner = new Scanner(System.in);
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
        System.out.println("Enter These title:");
        String title = scanner.nextLine();
        System.out.println("Enter These author:");
        String author = scanner.nextLine();
        System.out.println("Enter ID These:");
        int id_these = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Domaine:");
        String domaine = scanner.nextLine();
        System.out.println("Enter Universite:");
        String universite = scanner.nextLine();
        System.out.println("Enter number of pages:");
        int pages = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter publication date (YYYY-MM-DD):");
        LocalDate datePublication = LocalDate.parse(scanner.nextLine());
        TheseUniversitaire these = new TheseUniversitaire(title, author, datePublication, pages, id_these, domaine, universite, theseUniversitaireDaoImp);
        these.ajouterDocument();
    }

    private void modifierThese(int id) {
        TheseUniversitaire these = theseUniversitaireDaoImp.getTheseUniversitaireById(id);

        if (these != null) {
            System.out.println("Enter new These title (leave blank to keep current): ( current value :" + these.getTitle() + ") :");
            String newTitle = scanner.nextLine();
            if (!newTitle.isBlank()) these.setTitle(newTitle);

            System.out.println("Enter new author (leave blank to keep current): ( current value :" + these.getAuthor() + ") :");
            String newAuthor = scanner.nextLine();
            if (!newAuthor.isBlank()) these.setAuthor(newAuthor);

            System.out.println("Enter new publication date (YYYY-MM-DD) (leave blank to keep current) (current value: " + these.getDate_publication() + "):");
            String newDateInput = scanner.nextLine();
            if (!newDateInput.isBlank()) {
                LocalDate newDatePublication = LocalDate.parse(newDateInput);
                these.setDate_publication(newDatePublication);
            }

            System.out.println("Enter new number of pages (leave 0 to keep current) (current value: " + these.getNombre_of_pages() + "):");
            int newNombrePages = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            if (newNombrePages > 0) these.setNombre_of_pages(newNombrePages);

            System.out.println("Enter new Domaine (leave blank to keep current) (current value: " + these.getDomaine() + "):");
            String newDomaine = scanner.nextLine();
            if (!newDomaine.isBlank()) these.setDomaine(newDomaine);

            System.out.println("Enter new Universite (leave blank to keep current) (current value: " + these.getUniversitaire() + "):");
            String newUniversite = scanner.nextLine();
            if (!newUniversite.isBlank()) these.setUniversitaire(newUniversite);

            System.out.println("Enter new ID These (leave 0 to keep current) (current value: " + these.getId_theseUniversitaire() + "):");
            int newIdThese = scanner.nextInt();
            scanner.nextLine();
            if (newIdThese > 0) these.setId_theseUniversitaire(newIdThese);

            these.modifierDocument(id);
            System.out.println("These updated successfully!");
        } else {
            System.out.println("These not found.");
        }
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
        System.out.println("==========Theses==========");
        theseUniversitaireDaoImp.getTheseUniversitaires().forEach(these -> {
            System.out.println("ID Document : " + these.getId());
            System.out.println("Title: " + these.getTitle());
            System.out.println("Author: " + these.getAuthor());
            System.out.println("Publication Date: " + these.getDate_publication());
            System.out.println("Number of Pages: " + these.getNombre_of_pages());
            System.out.println("ID These : " + these.getId_theseUniversitaire());
            System.out.println("Domaine: " + these.getDomaine());
            System.out.println("Universite: " + these.getUniversitaire());
            System.out.println("Emprunter: " + (these.isEstEmprunter() ? "Non disponible" : "Disponible"));
            System.out.println("Reserver: " + (these.isEstReserver() ? "Non disponible" : "Disponible"));
            System.out.println("---------------");
        });
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

}
