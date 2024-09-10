package Metier;

import java.time.LocalDate;

public class Magazine extends Document{
    private int numero;
    public Magazine(String id,String title, String author, LocalDate date_publication, int nombre_of_pages, int numero) {
        super(id,title, author, date_publication, nombre_of_pages);
        this.numero = numero;
    }
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
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
