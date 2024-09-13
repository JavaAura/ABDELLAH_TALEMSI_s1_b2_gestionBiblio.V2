package Metier.utilisateurs;

import Persistance.ProfesseurDaoImp;

import java.util.List;

public class Professeur extends Utilisateur {

    private String id_professeur;
    private ProfesseurDaoImp professeurDaoImp;

    public Professeur(String nom, String prenom, String email,String idProfessor,ProfesseurDaoImp professeurDaoImp) {
        super(nom, prenom, email);
        id_professeur = idProfessor;
        this.professeurDaoImp = professeurDaoImp;
    }
    public String getId_professeur() {
        return id_professeur;
    }
    public void setId_professeur(String id_professeur) {
        this.id_professeur = id_professeur;
    }


    public void addProfesseur() {
        if (professeurDaoImp != null) {
            professeurDaoImp.ajouterProfesseur(this);
        } else {
            throw new IllegalStateException("ProfesseurDaoImp is not set");
        }
    }

    public void updateProfesseur(String id) {
        if (professeurDaoImp != null) {
            professeurDaoImp.modifierProfesseur(this);
        } else {
            throw new IllegalStateException("ProfesseurDaoImp is not set");
        }
    }

    public void deleteProfesseur(String id) {
        if (professeurDaoImp != null) {
            professeurDaoImp.supprimerProfesseur(id);
        } else {
            throw new IllegalStateException("ProfesseurDaoImp is not set");
        }
    }

    public static void displayProfesseurById(String id, ProfesseurDaoImp dao) {
        Professeur professeur = dao.getProfesseurById(id);
        if (professeur != null) {
            System.out.println("Nom: " + professeur.getNom());
            System.out.println("Prenom: " + professeur.getPrenom());
            System.out.println("Email: " + professeur.getEmail());
            System.out.println("ID Professeur: " + professeur.getId_professeur());
            System.out.println("---------------");
        } else {
            System.out.println("No professor found with ID: " + id);
        }
    }
    public void afficherAllProfesseurs() {
        if (professeurDaoImp != null) {
            List<Professeur> professeurs = professeurDaoImp.getAllProfesseurs();
            for (Professeur professeur : professeurs) {
                System.out.println("Nom: " + professeur.getNom());
                System.out.println("Prenom: " + professeur.getPrenom());
                System.out.println("Email: " + professeur.getEmail());
                System.out.println("ID Professeur: " + professeur.getId_professeur());
                System.out.println("---------------");
            }
        } else {
            throw new IllegalStateException("ProfesseurDaoImp is not set");
        }
    }
    @Override
    public void afficher() {
         afficherAllProfesseurs();
    }

    @Override
    public void ajouterUtilisateur(Utilisateur utilisateur) {
         addProfesseur();
    }

    @Override
    public void modifierUtilisateur(Utilisateur utilisateur) {
        updateProfesseur(((Professeur) utilisateur).getId_professeur());
    }

    @Override
    public void getUtilisateur(String id) {
        if (professeurDaoImp != null) {
            displayProfesseurById(id, professeurDaoImp);
        } else {
            throw new IllegalStateException("ProfesseurDaoImp is not set");
        }
    }


    @Override
    public void supprimerUtilisateur(String id) {
        deleteProfesseur(id);
    }

    @Override
    public void checkUtilisateur(Utilisateur utilisateur) {
        if (utilisateur instanceof Professeur) {
            Professeur professeur = (Professeur) utilisateur;
            if (professeurDaoImp.professeurExistsByEmail(professeur.getEmail())) {
                System.out.println("Professor with email " + professeur.getEmail() + " already exists.");
            } else {
                System.out.println("No professor found with email " + professeur.getEmail() + ".");
            }
        } else {
            System.out.println("The user is not a Professeur.");
        }
    }
}
