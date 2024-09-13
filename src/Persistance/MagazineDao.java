package Persistance;

import Metier.Livre;
import Metier.Magazine;

import java.util.List;
import java.util.Map;

public interface MagazineDao {
    void addMagazine(Magazine magazine);
    Magazine getMagazineById(int id);
    Magazine getMagazineByTitle(String titre);
    List<Magazine> getMagazines();
    void updateMagazine(Magazine magazine ,int id);
    void deleteMagazine(int id);
}
