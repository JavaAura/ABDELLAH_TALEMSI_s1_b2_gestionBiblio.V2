package Metier;

import Metier.interfaces.Empruntable;
import Metier.interfaces.Reservable;
import Persistance.EmprunteDaoImp;

import java.sql.*;

public class Bibliotheque implements Reservable, Empruntable {

    private EmprunteDaoImp empruntDao;

    public Bibliotheque(Connection connection) {
        this.empruntDao = new EmprunteDaoImp(connection);
    }

    @Override
    public boolean emprunt(int documentId, int userId) {
        return empruntDao.emprunterDocument(documentId, userId);
    }

    @Override
    public boolean retourner(int documentId) {
        return empruntDao.retournerDocument(documentId);
    }

    @Override
    public void reserve() {

    }

    @Override
    public void unreserve() {

    }

}
