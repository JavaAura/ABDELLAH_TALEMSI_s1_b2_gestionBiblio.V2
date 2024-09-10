package Metier;

import java.time.LocalDate;

public class JournalScientifique extends Document{
    private String domainerechercher ;
    private int id_journal;
    public JournalScientifique(String title, String author, LocalDate date_publication, int nombre_of_pages, String domaineRechercher, int idJournal) {
        super(title, author, date_publication, nombre_of_pages);
        domainerechercher = domaineRechercher;
        id_journal = idJournal;
    }
    public String getDomaineRechercher() {
        return domainerechercher;
    }
    public int getIdJournal() {
        return id_journal;
    }
    public void setIdJournal(int idJournal) {
        this.id_journal = idJournal;
    }
    public void setDomaineRechercher(String domaineRechercher) {
        this.domainerechercher = domaineRechercher;
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
