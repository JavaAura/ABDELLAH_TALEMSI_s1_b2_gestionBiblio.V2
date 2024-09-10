package Metier;

import java.time.LocalDate;

public abstract class Document {
    protected String id;
    protected String title;
    protected String author;
    protected LocalDate date_publication;
    protected int nombre_of_pages;
    protected boolean estEmprunter = false;
    protected boolean estReserver = false;

    public Document(String id,String title, String author, LocalDate date_publication, int nombre_of_pages) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.date_publication = date_publication;
        this.nombre_of_pages = nombre_of_pages;

    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public LocalDate getDate_publication() {
        return date_publication;
    }
    public void setDate_publication(LocalDate date_publication) {
        this.date_publication = date_publication;
    }
    public int getNombre_of_pages() {
        return nombre_of_pages;
    }
    public void setNombre_of_pages(int nombre_of_pages) {
        this.nombre_of_pages = nombre_of_pages;
    }
    public boolean isEstEmprunter() {
        return estEmprunter;
    }
    public void setEstEmprunter(boolean estEmprunter) {
        this.estEmprunter = estEmprunter;
    }
    public boolean isEstReserver() {
        return estReserver;
    }
    public void setEstReserver(boolean estReserver) {
        this.estReserver = estReserver;
    }

    public abstract void afficher();
    public abstract void ajouterDocument();
    public abstract void modifierDocument();
    public abstract void reserverDocument();
}
