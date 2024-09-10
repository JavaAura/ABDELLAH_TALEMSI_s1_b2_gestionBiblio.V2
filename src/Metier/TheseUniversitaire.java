package Metier;

import java.time.LocalDate;

public class TheseUniversitaire extends Document{

    private String id_theseUniversitaire ;
    private String universitaire ;
    private String domaine;

    public TheseUniversitaire(String title, String author, LocalDate date_publication, int nombre_of_pages, String idTheseUniversitaire, String universitaire, String domaine) {
        super(title, author, date_publication, nombre_of_pages);
        id_theseUniversitaire = idTheseUniversitaire;
        this.universitaire = universitaire;
        this.domaine = domaine;
    }
    public String getId_theseUniversitaire() {
        return id_theseUniversitaire;
    }
    public void setId_theseUniversitaire(String id_theseUniversitaire) {
        this.id_theseUniversitaire = id_theseUniversitaire;
    }
    public String getUniversitaire() {
        return universitaire;
    }
    public void setUniversitaire(String universitaire) {
        this.universitaire = universitaire;
    }
    public String getDomaine() {
        return domaine;
    }
    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }


    @Override
    public void afficher() {

    }

    @Override
    public void ajouterDocument() {

    }

    @Override
    public void modifierDocument() {

    }

    @Override
    public void reserverDocument() {

    }
}
