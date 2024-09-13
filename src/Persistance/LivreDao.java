package Persistance;

import Metier.Livre;

import java.util.List;

public interface LivreDao {
   void addLivre(Livre livre);
   Livre getLivreById(int id);
   Livre getLivreByTitle(String titre);
   List<Livre> getLivres();
   void updateLivre(Livre livre ,int id);
   void deleteLivre(int id);
}
