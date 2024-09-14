package Metier;

import Metier.interfaces.Empruntable;
import Metier.interfaces.Reservable;
import Persistance.TheseUniversitaireDaoImp;

import java.time.LocalDate;
import java.util.List;

public class TheseUniversitaire extends Document {

    private int id_theseUniversitaire ;
    private String universitaire ;
    private String domaine;
    private TheseUniversitaireDaoImp theseUniversitaireDaoImp;

    public TheseUniversitaire(String title, String author, LocalDate date_publication, int nombre_of_pages, int idTheseUniversitaire, String universitaire, String domaine,TheseUniversitaireDaoImp theseUniversitaireDaoImp) {
        super(title, author, date_publication, nombre_of_pages);
        id_theseUniversitaire = idTheseUniversitaire;
        this.universitaire = universitaire;
        this.domaine = domaine;
        this.theseUniversitaireDaoImp = theseUniversitaireDaoImp;
    }
    public int getId_theseUniversitaire() {
        return id_theseUniversitaire;
    }
    public void setId_theseUniversitaire(int id_theseUniversitaire) {
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

    public void addtheseuniversitaire() {
        if (theseUniversitaireDaoImp != null) {
            theseUniversitaireDaoImp.addTheseUniversitaire(this);
        } else {
            throw new IllegalStateException("TheseDaoImp is not set");
        }
    }

    public void updateTheseuniversitaire(int id) {
        if (theseUniversitaireDaoImp != null) {
            theseUniversitaireDaoImp.updateTheseUniversitaire(this ,id);
        } else {
            throw new IllegalStateException("TheseDaoImp is not set");
        }
    }

    public void deleteTheseuniversitaire(int id) {
        if (theseUniversitaireDaoImp != null) {
            theseUniversitaireDaoImp.deleteTheseUniversitaire(id);  // Ensure the ID is properly parsed
        } else {
            throw new IllegalStateException("theseDaoImp is not set");
        }
    }

    public static void displayTheseuniversitaireById(int id, TheseUniversitaireDaoImp dao) {
        TheseUniversitaire theseUniversitaire = dao.getTheseUniversitaireById(id);
        if (theseUniversitaire != null) {
            System.out.println("Title: " + theseUniversitaire.getTitle());
            System.out.println("Author: " + theseUniversitaire.getAuthor());
            System.out.println("Publication Date: " + theseUniversitaire.getDate_publication());
            System.out.println("Number of Pages: " + theseUniversitaire.getNombre_of_pages());
            System.out.println("ID These: " + theseUniversitaire.getId_theseUniversitaire());
            System.out.println("Domain Recherche : " + theseUniversitaire.getDomaine());
            System.out.println("University: " + theseUniversitaire.getUniversitaire());
            System.out.println("Emprunter: " + theseUniversitaire.isEstEmprunter());
            System.out.println("Reserver: " + theseUniversitaire.isEstReserver());
            System.out.println("---------------");
        } else {
            System.out.println("No book found with ID: " + id);
        }
    }

    public void afficherAllJTheseuniversitaires() {
        if (theseUniversitaireDaoImp != null) {
            List<TheseUniversitaire> theseUniversitaires = theseUniversitaireDaoImp.getTheseUniversitaires();
            for (TheseUniversitaire theseUniversitaire : theseUniversitaires) {
                System.out.println("ID: " + theseUniversitaire.getId());
                System.out.println("Title: " + theseUniversitaire.getTitle());
                System.out.println("Author: " + theseUniversitaire.getAuthor());
                System.out.println("Publication Date: " + theseUniversitaire.getDate_publication());
                System.out.println("Number of Pages: " + theseUniversitaire.getNombre_of_pages());
                System.out.println("ID These: " + theseUniversitaire.getId_theseUniversitaire());
                System.out.println("Domain Recherche : " + theseUniversitaire.getDomaine());
                System.out.println("University: " + theseUniversitaire.getUniversitaire());
                System.out.println("Emprunter: " + (theseUniversitaire.isEstEmprunter() ? "Non disponible" : "Disponible"));
                System.out.println("Reserver: " + (theseUniversitaire.isEstReserver() ? "Non disponible" : "Disponible"));
                System.out.println("---------------");
            }
        } else {
            throw new IllegalStateException("TheseDaoImp is not set");
        }
    }


    @Override
    public void afficher() {
         afficherAllJTheseuniversitaires();
    }

    @Override
    public void afficherLivre(int id) {
          if (theseUniversitaireDaoImp != null) {
              displayTheseuniversitaireById(id,theseUniversitaireDaoImp);
          }else {
              throw new IllegalStateException("TheseDaoImp is not set");
          }
    }


    @Override
    public void ajouterDocument() {
        addtheseuniversitaire();

    }

    @Override
    public void modifierDocument(int id) {
             updateTheseuniversitaire(id);
    }

    @Override
    public void supprimerDocument(int id) {
         deleteTheseuniversitaire(id);
    }

    public void afficherThese() {
        System.out.println("ID : " + getId());
        System.out.println("Title: " + getTitle());
        System.out.println("Author: " + getAuthor());
        System.out.println("Publication Date: " + getDate_publication());
        System.out.println("Number of Pages: " + getNombre_of_pages());
        System.out.println("Id These: " + getId_theseUniversitaire());
        System.out.println("Domaine : " + getDomaine());
        System.out.println("Universitaire: " + getUniversitaire());
        System.out.println("Emprunter: " + (isEstEmprunter() ? "Non disponible" : "Disponible"));
        System.out.println("Reserver: " + (isEstReserver() ? "Non disponible" : "Disponible"));
    }

}
