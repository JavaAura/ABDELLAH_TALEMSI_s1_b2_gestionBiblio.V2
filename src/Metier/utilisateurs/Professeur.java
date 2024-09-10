package Metier.utilisateurs;

public class Professeur extends Utilisateur {

    private int id_professeur;

    public Professeur(String nom, String prenom, String email, String password, int idProfessor) {
        super(nom, prenom, email, password);
        id_professeur = idProfessor;
    }
    public int getIdEtudiant() {
        return id_professeur;
    }
    public void setIdEtudiant(int id_etudiant) {
        this.id_professeur = id_etudiant;
    }

    @Override
    public void afficher() {

    }

    @Override
    public void ajouterUtilisateur(Utilisateur utilisateur) {

    }

    @Override
    public void modifierUtilisateur(Utilisateur utilisateur) {

    }

    @Override
    public void supprimerUtilisateur() {

    }
}
