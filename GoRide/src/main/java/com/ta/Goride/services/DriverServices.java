package com.ta.Goride.services;


import com.ta.Goride.dao.DriverDao;
import com.ta.Goride.dto.Driver;

import java.util.List;

public class DriverServices {
     DriverDao driverDao =new DriverDao() ;

    public DriverServices() {
        this.driverDao = new DriverDao();
    }

    // Add a new driver
    public void addDriver(Driver driver) {
    	if(isValidDriver(driver))
        driverDao.addDriver(driver);
    	else
    	{
    		throw new  IllegalArgumentException("INVALID DRIVER DETAILS "); 
    	}
    }

    // Find a driver by ID
    public Driver findDriverById(int id) {
    	
    	Driver driver = driverDao.findDriverById(id);
        if (driver == null) {
            throw new IllegalArgumentException("Driver not found");
        }
        return driver;
        
    }

    // Update an existing driver
    public void updateDriver(Driver driver) {
    	
    	
    	 if (isValidDriver(driver)) {
             Driver existingDriver = driverDao.findDriverById(driver.getDriverId());
             if (existingDriver != null) {
                 driverDao.updateDriver(driver);
             } else {
                 throw new IllegalArgumentException("Driver not found");
             }
         } else {
             throw new IllegalArgumentException("Invalid driver details");
         }
    
    }

    // Delete a driver by ID
    public void deleteDriver(int id) {
    	 Driver existingDriver = driverDao.findDriverById(id);
         if (existingDriver != null) {
             driverDao.deleteDriver(id);
         } else {
             throw new IllegalArgumentException("Driver not found");
         }
    }

    // Get all drivers
    public List<Driver> getAllDrivers() {
    	
        return driverDao.getAllDrivers();
    }

    // Find available drivers
    public List<Driver> findAvailableDrivers() {
        return driverDao.findAvailableDrivers();
    }
    
    private boolean isValidDriver(Driver driver) {
   
        return driver.getDriverId() > 0 &&
               driver.getDrivername() != null && !driver.getDrivername().isEmpty() &&
               driver.getLicenseno() > 0 &&
               driver.getVehicletype() != null && !driver.getVehicletype().isEmpty();
    }
}

