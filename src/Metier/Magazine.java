package Metier;

import Metier.interfaces.Empruntable;
import Metier.interfaces.Reservable;
import Persistance.MagazineDaoImp;

import java.time.LocalDate;
import java.util.List;

public class Magazine extends Document {
    private int numero;
    private MagazineDaoImp magazineDaoImp;
    public Magazine(String title, String author, LocalDate date_publication, int nombre_of_pages, int numero, MagazineDaoImp magazineDaoImp) {
        super(title, author, date_publication, nombre_of_pages);
        this.numero = numero;
        this.magazineDaoImp = magazineDaoImp;
    }
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void addMagazine() {
        if (magazineDaoImp != null) {
            magazineDaoImp.addMagazine(this);
        } else {
            throw new IllegalStateException("MagazineDaoImp is not set");
        }
    }

    public void updateMagazine(int id) {
        if (magazineDaoImp != null) {
            magazineDaoImp.updateMagazine(this ,id);
        } else {
            throw new IllegalStateException("magazineDaoImp is not set");
        }
    }

    public void deleteMagazine(int id) {
        if (magazineDaoImp != null) {
            magazineDaoImp.deleteMagazine(id);  // Ensure the ID is properly parsed
        } else {
            throw new IllegalStateException("MagazineDaoImp is not set");
        }
    }

    public static void displayMagazineById(int id, MagazineDaoImp dao) {
        Magazine magazine = dao.getMagazineById(id);
        if (magazine != null) {
            System.out.println("Title: " + magazine.getTitle());
            System.out.println("Author: " + magazine.getAuthor());
            System.out.println("Publication Date: " + magazine.getDate_publication());
            System.out.println("Number of Pages: " + magazine.getNombre_of_pages());
            System.out.println("Numero: " + magazine.getNumero());
            System.out.println("Emprunter: " + magazine.isEstEmprunter());
            System.out.println("Reserver: " + magazine.isEstReserver());
            System.out.println("---------------");
        } else {
            System.out.println("No book found with ID: " + id);
        }
    }

    public void afficherAllMagazines() {
        if (magazineDaoImp != null) {
            List<Magazine> magazines = magazineDaoImp.getMagazines();
            for (Magazine magazine : magazines) {
                System.out.println("Title: " + magazine.getTitle());
                System.out.println("Author: " + magazine.getAuthor());
                System.out.println("Publication Date: " + magazine.getDate_publication());
                System.out.println("Number of Pages: " + magazine.getNombre_of_pages());
                System.out.println("Numero: " + magazine.getNumero());
                System.out.println("Emprunter: " + (magazine.isEstEmprunter() ? "Non disponible" : "Disponible"));
                System.out.println("Reserver: " + (magazine.isEstReserver() ? "Non disponible" : "Disponible"));
                System.out.println("---------------");
            }
        } else {
            throw new IllegalStateException("MagazineDaoImp is not set");
        }
    }

    @Override
    public void afficher() {
        afficherAllMagazines();

    }

    @Override
    public void afficherLivre(int id) {
        if (magazineDaoImp != null) {
            displayMagazineById(id, magazineDaoImp);
        }else {
            throw new IllegalStateException("MagazineDaoImp is not set");
        }
    }


    @Override
    public void ajouterDocument() {
        addMagazine();

    }

    @Override
    public void modifierDocument(int id) {
        updateMagazine(id);

    }

    @Override
    public void supprimerDocument(int id) {
        deleteMagazine(id);

    }
    public void afficherMagazine() {
        System.out.println("ID : " + getId());
        System.out.println("Title: " + getTitle());
        System.out.println("Author: " + getAuthor());
        System.out.println("Publication Date: " + getDate_publication());
        System.out.println("Number of Pages: " + getNombre_of_pages());
        System.out.println("Numero: " + getNumero());
        System.out.println("Emprunter: " + (isEstEmprunter() ? "Non disponible" : "Disponible"));
        System.out.println("Reserver: " + (isEstReserver() ? "Non disponible" : "Disponible"));
    }

}
