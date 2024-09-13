package Presentation;

import Metier.Bibliotheque;

import java.util.Scanner;

public class ConsoleUI {
    private static final String adminEmail = "admin@example.com";
    private ConsoleUiLivre consoleUiLivre;
    private ConsoleUiMagazine consoleUiMagazine;
    private ConsoleUiJournal consoleUiJournal;
    private ConsoleUiThese consoleUiThese;
    private ConsoleUiEtudiant consoleUiEtudiant;
    private ConsoleUiProfesseur consoleUiProfesseur;
    private Scanner scanner;
    private Bibliotheque bibliotheque;

    public ConsoleUI(ConsoleUiLivre consoleUiLivre, ConsoleUiMagazine consoleUiMagazine,
                     ConsoleUiJournal consoleUiJournal, ConsoleUiThese consoleUiThese,
                     ConsoleUiEtudiant consoleUiEtudiant, ConsoleUiProfesseur consoleUiProfesseur,Bibliotheque bibliotheque) {
        this.consoleUiLivre = consoleUiLivre;
        this.consoleUiMagazine = consoleUiMagazine;
        this.consoleUiJournal = consoleUiJournal;
        this.consoleUiThese = consoleUiThese;
        this.consoleUiEtudiant = consoleUiEtudiant;
        this.consoleUiProfesseur = consoleUiProfesseur;
        this.bibliotheque = bibliotheque;

        this.scanner = new Scanner(System.in);
    }

    public void run() {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("Welcome! Please select your role:");
            System.out.println("1. Admin");
            System.out.println("2. Student");
            System.out.println("3. Professor");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int role = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            if (role == 4) {
                System.out.println("Goodbye!");
                isRunning = false;
                break;
            }

            boolean isEmailValid = false;
            String email = "";

            if (role == 1) {
                System.out.print("Enter Admin email: ");
                email = scanner.nextLine();

                if (email.equals(adminEmail)) {
                    isEmailValid = true;
                } else {
                    System.out.println("Invalid Admin email. Access denied.");
                    continue;
                }
            } else {
                int attempts = 0;

                while (attempts < 3 && !isEmailValid) {
                    System.out.print("Enter your email: ");
                    email = scanner.nextLine();

                    if (role == 2) {  // Student
                        isEmailValid = consoleUiEtudiant.checkEmailEtudiant(email);
                    } else if (role == 3) {  // Professor
                        isEmailValid = consoleUiProfesseur.checkEmailProffesor(email);
                    }

                    if (!isEmailValid) {
                        System.out.println("Email not found. Attempts left: " + (2 - attempts));
                        attempts++;
                    }
                }

                if (!isEmailValid) {
                    System.out.println("Too many incorrect attempts. Goodbye!");
                    continue;
                }
            }

            // Proceed with role-specific menu after a valid email
            switch (role) {
                case 1:
                    adminMenu(email);
                    break;
                case 2:
                    studentMenu(email);
                    break;
                case 3:
                    professorMenu(email);
                    break;
                default:
                    System.out.println("Invalid choice. Exiting...");
            }
        }
    }

    private void adminMenu(String email) {
        System.out.println("\nAuthenticated as Admin: " + email);
        int choice;
        do {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Manage Books");
            System.out.println("2. Manage Magazines");
            System.out.println("3. Manage Scientific Journals");
            System.out.println("4. Manage Theses");
            System.out.println("5. Manage Students");
            System.out.println("6. Manage Professors");
            System.out.println("7. Deconnect and return to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    consoleUiLivre.gereBooks();
                    break;
                case 2:
                    consoleUiMagazine.gereMagazine();
                    break;
                case 3:
                    consoleUiJournal.gereJournal();
                    break;
                case 4:
                    consoleUiThese.gereThere();
                    break;
                case 5:
                    consoleUiEtudiant.gereEtudiants();
                    break;
                case 6:
                    consoleUiProfesseur.gererProfesseurs();
                    break;
                case 7:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 7);
    }

    private void studentMenu(String email) {
        System.out.println("\nAuthenticated as Student: " + email);
        int etudiantId = consoleUiEtudiant.getStudentId(email);
        int choice;
        do {
            System.out.println("\nStudent Menu:");
            System.out.println("1. View All Books");
            System.out.println("2. View All Magazines");
            System.out.println("3. Borrow Book");
            System.out.println("4. Reserve Book");
            System.out.println("5. Display one book");
            System.out.println("6. Display one magazine");
            System.out.println("7. Deconnect and return to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            switch (choice) {
                case 1:
                    consoleUiLivre.afficherTousLesLivres();
                    break;
                case 2:
                    consoleUiMagazine.afficherTousLesMagazines();
                    break;
                case 3:
                    System.out.print("Enter Book ID to borrow: ");
                    int borrowBookId = scanner.nextInt();
                    scanner.nextLine();
                    boolean success = bibliotheque.emprunt(borrowBookId, etudiantId);
                    if (success) {
                        System.out.println("Book borrowed successfully.");
                    } else {
                        System.out.println("Failed to borrow the book. It might already be borrowed.");
                    }
                    break;
                case 4:
                    System.out.print("Enter Book ID to reserve: ");
                    int reserveBookId = scanner.nextInt();
                    scanner.nextLine();
                    boolean isRetourner = bibliotheque.retourner(reserveBookId);
                    if (isRetourner) {
                        System.out.println("Book reserved successfully.");
                    }else{
                        System.out.println("Failed to reserve the book. It might already be reserved.");
                    }
                    break;
                case 5:
                    consoleUiLivre.afficherLivre();
                    break;
                case 6:
                    consoleUiMagazine.afficherMagazine();
                    break;
                case 7:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 7);
    }

    private void professorMenu(String email) {
        System.out.println("\nAuthenticated as Professor: " + email);
        int choice;
        do {
            System.out.println("\nProfessor Menu:");
            System.out.println("1. View All Theses");
            System.out.println("2. View All Scientific Journals");
            System.out.println("3. Borrow Book");
            System.out.println("4. Reserve Book");
            System.out.println("5. Deconnect and return to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            switch (choice) {
                case 1:
                    consoleUiThese.afficherToutesLesTheses();
                    break;
                case 2:
                    consoleUiJournal.afficherTousLesJournals();
                    break;
                case 3:
                    System.out.print("Enter Book ID to borrow: ");
                    String borrowBookId = scanner.nextLine();
                    // Implement borrowing logic
                    break;
                case 4:
                    System.out.print("Enter Book ID to reserve: ");
                    String reserveBookId = scanner.nextLine();
                    // Implement reservation logic
                    break;
                case 5:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 5);
    }
}
