package Metier;

import Metier.interfaces.Empruntable;
import Metier.interfaces.Reservable;
import Persistance.LivreDaoImp;
import java.time.LocalDate;
import java.util.List;

public class Livre extends Document {
    private String isbn;
    private LivreDaoImp livreDaoImp;

    public Livre( String title, String author, LocalDate date_publication, int nombre_of_pages, String isbn, LivreDaoImp livreDaoImp) {
        super(title, author, date_publication, nombre_of_pages);
        this.isbn = isbn;
        this.livreDaoImp = livreDaoImp;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void addLivre() {
        if (livreDaoImp != null) {
            livreDaoImp.addLivre(this);
        } else {
            throw new IllegalStateException("LivreDaoImp is not set");
        }
    }

    public void updateLivre(int id) {
        if (livreDaoImp != null) {
            livreDaoImp.updateLivre(this ,id);
        } else {
            throw new IllegalStateException("LivreDaoImp is not set");
        }
    }

    public void deleteLivre(int id) {
        if (livreDaoImp != null) {
            livreDaoImp.deleteLivre(id);  // Ensure the ID is properly parsed
        } else {
            throw new IllegalStateException("LivreDaoImp is not set");
        }
    }

    public static void displayLivreById(int id, LivreDaoImp dao) {
        Livre livre = dao.getLivreById(id);
        if (livre != null) {
            System.out.println("Title: " + livre.getTitle());
            System.out.println("Author: " + livre.getAuthor());
            System.out.println("Publication Date: " + livre.getDate_publication());
            System.out.println("Number of Pages: " + livre.getNombre_of_pages());
            System.out.println("ISBN: " + livre.getIsbn());
            System.out.println("Emprunter: " + livre.isEstEmprunter());
            System.out.println("Reserver: " + livre.isEstReserver());
            System.out.println("---------------");
        } else {
            System.out.println("No book found with ID: " + id);
        }
    }

    public void afficherAllLivres() {
        if (livreDaoImp != null) {
            List<Livre> livres = livreDaoImp.getLivres();
            for (Livre livre : livres) {
                System.out.println("ID :" + livre.getId());
                System.out.println("Title: " + livre.getTitle());
                System.out.println("Author: " + livre.getAuthor());
                System.out.println("Publication Date: " + livre.getDate_publication());
                System.out.println("Number of Pages: " + livre.getNombre_of_pages());
                System.out.println("ISBN: " + livre.getIsbn());
                System.out.println("Emprunter: " + (livre.isEstEmprunter() ? "Non disponible" : "Disponible"));
                System.out.println("Reserver: " + (livre.isEstReserver() ? "Non disponible" : "Disponible"));
                System.out.println("---------------");
            }
        } else {
            throw new IllegalStateException("LivreDaoImp is not set");
        }
    }

    @Override
    public void afficher() {
        afficherAllLivres();
    }

    @Override
    public void afficherLivre(int id) {
        if (livreDaoImp != null) {
            displayLivreById(id, livreDaoImp);
        } else {
            throw new IllegalStateException("LivreDaoImp is not set");
        }
    }

    @Override
    public void ajouterDocument() {
        addLivre();
    }

    @Override
    public void modifierDocument(int id) {
        updateLivre(id);
    }

    @Override
    public void supprimerDocument(int id) {
        deleteLivre(id);
    }


    public void afficherLivre() {
        System.out.println("ID : " + getId());
        System.out.println("Title: " + getTitle());
        System.out.println("Author: " + getAuthor());
        System.out.println("Publication Date: " + getDate_publication());
        System.out.println("Number of Pages: " + getNombre_of_pages());
        System.out.println("Id ISBN: " + getIsbn());
        System.out.println("Emprunter: " + (isEstEmprunter() ? "Non disponible" : "Disponible"));
        System.out.println("Reserver: " + (isEstReserver() ? "Non disponible" : "Disponible"));
    }


}
