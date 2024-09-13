package Metier;

import Metier.interfaces.Empruntable;
import Metier.interfaces.Reservable;
import Persistance.EmprunteDaoImp;
import Persistance.ReserveDaoImp;

import java.sql.*;

public class Bibliotheque implements Reservable, Empruntable {

    private EmprunteDaoImp empruntDao;
    private ReserveDaoImp reserveDaoImp;

    public Bibliotheque(Connection connection) {
        this.empruntDao = new EmprunteDaoImp(connection);
        this.reserveDaoImp = new ReserveDaoImp(connection);
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
    public void reserve(int document_id, int user_id) {
        reserveDaoImp.reserve(document_id, user_id);
    }

    @Override
    public void unreserve(int document_id, int user_id) {
        reserveDaoImp.unreserve(document_id, user_id);

    }

}
