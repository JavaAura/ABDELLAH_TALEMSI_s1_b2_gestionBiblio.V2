package Persistance;

import Metier.interfaces.Reservable;

import java.sql.*;

public class ReserveDaoImp implements Reservable {
    private Connection connection;
    public ReserveDaoImp(Connection connection) {
        this.connection = connection;
    }
    @Override
    public void reserve(int document_id, int user_id) {
        String checkIfBorrowedSql = "SELECT estemprunter FROM document WHERE id = ?";
        String reserveSql = "INSERT INTO reservation (document_id, user_id) VALUES (?, ?)";
        String updateDocumentSql = "UPDATE document SET estreserver = TRUE WHERE id = ?";

        try (PreparedStatement checkBorrowedStatement = connection.prepareStatement(checkIfBorrowedSql);
             PreparedStatement reserveStatement = connection.prepareStatement(reserveSql);
             PreparedStatement updateDocumentStatement = connection.prepareStatement(updateDocumentSql)) {

            checkBorrowedStatement.setInt(1, document_id);
            ResultSet resultSet = checkBorrowedStatement.executeQuery();

            if (resultSet.next()) {
                boolean isBorrowed = resultSet.getBoolean("estemprunter");
                if (isBorrowed) {
                    System.out.println("The document is already borrowed and cannot be reserved.");
                    return;
                }
            }
            reserveStatement.setInt(1, document_id);
            reserveStatement.setInt(2, user_id);
            reserveStatement.executeUpdate();

            updateDocumentStatement.setInt(1, document_id);
            updateDocumentStatement.executeUpdate();

            System.out.println("Document reserved successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to reserve document.");
        }
    }


    @Override
    public void unreserve(int documentId, int userId) {
        String unreserveSql = "DELETE FROM reservation WHERE document_id = ? AND user_id = ?";
        String updateDocumentSql = "UPDATE document SET estreserver = FALSE WHERE id = ?";

        try (PreparedStatement unreserveStatement = connection.prepareStatement(unreserveSql);
             PreparedStatement updateDocumentStatement = connection.prepareStatement(updateDocumentSql)) {

            unreserveStatement.setInt(1, documentId);
            unreserveStatement.setInt(2, userId);
            int rowsAffected = unreserveStatement.executeUpdate();

            if (rowsAffected > 0) {
                updateDocumentStatement.setInt(1, documentId);
                updateDocumentStatement.executeUpdate();
                System.out.println("Document unreserved successfully.");
            } else {
                System.out.println("No reservation found for the document.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to unreserve document.");
        }
    }
}
