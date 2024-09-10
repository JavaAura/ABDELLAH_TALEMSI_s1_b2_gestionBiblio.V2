package Persistance;

import Metier.Livre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class LivreDaoImp implements LivreDao {
    private Connection connection;
    public LivreDaoImp(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addLivre(Livre livre) {
        try {
            String sql = "INSERT INTO livre(id, title, author, date_publication, nombre_of_pages, estemprunter, estreserver) VALUES (CAST(? AS uuid), ?, ?, ?, ?, false, false)";
            PreparedStatement ps = connection.prepareStatement(sql);

            // Set parameters
            ps.setString(1, livre.getId());
            ps.setString(2, livre.getTitle());
            ps.setString(3, livre.getAuthor());

            // Convert LocalDate to java.sql.Date
            ps.setDate(4, java.sql.Date.valueOf(livre.getDate_publication()));

            ps.setInt(5, livre.getNombre_of_pages());

            // Execute the query
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }


    }

    @Override
    public LivreDao getLivre(int id) {
        return null;
    }

    @Override
    public LivreDao getLivre(String titre) {
        return null;
    }

    @Override
    public List<LivreDao> getLivres() {
        return List.of();
    }

    @Override
    public void updateLivre(Livre livre) {

    }

    @Override
    public void deleteLivre(int id) {

    }
}
