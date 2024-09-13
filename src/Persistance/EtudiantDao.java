package Persistance;

import Metier.utilisateurs.Etudiant;

import java.util.List;

public interface EtudiantDao {
    void ajouterEtudiant(Etudiant etudiant);
    void modifierEtudiant(Etudiant etudiant);
    Etudiant getEtudiantById(String id_etudiant);
    void supprimerEtudiant(String id_etudiant);
    List<Etudiant> getAllEtudiants();
    boolean etudiantExistsByEmail(String email);
    boolean isEmailExist(String email);
}
