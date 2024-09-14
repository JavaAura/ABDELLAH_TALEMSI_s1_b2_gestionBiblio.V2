package Metier;

import Metier.interfaces.Empruntable;
import Metier.interfaces.Reservable;
import Persistance.JournalScientifiqueDaoImp;

import java.time.LocalDate;
import java.util.List;

public class JournalScientifique extends Document {
    private String domainerechercher ;
    private int id_journal;
    private JournalScientifiqueDaoImp journalDaoImp;
    public JournalScientifique(String title, String author, LocalDate date_publication, int nombre_of_pages, String domaineRechercher, int idJournal, JournalScientifiqueDaoImp journalDaoImp) {
        super(title, author, date_publication, nombre_of_pages);
        domainerechercher = domaineRechercher;
        id_journal = idJournal;
        this.journalDaoImp = journalDaoImp;
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

    public void addJournalScientifique() {
        if (journalDaoImp != null) {
            journalDaoImp.addJournalScientifique(this);
        } else {
            throw new IllegalStateException("JournalDaoImp is not set");
        }
    }

    public void updateJournalScientifique(int id) {
        if (journalDaoImp != null) {
            journalDaoImp.updateJournalScientifique(this ,id);
        } else {
            throw new IllegalStateException("JournalDaoImp is not set");
        }
    }

    public void deleteJournalScientifique(int id) {
        if (journalDaoImp != null) {
            journalDaoImp.deleteJournalScientifique(id);  // Ensure the ID is properly parsed
        } else {
            throw new IllegalStateException("JournalDaoImp is not set");
        }
    }

    public static void displayJournalScientifiqueById(int id, JournalScientifiqueDaoImp dao) {
        JournalScientifique journalScientifique = dao.getJournalScientifiqueById(id);
        if (journalScientifique != null) {
            System.out.println("Title: " + journalScientifique.getTitle());
            System.out.println("Author: " + journalScientifique.getAuthor());
            System.out.println("Publication Date: " + journalScientifique.getDate_publication());
            System.out.println("Number of Pages: " + journalScientifique.getNombre_of_pages());
            System.out.println("Domain Recherche : " + journalScientifique.getDomaineRechercher());
            System.out.println("Id de Journal: " + journalScientifique.getIdJournal());
            System.out.println("Emprunter: " + journalScientifique.isEstEmprunter());
            System.out.println("Reserver: " + journalScientifique.isEstReserver());
            System.out.println("---------------");
        } else {
            System.out.println("No book found with ID: " + id);
        }
    }

    public void afficherAllJournalScientifiques() {
        if (journalDaoImp != null) {
            List<JournalScientifique> journalScientifiques = journalDaoImp.getJournalScientifiques();
            for (JournalScientifique journalScientifique : journalScientifiques) {
                System.out.println("ID: " + journalScientifique.getId());
                System.out.println("Title: " + journalScientifique.getTitle());
                System.out.println("Author: " + journalScientifique.getAuthor());
                System.out.println("Publication Date: " + journalScientifique.getDate_publication());
                System.out.println("Number of Pages: " + journalScientifique.getNombre_of_pages());
                System.out.println("Domain Recherche : " + journalScientifique.getDomaineRechercher());
                System.out.println("Id de Journal: " + journalScientifique.getIdJournal());
                System.out.println("Emprunter: " + (journalScientifique.isEstEmprunter() ? "Non disponible" : "Disponible"));
                System.out.println("Reserver: " + (journalScientifique.isEstReserver() ? "Non disponible" : "Disponible"));
                System.out.println("---------------");
            }
        } else {
            throw new IllegalStateException("JournalDaoImp is not set");
        }
    }

    @Override
    public void afficher() {
        afficherAllJournalScientifiques();

    }

    @Override
    public void afficherLivre(int id) {
        if (journalDaoImp != null) {
            displayJournalScientifiqueById(id, journalDaoImp);
        } else {
            throw new IllegalStateException("JournalDaoImp is not set");
        }

    }



    @Override
    public void ajouterDocument() {
        addJournalScientifique();

    }

    @Override
    public void modifierDocument(int id) {
        updateJournalScientifique(id);

    }

    @Override
    public void supprimerDocument(int id) {
        deleteJournalScientifique(id);

    }
    public void afficherJounale() {
        System.out.println("ID : " + getIdJournal());
        System.out.println("Title: " + getTitle());
        System.out.println("Author: " + getAuthor());
        System.out.println("Publication Date: " + getDate_publication());
        System.out.println("Number of Pages: " + getNombre_of_pages());
        System.out.println("Id Journal: " + getIdJournal());
        System.out.println("Domaine Recherche: " + getDomaineRechercher());
        System.out.println("Emprunter: " + (isEstEmprunter() ? "Non disponible" : "Disponible"));
        System.out.println("Reserver: " + (isEstReserver() ? "Non disponible" : "Disponible"));
    }

}
