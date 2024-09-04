package com.ta.Goride.services;

import java.util.List;

import com.ta.Goride.dao.DriverDao;
import com.ta.Goride.dao.PassangerDao;
import com.ta.Goride.dao.Transactiondao;
import com.ta.Goride.dto.Driver;
import com.ta.Goride.dto.Passanger;
import com.ta.Goride.dto.Transaction;

public class RideService {

	
	    private PassangerDao passengerDAO;
	    private DriverDao driverDAO;
	    private Transactiondao transactionDAO;
	    
	   
	    
	    public RideService(PassangerDao passengerDAO, DriverDao driverDAO, Transactiondao transactionDAO) {
			this.passengerDAO = passengerDAO;
			this.driverDAO = driverDAO;
			this.transactionDAO = transactionDAO;
		}

		public boolean bookRide(int passengerId, String otp) {
	        Passanger passenger = passengerDAO.findPassangerById(passengerId);
	        if (!passengerDAO.validateOTP(passengerId, otp)) {
	            return false;
	        }
	        
	        List<Driver> availableDrivers = driverDAO.findAvailableDrivers();
	        
	        if (!availableDrivers.isEmpty()) {
	            Driver selectedDriver = availableDrivers.get(0); // select the first matching driver for simplicity
	            passengerDAO.bookRide(passengerId, otp);
	            
	            Transaction transaction = new Transaction();
	            transaction.setPassangerid(passengerId);
	            transaction.setDriverid(selectedDriver.getDriverId());
	            transaction.setTransactionAmount(calculateFareBasedOnDistance(25.0));
	            transactionDAO.addTransaction(transaction);
	            return true;
	        }
	        
	        return false;
	    }
	    
		
		

		
	
		private double calculateFareBasedOnDistance(double distance) {
		    double fare = 0.0;

		    if (distance >= 0 && distance <= 20) {
		        fare = 25.0;
		    } else if (distance > 20 && distance <= 40) {
		        fare = 50.0;
		    } else if (distance > 40 && distance <= 60) {
		    	fare=75.0;
		    }
		    else if(distance>60&&distance<=80){
		    	fare=100;
		    }
		    else if(distance>80&&distance<=100){
		    	fare=150;
		    }
		       else {
		        fare = 200.0; // For any distance greater than 100 km
		    }

		    return fare;
		}



}
