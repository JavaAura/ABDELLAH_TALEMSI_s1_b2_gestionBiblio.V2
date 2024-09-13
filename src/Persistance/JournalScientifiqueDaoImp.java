package Persistance;

import Metier.JournalScientifique;
import Metier.Livre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JournalScientifiqueDaoImp implements JournalScientifiqueDao{
    private Connection connection;

    public JournalScientifiqueDaoImp(Connection connection) {
        this.connection = connection;

    }
    @Override
    public void addJournalScientifique(JournalScientifique journalScientifique) {
        try {
            String sql = "INSERT INTO journalScientifique(title, author, date_publication, nombre_of_pages,id_journal,domainerecherche ,estemprunter, estreserver) VALUES (?, ?, ?, ?, ?,?, false, false)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, journalScientifique.getTitle());
            ps.setString(2, journalScientifique.getAuthor());
            ps.setDate(3, java.sql.Date.valueOf(journalScientifique.getDate_publication()));
            ps.setInt(4, journalScientifique.getNombre_of_pages());
            ps.setInt(5, journalScientifique.getIdJournal());
            ps.setString(6,journalScientifique.getDomaineRechercher());
            ps.executeUpdate();
            System.out.println("JournalScientifique added successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public JournalScientifique getJournalScientifiqueById(int id) {
        JournalScientifique journalScientifique = null;
        try {
            String sql = "SELECT * FROM journalScientifique WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                journalScientifique = new JournalScientifique(rs.getString("title"), rs.getString("author"),
                        rs.getDate("date_publication").toLocalDate(), rs.getInt("nombre_of_pages"), rs.getString("domainerecherche"), rs.getInt("id_journal"),this );
                journalScientifique.setEstEmprunter(rs.getBoolean("estemprunter"));
                journalScientifique.setEstReserver(rs.getBoolean("estreserver"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return journalScientifique;
    }

    @Override
    public JournalScientifique getJournalScientifiqueByTitle(String titre) {
        return null;
    }

    @Override
    public List<JournalScientifique> getJournalScientifiques() {
        List<JournalScientifique> journalScientifiques = new ArrayList<>();
        try {
            String sql = "SELECT * FROM journalScientifique";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            while (rs.next()) {
                JournalScientifique journalScientifique = new JournalScientifique(rs.getString("title"), rs.getString("author"),
                        rs.getDate("date_publication").toLocalDate(), rs.getInt("nombre_of_pages"),rs.getString("domainerecherche"), rs.getInt("id_journal"),this);
                journalScientifique.setEstEmprunter(rs.getBoolean("estemprunter"));
                journalScientifique.setEstReserver(rs.getBoolean("estreserver"));
                journalScientifique.setId(rs.getInt("id"));
                journalScientifiques.add(journalScientifique);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return journalScientifiques;
    }

    @Override
    public void updateJournalScientifique(JournalScientifique journalScientifique, int id) {
        try {
            String query = "UPDATE journalScientifique SET title = ?, author = ?, date_publication = ?, nombre_of_pages = ?, domainerecherche = ?,id_journal= ? WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, journalScientifique.getTitle());
            pstmt.setString(2, journalScientifique.getAuthor());
            pstmt.setDate(3, java.sql.Date.valueOf(journalScientifique.getDate_publication()));
            pstmt.setInt(4, journalScientifique.getNombre_of_pages());
            pstmt.setString(5, journalScientifique.getDomaineRechercher());
            pstmt.setInt(6, journalScientifique.getIdJournal());
            pstmt.setInt(7,id);
            pstmt.executeUpdate();
            System.out.println("JournalScientifique updated successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteJournalScientifique(int id) {
        try {
            String sql = "DELETE FROM journalScientifique WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
