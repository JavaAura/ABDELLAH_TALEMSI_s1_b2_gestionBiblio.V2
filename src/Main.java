import Metier.Bibliotheque;
import Persistance.*;
import Presentation.*;
import com.company.DbFunctions;

import java.sql.Connection;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Initialize the database connection
        DbFunctions db = DbFunctions.getInstance();
        Connection conn = db.connect_to_db("gestion_biblio", "postgres", "Abdo@2023");

        if (conn != null) {
            LivreDaoImp livreDaoImp = new LivreDaoImp(conn);
            MagazineDaoImp magazineDaoImp = new MagazineDaoImp(conn);
            JournalScientifiqueDaoImp journalScientifiqueDaoImp = new JournalScientifiqueDaoImp(conn);
            TheseUniversitaireDaoImp theseUniversitaireDaoImp = new TheseUniversitaireDaoImp(conn);
            EtudiantDaoImp etudiantDaoImp = new EtudiantDaoImp(conn);
            ProfesseurDaoImp professeurDaoImp = new ProfesseurDaoImp(conn);

            ConsoleUiLivre consoleUiLivre = new ConsoleUiLivre(livreDaoImp);
            ConsoleUiMagazine consoleUiMagazine = new ConsoleUiMagazine(magazineDaoImp);
            ConsoleUiJournal consoleUiJournal = new ConsoleUiJournal(journalScientifiqueDaoImp);
            ConsoleUiThese consoleUiThese = new ConsoleUiThese(theseUniversitaireDaoImp);
            ConsoleUiEtudiant consoleUiEtudiant = new ConsoleUiEtudiant(etudiantDaoImp);
            ConsoleUiProfesseur consoleUiProfesseur = new ConsoleUiProfesseur(professeurDaoImp);
            Bibliotheque bibliotheque = new Bibliotheque(conn);

            ConsoleUI consoleUI = new ConsoleUI(
                    consoleUiLivre,
                    consoleUiMagazine,
                    consoleUiJournal,
                    consoleUiThese,
                    consoleUiEtudiant,
                    consoleUiProfesseur,
                    bibliotheque
            );
            consoleUI.run();

            db.closeConnection();

        } else {
            System.out.println("Erreur de connexion à la base de données.");
        }

    }


}