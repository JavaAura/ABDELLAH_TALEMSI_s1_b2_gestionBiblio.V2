package Persistance;

import Metier.JournalScientifique;

import java.util.List;

public interface JournalScientifiqueDao {

    void addJournalScientifique(JournalScientifique journalScientifique);
    JournalScientifique getJournalScientifiqueById(int id);
    JournalScientifique getJournalScientifiqueByTitle(String titre);
    List<JournalScientifique> getJournalScientifiques();
    void updateJournalScientifique(JournalScientifique journalScientifique ,int id);
    void deleteJournalScientifique(int id);
}
