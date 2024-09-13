package Metier.utilisateurs;

public abstract class  Utilisateur {
  protected String nom;
  protected String prenom;
  protected String email;

  public Utilisateur(String nom, String prenom, String email) {
      this.nom = nom;
      this.prenom = prenom;
      this.email = email;
  }
  public String getNom() {
      return nom;
  }
  public void setNom(String nom) {
      this.nom = nom;
  }
  public String getPrenom() {
      return prenom;
  }
  public void setPrenom(String prenom) {
      this.prenom = prenom;
  }
  public String getEmail() {
      return email;
  }
  public void setEmail(String email) {
      this.email = email;
  }


  public abstract void afficher();
  public abstract void ajouterUtilisateur(Utilisateur utilisateur);
  public abstract void modifierUtilisateur(Utilisateur utilisateur);
  public abstract void getUtilisateur(String id);
  public abstract void supprimerUtilisateur(String id);
  public abstract void checkUtilisateur(Utilisateur utilisateur);

}
