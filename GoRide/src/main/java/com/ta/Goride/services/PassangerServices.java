package com.ta.Goride.services;

import java.util.List;

import com.ta.Goride.dao.PassangerDao;
import com.ta.Goride.dto.Passanger;

public class PassangerServices extends PassangerDao {
    private PassangerDao passengerDAO;
    
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
}
