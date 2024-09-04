package com.ta.Goride.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ta.Goride.dto.Driver;
import com.ta.Goride.util.DatabaseConnection;

public class DriverDao {

    // Add a new driver to the database
    public void addDriver(Driver driver) {
        String sql = "INSERT INTO drivers (driverid, drivername, licenseno, vehicletype, available) VALUES (?, ?, ?, ?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, driver.getDriverId());
            stmt.setString(2, driver.getDrivername());
            stmt.setLong(3, driver.getLicenseno());
            stmt.setString(4, driver.getVehicletype());
            stmt.setBoolean(5, driver.isAvailable());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
    }

    // Find a driver by their ID
    public Driver findDriverById(int id) {
        String sql = "SELECT * FROM drivers WHERE driverid = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Driver driver = new Driver();
                driver.setDriverId(rs.getInt(1));
                driver.setDrivername(rs.getString(2));
                driver.setLicenseno(rs.getLong(3));
                driver.setVehicletype(rs.getString(4));
                driver.setAvailable(rs.getBoolean(5));
                return driver;
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
        return null; // Return null if the driver is not found
    }

    // Update an existing driver's information
    public void updateDriver(Driver driver) {
        String sql = "UPDATE drivers SET   driverid=?  drivername = ?, licenseno ?, vehicletype = ?, available = ? WHERE driverid = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, driver.getDriverId());
            stmt.setString(2, driver.getDrivername());
            stmt.setLong(3, driver.getLicenseno());
            stmt.setString(4, driver.getVehicletype());
            stmt.setBoolean(5, driver.isAvailable());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
    }

    // Delete a driver from the database by their ID
    public void deleteDriver(int id) {
        String sql = "DELETE FROM drivers WHERE driverid = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
    }

    // Retrieve all drivers from the database
    public List<Driver> getAllDrivers() {
        List<Driver> drivers = new ArrayList<>();
        String sql = "SELECT * FROM drivers";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Driver driver = new Driver();
                driver.setDriverId(rs.getInt(1));
                driver.setDrivername(rs.getString(2));
                driver.setLicenseno(rs.getLong(3));
                driver.setVehicletype(rs.getString(4));
                driver.setAvailable(rs.getBoolean(5));
                drivers.add(driver);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
        return drivers;
    }

    // Find available drivers from the database
    public List<Driver> findAvailableDrivers() {
        List<Driver> availableDrivers = new ArrayList<>();
        String sql = "SELECT * FROM drivers WHERE available = true";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Driver driver = new Driver();
               
                availableDrivers.add(driver);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
        return availableDrivers;
    }
}
