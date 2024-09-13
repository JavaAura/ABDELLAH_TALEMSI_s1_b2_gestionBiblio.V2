package Persistance;
import Metier.utilisateurs.Professeur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfesseurDaoImp implements ProfesseurDao {
    private Connection conn;

    public ProfesseurDaoImp(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void ajouterProfesseur(Professeur professeur) {
        String query = "INSERT INTO professeur (nom, prenom, email, id_professeur) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, professeur.getNom());
            stmt.setString(2, professeur.getPrenom());
            stmt.setString(3, professeur.getEmail());
            stmt.setString(4, professeur.getId_professeur());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modifierProfesseur(Professeur professeur) {
        String query = "UPDATE professeur SET nom = ?, prenom = ?, email = ? WHERE id_professeur = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, professeur.getNom());
            stmt.setString(2, professeur.getPrenom());
            stmt.setString(3, professeur.getEmail());
            stmt.setString(4, professeur.getId_professeur());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Professeur getProfesseurById(String id_professeur) {
        String query = "SELECT * FROM professeur WHERE id_professeur = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, id_professeur);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Professeur(
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("id_professeur"),
                        this
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void supprimerProfesseur(String id_professeur) {
        String query = "DELETE FROM professeur WHERE id_professeur = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, id_professeur);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Professeur> getAllProfesseurs() {
        List<Professeur> professeurs = new ArrayList<>();
        String query = "SELECT * FROM professeur";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Professeur professeur = new Professeur(
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("id_professeur"),
                        this
                );
                professeurs.add(professeur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return professeurs;
    }

    @Override
    public boolean professeurExistsByEmail(String email) {
        String query = "SELECT COUNT(*) FROM professeur WHERE email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean isEmailExist(String email) {
        return professeurExistsByEmail(email); // Delegate to the method checking existence
    }



}
