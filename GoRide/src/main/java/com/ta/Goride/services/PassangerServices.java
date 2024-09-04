package com.ta.Goride.services;

import java.util.List;

import com.ta.Goride.dao.PassangerDao;
import com.ta.Goride.dto.Driver;
import com.ta.Goride.dto.Passanger;

public class PassangerServices extends PassangerDao {
     PassangerDao  passengerDAO=new PassangerDao();
    
    public void addPassenger(Passanger passenger) {
    	
        passengerDAO.addPassanger(passenger);
    }

 
    public Passanger findPassengerById(int id) {
        return passengerDAO.findPassangerById(id);
    }

    public List<Passanger> getAllPassengers() {
        return passengerDAO.getAllPassangers();
    }

    
    public void updatePassenger(Passanger passenger) {
        passengerDAO.updatePassanger(passenger);
    }

 
    public void deletePassengerById(int id) {
        passengerDAO.deletePassanger(id);
    }
    
    
    public boolean bookRide(int passengerId, String otp) {
    	
		return false;
               }

		public boolean validateOTP(int passengerId, String otp) {
		
		
		return false;
	}
    
}
