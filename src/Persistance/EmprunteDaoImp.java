package Persistance;

import java.sql.Connection;
import java.sql.*;

public class EmprunteDaoImp {

    private Connection connection;

    public EmprunteDaoImp(Connection connection) {
        this.connection = connection;
    }
    public boolean emprunterDocument(int documentId, int userId) {
        try {
            String checkQuery = "SELECT estEmprunte FROM documents WHERE document_id = ?";
            PreparedStatement checkStmt = connection.prepareStatement(checkQuery);
            checkStmt.setInt(1, documentId);
            ResultSet resultSet = checkStmt.executeQuery();

            if (resultSet.next()) {
                boolean estEmprunte = resultSet.getBoolean("estEmprunte");

                if (estEmprunte) {
                    System.out.println("Document is already borrowed.");
                    return false;
                } else {
                    String updateQuery = "UPDATE documents SET estEmprunte = ?, user_id = ? WHERE id = ?";
                    PreparedStatement updateStmt = connection.prepareStatement(updateQuery);
                    updateStmt.setBoolean(1, true);  // Mark as borrowed
                    updateStmt.setInt(2, userId);    // Set the user who borrowed the document
                    updateStmt.setInt(3, documentId);

                    int rowsUpdated = updateStmt.executeUpdate();

                    if (rowsUpdated > 0) {
                        System.out.println("Document borrowed successfully.");
                        return true;
                    } else {
                        System.out.println("Failed to borrow the document.");
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean retournerDocument(int documentId) {
        try {

            String checkQuery = "SELECT estEmprunte FROM documents WHERE id = ?";
            PreparedStatement checkStmt = connection.prepareStatement(checkQuery);
            checkStmt.setInt(1, documentId);
            ResultSet resultSet = checkStmt.executeQuery();

            if (resultSet.next()) {
                boolean estEmprunte = resultSet.getBoolean("estEmprunte");

                if (!estEmprunte) {
                    System.out.println("Document is not borrowed.");
                    return false;
                } else {
                    String updateQuery = "UPDATE documents SET estEmprunte = ?, user_id = NULL WHERE id = ?";
                    PreparedStatement updateStmt = connection.prepareStatement(updateQuery);
                    updateStmt.setBoolean(1, false);  // Mark as returned
                    updateStmt.setInt(2, documentId);

                    int rowsUpdated = updateStmt.executeUpdate();

                    if (rowsUpdated > 0) {
                        System.out.println("Document returned successfully.");
                        return true;
                    } else {
                        System.out.println("Failed to return the document.");
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
