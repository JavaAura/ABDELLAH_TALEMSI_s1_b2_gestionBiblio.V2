package Presentation;

import Metier.utilisateurs.Professeur;
import Persistance.ProfesseurDaoImp;

import java.util.Scanner;

public class ConsoleUiProfesseur {
    private ProfesseurDaoImp professeurDaoImp;
    private Scanner scanner;

    public ConsoleUiProfesseur(ProfesseurDaoImp professeurDaoImp) {
        this.professeurDaoImp = professeurDaoImp;
        this.scanner = new Scanner(System.in);
    }

    public void gererProfesseurs() {
        int choice;

        do {
            System.out.println("Professor Management System");
            System.out.println("1. Add Professor");
            System.out.println("2. Update Professor");
            System.out.println("3. Display Professor");
            System.out.println("4. Display All Professors");
            System.out.println("5. Delete Professor");
            System.out.println("6. Exit");
            System.out.println("Enter your option (1-6): ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    ajouterProfesseur();
                    break;
                case 2:
                    modifierProfesseur();
                    break;
                case 3:
                    afficherProfesseur();
                    break;
                case 4:
                    afficherTousLesProfesseurs();
                    break;
                case 5:
                    supprimerProfesseur();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 6);
    }

    private void ajouterProfesseur() {
        System.out.println("Enter professor name:");
        String nom = scanner.nextLine();
        System.out.println("Enter professor last name:");
        String prenom = scanner.nextLine();

        String email;
        while (true) {
            System.out.println("Enter professor email:");
            email = scanner.nextLine();

            if (professeurDaoImp.isEmailExist(email)) {
                System.out.println("Email already exists! Please enter a new email.");
            } else {
                break;
            }
        }

        System.out.println("Enter professor CIN:");
        String id_professeur = scanner.nextLine();
        Professeur professeur = new Professeur(nom, prenom, email, id_professeur, professeurDaoImp);
        professeur.addProfesseur();  // Save the professor

        System.out.println("Professor added successfully.");
    }

    private void modifierProfesseur() {
        System.out.println("Enter professor CIN to update:");
        String id = scanner.nextLine();
        Professeur professeur = professeurDaoImp.getProfesseurById(id);

        if (professeur != null) {
            // Update Name
            System.out.println("Enter new professor name (leave blank to keep current): (current value: " + professeur.getNom() + ")");
            String newNom = scanner.nextLine();
            if (!newNom.isBlank()) {
                professeur.setNom(newNom);
            }


            System.out.println("Enter new professor surname (leave blank to keep current): (current value: " + professeur.getPrenom() + ")");
            String newPrenom = scanner.nextLine();
            if (!newPrenom.isBlank()) {
                professeur.setPrenom(newPrenom);
            }

            System.out.println("Enter new professor email (leave blank to keep current): (current value: " + professeur.getEmail() + ")");
            String newEmail = scanner.nextLine();
            if (!newEmail.isBlank()) {
                professeur.setEmail(newEmail);
            }

            professeurDaoImp.modifierProfesseur(professeur);
            System.out.println("Professor updated successfully!");
        } else {
            System.out.println("Professor not found.");
        }
    }

    private void afficherProfesseur() {
        System.out.println("Enter professor ID to display:");
        String id = scanner.nextLine();
        Professeur professeur = professeurDaoImp.getProfesseurById(id);
        if (professeur != null) {
            System.out.println("Name: " + professeur.getNom());
            System.out.println("Surname: " + professeur.getPrenom());
            System.out.println("Email: " + professeur.getEmail());
            System.out.println("Professor ID: " + professeur.getId_professeur());
        } else {
            System.out.println("Professor not found.");
        }
    }

    private void afficherTousLesProfesseurs() {
        System.out.println("========== Professors ==========");
        professeurDaoImp.getAllProfesseurs().forEach(professeur -> {
            System.out.println("Name: " + professeur.getNom());
            System.out.println("Surname: " + professeur.getPrenom());
            System.out.println("Email: " + professeur.getEmail());
            System.out.println("Professor CIN: " + professeur.getId_professeur());
            System.out.println("---------------");
        });
    }

    private void supprimerProfesseur() {
        System.out.println("Enter professor ID to delete:");
        String id = scanner.nextLine();
        Professeur professeur = professeurDaoImp.getProfesseurById(id);
        if (professeur != null) {
            professeurDaoImp.supprimerProfesseur(id);
            System.out.println("Professor deleted successfully!");
        } else {
            System.out.println("Professor not found.");
        }
    }

    public boolean checkEmailProffesor(String email){
        professeurDaoImp.isEmailExist(email);
        if (professeurDaoImp.isEmailExist(email)) {
            return true;
        }else {
            return false;
        }
    }

    public  int getProfId(String email) {
        int id_etudiant = professeurDaoImp.getProffesorIdByEmail(email);
        if (professeurDaoImp.isEmailExist(email)) {
            return id_etudiant;
        }else{
            return -1;
        }
    }
}

