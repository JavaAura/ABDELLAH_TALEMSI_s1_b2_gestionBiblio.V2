package Presentation;

import Metier.utilisateurs.Etudiant;
import Persistance.EtudiantDaoImp;

import java.util.Scanner;

public class ConsoleUiEtudiant {
    private EtudiantDaoImp etudiantDaoImp;
    private Scanner scanner;

    public ConsoleUiEtudiant(EtudiantDaoImp etudiantDaoImp) {
        this.etudiantDaoImp = etudiantDaoImp;
        this.scanner = new Scanner(System.in);
    }

    public void gereEtudiants() {
        int choice;

        do {
            System.out.println("Student Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Display Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.println("Enter your option (1-6): ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    ajouterEtudiant();
                    break;
                case 2:
                    modifierEtudiant();
                    break;
                case 3:
                    afficherEtudiant();
                    break;
                case 4:
                    afficherTousLesEtudiants();
                    break;
                case 5:
                    supprimerEtudiant();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 6);
    }

    private void ajouterEtudiant() {
        System.out.println("Enter student name:");
        String nom = scanner.nextLine();
        System.out.println("Enter student last name:");
        String prenom = scanner.nextLine();

        String email;
        while (true) {
            System.out.println("Enter student email:");
            email = scanner.nextLine();

            // Check if email already exists
            if (etudiantDaoImp.isEmailExist(email)) {
                System.out.println("Email already exists! Please enter a new email.");
            } else {
                break;
            }
        }

        System.out.println("Enter student CIN:");
        String id_etudiant = scanner.nextLine();
        Etudiant etudiant = new Etudiant(nom, prenom, email, id_etudiant, etudiantDaoImp);
        etudiant.addEtudiant();  // Save the student

        System.out.println("Student added successfully.");
    }

    private void modifierEtudiant() {
        System.out.println("Enter student CIN to update:");
        String id = scanner.nextLine();
        Etudiant etudiant = etudiantDaoImp.getEtudiantById(id);

        if (etudiant != null) {
            // Update Name
            System.out.println("Enter new student name (leave blank to keep current): (current value: " + etudiant.getNom() + ")");
            String newNom = scanner.nextLine();
            if (!newNom.isBlank()) {
                etudiant.setNom(newNom);
            }

            // Update Surname
            System.out.println("Enter new student surname (leave blank to keep current): (current value: " + etudiant.getPrenom() + ")");
            String newPrenom = scanner.nextLine();
            if (!newPrenom.isBlank()) {
                etudiant.setPrenom(newPrenom);
            }

            // Update Email
            System.out.println("Enter new student email (leave blank to keep current): (current value: " + etudiant.getEmail() + ")");
            String newEmail = scanner.nextLine();
            if (!newEmail.isBlank()) {
                etudiant.setEmail(newEmail);
            }

            etudiantDaoImp.modifierEtudiant(etudiant);
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Student not found.");
        }
    }

    private void afficherEtudiant() {
        System.out.println("Enter student CIN to display:");
        String id = scanner.nextLine();
        Etudiant etudiant = etudiantDaoImp.getEtudiantById(id);
        if (etudiant != null) {
            System.out.println("Name: " + etudiant.getNom());
            System.out.println("Surname: " + etudiant.getPrenom());
            System.out.println("Email: " + etudiant.getEmail());
            System.out.println("Student CIN: " + etudiant.getId_etudiant());
        } else {
            System.out.println("Student not found.");
        }
    }

    private void afficherTousLesEtudiants() {
        System.out.println("========== Students ==========");
        etudiantDaoImp.getAllEtudiants().forEach(etudiant -> {
            System.out.println("Name: " + etudiant.getNom());
            System.out.println("Surname: " + etudiant.getPrenom());
            System.out.println("Email: " + etudiant.getEmail());
            System.out.println("Student CIN: " + etudiant.getId_etudiant());
            System.out.println("---------------");
        });
    }

    private void supprimerEtudiant() {
        System.out.println("Enter student ID to delete:");
        String id = scanner.nextLine();
        Etudiant etudiant = etudiantDaoImp.getEtudiantById(id);
        if (etudiant != null) {
            etudiantDaoImp.supprimerEtudiant(id);
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student not found.");
        }
    }

    public boolean checkEmailEtudiant(String email){
        etudiantDaoImp.isEmailExist(email);
       if (etudiantDaoImp.isEmailExist(email)) {
           return true;
       }else {
           return false;
       }
    }

    public  int getStudentId(String email) {
       int id_etudiant = etudiantDaoImp.getEtudiantIdByEmail(email);
        if (etudiantDaoImp.isEmailExist(email)) {
            return id_etudiant;
        }else{
            return -1;
        }
    }
}
