package Metier;

import java.time.LocalDate;

public class Livre extends Document {
     private String isbn;

    public Livre(String id ,String title, String author, LocalDate date_publication, int nombre_of_pages , String isbn) {
        super(id,title, author, date_publication, nombre_of_pages);
        this.isbn = isbn;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
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
