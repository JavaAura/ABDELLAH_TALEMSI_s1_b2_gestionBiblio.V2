package Metier.interfaces;

public interface Empruntable {
    boolean emprunt(int documentId, int userId);
    public boolean retourner(int documentId);
}
