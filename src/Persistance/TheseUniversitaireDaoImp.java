package Persistance;

import Metier.JournalScientifique;
import Metier.TheseUniversitaire;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TheseUniversitaireDaoImp implements TheseUniversitaireDao {
     private Connection connection;

     public TheseUniversitaireDaoImp(Connection connection) {
         this.connection = connection;
     }

    @Override
    public void addTheseUniversitaire(TheseUniversitaire theseUniversitaire) {
        try {
            String sql = "INSERT INTO theseuniversitaire(title, author, date_publication, nombre_of_pages,id_these,universite,domaine,estemprunter, estreserver) VALUES (?, ?, ?, ?, ?,?,?, false, false)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, theseUniversitaire.getTitle());
            ps.setString(2, theseUniversitaire.getAuthor());
            ps.setDate(3, java.sql.Date.valueOf(theseUniversitaire.getDate_publication()));
            ps.setInt(4, theseUniversitaire.getNombre_of_pages());
            ps.setInt(5, theseUniversitaire.getId_theseUniversitaire());
            ps.setString(6,theseUniversitaire.getUniversitaire());
            ps.setString(7,theseUniversitaire.getDomaine());
            ps.executeUpdate();
            System.out.println("theseUniversite added successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public TheseUniversitaire getTheseUniversitaireById(int id) {
        TheseUniversitaire theseUniversitaire = null;
        try {
            String sql = "SELECT * FROM theseuniversitaire WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                theseUniversitaire = new TheseUniversitaire(rs.getString("title"), rs.getString("author"),
                        rs.getDate("date_publication").toLocalDate(), rs.getInt("nombre_of_pages"),rs.getInt("id_these"),rs.getString("universite"),rs.getString("domaine"),this );
                theseUniversitaire.setEstEmprunter(rs.getBoolean("estemprunter"));
                theseUniversitaire.setEstReserver(rs.getBoolean("estreserver"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return theseUniversitaire;
    }

    @Override
    public TheseUniversitaire getTheseUniversitaireByTitle(String titre) {
        return null;
    }

    @Override
    public List<TheseUniversitaire> getTheseUniversitaires() {
        List<TheseUniversitaire> theseUniversitaires = new ArrayList<>();
        try {
            String sql = "SELECT * FROM theseuniversitaire";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            while (rs.next()) {
                TheseUniversitaire theseUniversitaire = new TheseUniversitaire(rs.getString("title"), rs.getString("author"),
                        rs.getDate("date_publication").toLocalDate(), rs.getInt("nombre_of_pages"),rs.getInt("id_these"),rs.getString("universite"),rs.getString("domaine"),this);
                theseUniversitaire.setEstEmprunter(rs.getBoolean("estemprunter"));
                theseUniversitaire.setEstReserver(rs.getBoolean("estreserver"));
                theseUniversitaire.setId(rs.getInt("id"));
                theseUniversitaires.add(theseUniversitaire);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return theseUniversitaires;
    }

    @Override
    public void updateTheseUniversitaire(TheseUniversitaire theseUniversitaire, int id) {
        try {
            String query = "UPDATE theseuniversitaire SET title = ?, author = ?, date_publication = ?, nombre_of_pages = ?, domainerecherche = ?,id_these= ?,universite = ?,domaine =? WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, theseUniversitaire.getTitle());
            pstmt.setString(2, theseUniversitaire.getAuthor());
            pstmt.setDate(3, java.sql.Date.valueOf(theseUniversitaire.getDate_publication()));
            pstmt.setInt(4, theseUniversitaire.getNombre_of_pages());
            pstmt.setInt(5, theseUniversitaire.getId_theseUniversitaire());
            pstmt.setString(6,theseUniversitaire.getUniversitaire());
            pstmt.setString(7,theseUniversitaire.getDomaine());
            pstmt.setInt(8,id);
            pstmt.executeUpdate();
            System.out.println("theseuniversitaire updated successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTheseUniversitaire(int id) {
        try {
            String sql = "DELETE FROM theseuniversitaire WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
