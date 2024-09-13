package Presentation;

import Metier.Magazine;
import Persistance.MagazineDaoImp;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

public class ConsoleUiMagazine{
    private MagazineDaoImp magazineDaoImp;
    private Scanner scanner;
    private HashMap<String, Magazine> magazineMap;

    public ConsoleUiMagazine(MagazineDaoImp magazineDaoImp){
        this.magazineDaoImp = magazineDaoImp;
        scanner = new Scanner(System.in);
        this.magazineMap = new HashMap<>();
        populateMagazineMap();
    }

    public void gereMagazine(){
        int choice ;

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
        System.out.println("Enter Magazine title:");
        String title = scanner.nextLine();
        System.out.println("Enter Magazine author:");
        String author = scanner.nextLine();
        System.out.println("Enter Numero:");
        int numero = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter number of pages:");
        int pages = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.println("Enter publication date (YYYY-MM-DD):");
        LocalDate datePublication = LocalDate.parse(scanner.nextLine());
        Magazine magazine = new Magazine(title, author, datePublication, pages, numero, magazineDaoImp);
        magazine.ajouterDocument();
    }

    private void modifierMagazine(int id) {
        Magazine magazine = magazineDaoImp.getMagazineById(id);  // Retrieve the existing Livre

        if (magazine != null) {
            System.out.println("Enter new Livre title (leave blank to keep current): ( current value :" + magazine.getTitle()+") :");
            String newTitle = scanner.nextLine();
            scanner.next();
            if (!newTitle.isBlank()) magazine.setTitle(newTitle);

            System.out.println("Enter new author (leave blank to keep current): ( current value :" + magazine.getAuthor()+") :");
            String newAuthor = scanner.nextLine();
            scanner.next();
            if (!newAuthor.isBlank()) magazine.setAuthor(newAuthor);

            // Publication Date
            System.out.println("Enter new publication date (YYYY-MM-DD) (leave blank to keep current) (current value: " + magazine.getDate_publication() + "):");
            String newDateInput = scanner.nextLine();
            scanner.next();
            if (!newDateInput.isBlank()) {
                LocalDate newDatePublication = LocalDate.parse(newDateInput);
                magazine.setDate_publication(newDatePublication);
            }

            // Number of Pages
            System.out.println("Enter new number of pages (leave 0 to keep current) (current value: " + magazine.getNombre_of_pages() + "):");
            int newNombrePages = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            if (newNombrePages > 0) magazine.setNombre_of_pages(newNombrePages);

            // Numero
            System.out.println("Enter new ISBN (leave blank to keep current) (current value: " + magazine.getNumero() + "):");
            int newNumero = scanner.nextInt();
            scanner.nextLine();
            if (newNumero > 0) magazine.setNumero(newNumero);

            magazine.modifierDocument(id);  // Calls the method implemented in Livre class
            System.out.println("Book updated successfully!");
        } else {
            System.out.println("Book not found.");
        }
    }

    public void afficherMagazine() {
        System.out.println("Enter Magazine ID to display:");
        int id = scanner.nextInt();
        Magazine magazine = magazineDaoImp.getMagazineById(id);
        if (magazine != null) {
            magazine.afficher();
        } else {
            System.out.println("Book not found.");
        }
    }

    public void afficherTousLesMagazines() {
        System.out.println("==========Magazine==========");
        magazineDaoImp.getMagazines().forEach(magazine -> {
            System.out.println("Title: " + magazine.getTitle());
            System.out.println("Author: " + magazine.getAuthor());
            System.out.println("Publication Date: " + magazine.getDate_publication());
            System.out.println("Number of Pages: " + magazine.getNombre_of_pages());
            System.out.println("Numero: " + magazine.getNumero());
            System.out.println("Emprunter: " + (magazine.isEstEmprunter() ? "Non disponible" : "Disponible"));
            System.out.println("Reserver: " + (magazine.isEstReserver() ? "Non disponible" : "Disponible"));
            System.out.println("---------------");
        });
    }
    private void supprimerMagazine() {
        System.out.println("Enter Magazine ID to delete:");
        int id = scanner.nextInt();
        Magazine magazine = magazineDaoImp.getMagazineById(id);
        if (magazine != null) {
            magazine.deleteMagazine(id);
            System.out.println("Magazine deleted successfully!");
        }else {
            System.out.println("Magazine not found.");
        }
    }

    private void populateMagazineMap() {
        magazineDaoImp.getMagazines().forEach(magazine ->
                magazineMap.put(magazine.getTitle().toLowerCase(), magazine)
        );
    }

    public void searchMagazine() {
        System.out.println("Enter the title of the book to search:");
        String title = scanner.nextLine().toLowerCase();
        if (magazineMap.containsKey(title)) {
            Magazine foundMagazine = magazineMap.get(title);
            foundMagazine.afficher();
        } else {
            System.out.println("Book not found.");
        }
    }

}
