package Presentation;

import Metier.Livre;
import Persistance.LivreDaoImp;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ConsoleUiLivre {
    private LivreDaoImp livreDaoImp;
    private Scanner scanner;
    private HashMap<String, Livre> livreMap;

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
            System.out.println("6. Exit");
            System.out.println("Enter your option (1-6): ");
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
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 6);
    }
    private void ajouterLivre() {
        System.out.println("Enter Livre title:");
        String title = scanner.nextLine();
        System.out.println("Enter Livre author:");
        String author = scanner.nextLine();
        System.out.println("Enter ISBN:");
        String isbn = scanner.nextLine();
        System.out.println("Enter number of pages:");
        int pages = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.println("Enter publication date (YYYY-MM-DD):");
        LocalDate datePublication = LocalDate.parse(scanner.nextLine());
        Livre livre = new Livre(title, author, datePublication, pages, isbn, livreDaoImp);
        livre.ajouterDocument();
    }

    private void modifierLivre(int id) {
        Livre livre = livreDaoImp.getLivreById(id);  // Retrieve the existing Livre

        if (livre != null) {
            // Title
            System.out.println("Enter new Livre title (leave blank to keep current): ( current value :" + livre.getTitle() + ") :");
            String newTitle = scanner.nextLine(); // Keep the current behavior for title input
            if (!newTitle.isBlank()) {
                livre.setTitle(newTitle);
            }

            // Author
            System.out.println("Enter new author (leave blank to keep current): ( current value :" + livre.getAuthor() + ") :");
            String newAuthor = scanner.nextLine(); // No change here, this works correctly
            if (!newAuthor.isBlank()) {
                livre.setAuthor(newAuthor);
            }

            // Publication Date
            System.out.println("Enter new publication date (YYYY-MM-DD) (leave blank to keep current) (current value: " + livre.getDate_publication() + "):");
            String newDateInput = scanner.nextLine();
            if (!newDateInput.isBlank()) {
                LocalDate newDatePublication = LocalDate.parse(newDateInput);
                livre.setDate_publication(newDatePublication);
            }

            // Number of Pages
            System.out.println("Enter new number of pages (leave 0 to keep current) (current value: " + livre.getNombre_of_pages() + "):");
            int newNombrePages = scanner.nextInt();
            scanner.nextLine();  // Consume the leftover newline character
            if (newNombrePages > 0) {
                livre.setNombre_of_pages(newNombrePages);
            }

            // ISBN
            System.out.println("Enter new ISBN (leave blank to keep current) (current value: " + livre.getIsbn() + "):");
            String newIsbn = scanner.nextLine();
            if (!newIsbn.isBlank()) {
                livre.setIsbn(newIsbn);
            }

            // Update the Livre
            livreDaoImp.updateLivre(livre, id);  // Update Livre in the database
            System.out.println("Livre updated successfully.");
        } else {
            System.out.println("Livre not found.");
        }
    }


    public void afficherLivre() {
        System.out.println("Enter Livre ID to display:");
        int id = scanner.nextInt();
        Livre livre = livreDaoImp.getLivreById(id);
        if (livre != null) {
            livre.afficher();
        } else {
            System.out.println("Book not found.");
        }
    }

    public void afficherTousLesLivres() {
        System.out.println("==========Livres==========");
        livreDaoImp.getLivres().forEach(livre -> {
            System.out.println("ID: " + livre.getId());
            System.out.println("Title: " + livre.getTitle());
            System.out.println("Author: " + livre.getAuthor());
            System.out.println("Publication Date: " + livre.getDate_publication());
            System.out.println("Number of Pages: " + livre.getNombre_of_pages());
            System.out.println("ISBN: " + livre.getIsbn());
            System.out.println("Emprunter: " + (livre.isEstEmprunter() ? "Non disponible" : "Disponible"));
            System.out.println("Reserver: " + (livre.isEstReserver() ? "Non disponible" : "Disponible"));
            System.out.println("---------------");
        });
    }

    private void supprimerLivre() {
        System.out.println("Enter Livre ID to delete:");
        int id = scanner.nextInt();
        Livre livre = livreDaoImp.getLivreById(id);
        if (livre != null) {
            livreDaoImp.deleteLivre(id);
            System.out.println("Livre deleted successfully!");
        }else {
            System.out.println("Livre not found.");
        }
    }
    private void reserverLivre() {
        System.out.println("Enter Livre ID to reserve:");
        int id = scanner.nextInt();
        Livre livre = livreDaoImp.getLivreById(id);

        if (livre != null) {
              // Calls the method implemented in Livre class
            System.out.println("Book reserved successfully!");
        } else {
            System.out.println("Book not found.");
        }
    }
    private void emprunterLivre(){
        System.out.println("Enter Livre ID to emprun:");
        int id = scanner.nextInt();
        Livre livre = livreDaoImp.getLivreById(id);
        if (livre != null) {

            System.out.println("Book emprunted successfully!");
        }else {
            System.out.println("Book not found.");
        }
    }
    private void retournerLivre() {
        System.out.println("Enter Livre ID to return:");
        int id = scanner.nextInt();
        Livre livre = livreDaoImp.getLivreById(id);

        if (livre != null) {
             // Calls the method implemented in Livre class
            System.out.println("Book returned successfully!");
        } else {
            System.out.println("Book not found.");
        }
    }

    private void annulerReservation() {
        System.out.println("Enter Livre ID to cancel reservation:");
        int id = scanner.nextInt();
        Livre livre = livreDaoImp.getLivreById(id);

        if (livre != null) {
            // Calls the method implemented in Livre class
            System.out.println("Reservation cancelled successfully!");
        } else {
            System.out.println("Book not found.");
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


