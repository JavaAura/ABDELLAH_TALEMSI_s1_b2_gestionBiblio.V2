package Persistance;

import Metier.Livre;

import java.util.List;

public interface LivreDao {
   void addLivre(Livre livre);
   LivreDao getLivre(int id);
   LivreDao getLivre(String titre);
   List<LivreDao> getLivres();
   void updateLivre(Livre livre);
   void deleteLivre(int id);
}
