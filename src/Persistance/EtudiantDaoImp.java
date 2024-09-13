package Persistance;

import Metier.utilisateurs.Etudiant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EtudiantDaoImp implements EtudiantDao {
    private Connection conn;

    public EtudiantDaoImp(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void ajouterEtudiant(Etudiant etudiant) {
        String query = "INSERT INTO etudiant (nom, prenom, email,id_etudiant) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, etudiant.getNom());
            stmt.setString(2, etudiant.getPrenom());
            stmt.setString(3, etudiant.getEmail());
            stmt.setString(4, etudiant.getId_etudiant());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void modifierEtudiant(Etudiant etudiant) {
        String query = "UPDATE etudiant SET nom = ?, prenom = ?, email = ? WHERE id_etudiant = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, etudiant.getNom());
            stmt.setString(2, etudiant.getPrenom());
            stmt.setString(3, etudiant.getEmail());
            stmt.setString(4, etudiant.getId_etudiant());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Etudiant getEtudiantById(String id_etudiant) {
        String query = "SELECT * FROM etudiant WHERE id_etudiant = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, id_etudiant);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Etudiant(
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("id_etudiant"),
                        this
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void supprimerEtudiant(String id_etudiant) {
        String query = "DELETE FROM etudiant WHERE id_etudiant = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, id_etudiant);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Etudiant> getAllEtudiants() {
        List<Etudiant> etudiants = new ArrayList<>();
        String query = "SELECT * FROM etudiant";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Etudiant etudiant = new Etudiant(
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("id_etudiant"),
                        this
                );
                etudiants.add(etudiant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return etudiants;
    }

    @Override
    public boolean etudiantExistsByEmail(String email) {
        String query = "SELECT 1 FROM etudiant WHERE email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isEmailExist(String email) {
        String query = "SELECT COUNT(*) FROM etudiant WHERE email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;  // If count is greater than 0, email exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getEtudiantIdByEmail(String email) {
        int etudiantId = -1;
        String query = "SELECT id FROM etudiant WHERE email = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                etudiantId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return etudiantId;  // Return the student ID or -1 if not found
    }
}


