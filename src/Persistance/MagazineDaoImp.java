package Persistance;

import Metier.Livre;
import Metier.Magazine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MagazineDaoImp implements MagazineDao {
   private Connection con;

   public MagazineDaoImp(Connection con) {
       this.con =con;
   }
    @Override
    public void addMagazine(Magazine magazine) {
        try {
            String sql = "INSERT INTO magazine(title, author, date_publication, nombre_of_pages,numero ,estemprunter, estreserver) VALUES (?, ?, ?, ?, ?, false, false)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, magazine.getTitle());
            ps.setString(2, magazine.getAuthor());
            ps.setDate(3, java.sql.Date.valueOf(magazine.getDate_publication()));
            ps.setInt(4, magazine.getNombre_of_pages());
            ps.setInt(5, magazine.getNumero());
            ps.executeUpdate();
            System.out.println("Magazine added successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public Magazine getMagazineById(int id) {
       Magazine magazine = null;
        try {
            String sql = "SELECT * FROM magazine WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                magazine = new Magazine(rs.getString("title"), rs.getString("author"),
                        rs.getDate("date_publication").toLocalDate(), rs.getInt("nombre_of_pages"), rs.getInt("numero"), this);
                magazine.setEstEmprunter(rs.getBoolean("estemprunter"));
                magazine.setEstReserver(rs.getBoolean("estreserver"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return magazine;
    }

    @Override
    public Magazine getMagazineByTitle(String titre) {
        return null;
    }

    @Override
    public List<Magazine> getMagazines() {
        List<Magazine> magazines = new ArrayList<>();
        try {
            String sql = "SELECT * FROM magazine";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                Magazine magazine = new Magazine( rs.getString("title"), rs.getString("author"),
                        rs.getDate("date_publication").toLocalDate(), rs.getInt("nombre_of_pages"), rs.getInt("numero"), this);
                magazine.setEstEmprunter(rs.getBoolean("estemprunter"));
                magazine.setEstReserver(rs.getBoolean("estreserver"));
                magazines.add(magazine);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return magazines;
    }

    @Override
    public void updateMagazine(Magazine magazine, int id) {
        try {
            String query = "UPDATE magazine SET title = ?, author = ?, date_publication = ?, nombre_of_pages = ?, numero = ? WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, magazine.getTitle());
            pstmt.setString(2, magazine.getAuthor());
            pstmt.setDate(3, java.sql.Date.valueOf(magazine.getDate_publication()));
            pstmt.setInt(4, magazine.getNombre_of_pages());
            pstmt.setInt(5, magazine.getNumero());
            pstmt.setInt(6,id);
            pstmt.executeUpdate();
            System.out.println("Magazine updated successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteMagazine(int id) {
        try {
            String sql = "DELETE FROM magazine WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
