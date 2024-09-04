package com.ta.Goride.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ta.Goride.dto.Transaction;
import com.ta.Goride.util.DatabaseConnection;

public class Transactiondao {

    // Add a new transaction to the database
    public void addTransaction(Transaction transaction) {
        String sql = "INSERT INTO transactions (transactionId,passangerId, driverid, transactionAmount,transactionType,pickuppoint,droppoint,transactionDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

        	stmt.setInt(1, transaction.getTransactionId());
            stmt.setInt(2, transaction.getPassangerid());
            stmt.setInt(3, transaction.getDriverid());
            stmt.setDouble(4, transaction.getTransactionAmount());
            stmt.setString(5, transaction.getTransactionType());
            stmt.setString(6, transaction.getPickuppoint());
            stmt.setString(7,transaction.getDroppoint());
            stmt.setTimestamp(8, transaction.getTransactionDate());
            

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
    }

    // Find a transaction by its ID
    public Transaction findTransactionById(int id) {
        String sql = "SELECT * FROM transactions WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Transaction transaction = new Transaction();
                
                stmt.setInt(1, transaction.getTransactionId());
                stmt.setInt(2, transaction.getPassangerid());
                stmt.setInt(3, transaction.getDriverid());
                stmt.setDouble(4, transaction.getTransactionAmount());
                stmt.setString(5, transaction.getTransactionType());
                stmt.setString(6, transaction.getPickuppoint());
                stmt.setString(7,transaction.getDroppoint());
                stmt.setTimestamp(8, transaction.getTransactionDate());
                
                return transaction;
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
        return null; // Return null if the transaction is not found
    }

    // Retrieve transactions by passenger ID
    public List<Transaction> getTransactionByPassangerId(int passangerId) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE passanger_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, passangerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Transaction transaction = new Transaction();
               
                
                stmt.setInt(1, transaction.getTransactionId());
                stmt.setInt(2, transaction.getPassangerid());
                stmt.setInt(3, transaction.getDriverid());
                stmt.setDouble(4, transaction.getTransactionAmount());
                stmt.setString(5, transaction.getTransactionType());
                stmt.setString(6, transaction.getPickuppoint());
                stmt.setString(7,transaction.getDroppoint());
                stmt.setTimestamp(8, transaction.getTransactionDate());
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
        return transactions;
    }

    // Retrieve transactions by driver ID
    public List<Transaction> getTransactionByDriverId(int driverId) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE driver_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, driverId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Transaction transaction = new Transaction();
                
                stmt.setInt(1, transaction.getTransactionId());
                stmt.setInt(2, transaction.getPassangerid());
                stmt.setInt(3, transaction.getDriverid());
                stmt.setDouble(4, transaction.getTransactionAmount());
                stmt.setString(5, transaction.getTransactionType());
                stmt.setString(6, transaction.getPickuppoint());
                stmt.setString(7,transaction.getDroppoint());
                stmt.setTimestamp(8, transaction.getTransactionDate());
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
        return transactions;
    }

	
}
