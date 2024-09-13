package Persistance;

import Metier.JournalScientifique;
import Metier.TheseUniversitaire;

import java.util.List;

public interface TheseUniversitaireDao {
    void addTheseUniversitaire(TheseUniversitaire theseUniversitaire);
    TheseUniversitaire getTheseUniversitaireById(int id);
    TheseUniversitaire getTheseUniversitaireByTitle(String titre);
    List<TheseUniversitaire> getTheseUniversitaires();
    void updateTheseUniversitaire(TheseUniversitaire theseUniversitaire , int id);
    void deleteTheseUniversitaire(int id);
}
