package com.ta.Goride.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ta.Goride.dto.Passanger;
import com.ta.Goride.util.DatabaseConnection;

public class PassangerDao {

    // Add a new passenger to the database
    public void addPassanger(Passanger passanger) {
        String sql = "INSERT INTO passangers (Passangerid,Passangername, Preferences, Contactno) VALUES (?,?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

        	  stmt.setInt(1, passanger.getPassangerid());
            stmt.setString(2, passanger.getPassangername());
            stmt.setString(3, passanger.getPreferences());
            stmt.setLong(4, passanger.getContactno());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
    }

    // Find a passenger by their ID
    public Passanger findPassangerById(int id) {
        String sql = "SELECT * FROM passangers WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Passanger passanger = new Passanger();
                passanger.setPassangerid(rs.getInt(1));
                passanger.setPassangername(rs.getString(2));
                passanger.setPreferences(rs.getString(3));
                passanger.setContactno(rs.getLong(4));
                return passanger;
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
        return null; // Return null if the passenger is not found
    }

    // Update an existing passenger's information
    public void updatePassanger(Passanger passanger) {
        String sql = "UPDATE passangers SET Passangerid = ?, Passangername = ?, Preferences = ? WHERE Contactno = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, passanger.getPassangerid());
            stmt.setString(2, passanger.getPassangername());
            stmt.setString(3, passanger.getPreferences());
            stmt.setLong(4, passanger.getContactno());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
    }

    // Delete a passenger from the database by their ID
    public void deletePassanger(int id) {
        String sql = "DELETE FROM passangers WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
    }

    // Retrieve all passengers from the database
    public List<Passanger> getAllPassangers() {
        List<Passanger> passangers = new ArrayList<>();
        String sql = "SELECT * FROM passangers";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Passanger passanger = new Passanger();
                passanger.setPassangerid(rs.getInt(1));
                passanger.setPassangername(rs.getString(2));
                passanger.setPreferences(rs.getString(3));
                passanger.setContactno(rs.getLong(4));
                passangers.add(passanger);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
        return passangers;
    }
}
