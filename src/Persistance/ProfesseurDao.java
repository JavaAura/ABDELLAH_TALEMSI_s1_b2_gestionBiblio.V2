package Persistance;

import Metier.utilisateurs.Professeur;

import java.util.List;

public interface ProfesseurDao {
    void ajouterProfesseur(Professeur professeur);
    void modifierProfesseur(Professeur professeur);
    Professeur getProfesseurById(String id_professeur);
    void supprimerProfesseur(String id_professeur);
    List<Professeur> getAllProfesseurs();
    boolean professeurExistsByEmail(String email);
    boolean isEmailExist(String email);
}
