package Persistance;

import Metier.Livre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivreDaoImp implements LivreDao {
    private Connection connection;

    public LivreDaoImp(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addLivre(Livre livre) {
        try {
            String sql = "INSERT INTO livre(title, author, date_publication, nombre_of_pages,isbn ,estemprunter, estreserver) VALUES (?, ?, ?, ?, ?, false, false)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, livre.getTitle());
            ps.setString(2, livre.getAuthor());
            ps.setDate(3, java.sql.Date.valueOf(livre.getDate_publication()));
            ps.setInt(4, livre.getNombre_of_pages());
            ps.setString(5, livre.getIsbn());
            ps.executeUpdate();
            System.out.println("Livre added successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Livre getLivreById(int id) {
        Livre livre = null;
        try {
            String sql = "SELECT * FROM livre WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                livre = new Livre(rs.getString("title"), rs.getString("author"),
                        rs.getDate("date_publication").toLocalDate(), rs.getInt("nombre_of_pages"), rs.getString("isbn"), this);
                livre.setEstEmprunter(rs.getBoolean("estemprunter"));
                livre.setEstReserver(rs.getBoolean("estreserver"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livre;
    }

    @Override
    public Livre getLivreByTitle(String titre) {
        return null;
    }

    @Override
    public List<Livre> getLivres() {
        List<Livre> livres = new ArrayList<>();
        try {
            String sql = "SELECT * FROM livre";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            while (rs.next()) {
                Livre livre = new Livre( rs.getString("title"), rs.getString("author"),
                        rs.getDate("date_publication").toLocalDate(), rs.getInt("nombre_of_pages"), rs.getString("isbn"), this);
                livre.setEstEmprunter(rs.getBoolean("estemprunter"));
                livre.setEstReserver(rs.getBoolean("estreserver"));
                livres.add(livre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livres;
    }

    @Override
    public void updateLivre(Livre livre , int id) {
        try {
            String query = "UPDATE livre SET title = ?, author = ?, date_publication = ?, nombre_of_pages = ?, isbn = ? WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, livre.getTitle());
            pstmt.setString(2, livre.getAuthor());
            pstmt.setDate(3, java.sql.Date.valueOf(livre.getDate_publication()));
            pstmt.setInt(4, livre.getNombre_of_pages());
            pstmt.setString(5, livre.getIsbn());
            pstmt.setInt(6,id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Livre updated successfully.");
            } else {
                System.out.println("Livre not found or update failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteLivre(int id) {
        try {
            String sql = "DELETE FROM livre WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
