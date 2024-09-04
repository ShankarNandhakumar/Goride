package com.ta.Goride.Controller;

import com.ta.Goride.dto.*;
import com.ta.Goride.services.RideService;
import com.ta.Goride.dao.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.PrintWriter;

@WebServlet("/ride")
public class RideController extends HttpServlet {

    private RideService rideService;
    private Gson gson = new Gson();

    @Override
    public void init() throws ServletException {
        // Initialize DAOs and Services here or use dependency injection frameworks
    	
        // Example:
        // this.rideService = new RideService(new PassengerDAOImpl(), new DriverDAOImpl(), new TransactionDAOImpl());
    }

    public RideController(RideService rideService, Gson gson) {
		this.rideService = rideService;
		this.gson = gson;
	}

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set response content type to JSON
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            // Read JSON payload from request body
            StringBuilder sb = new StringBuilder();
            String line;
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            // Parse JSON payload
            JsonObject jsonObject = JsonParser.parseString(sb.toString()).getAsJsonObject();
            int passengerId = jsonObject.get("passengerId").getAsInt();
            String otp = jsonObject.get("otp").getAsString();

            
            
            // Call the service to book ride
            boolean success = rideService.bookRide(passengerId, otp);

            // Prepare JSON response
            JsonObject jsonResponse = new JsonObject();
            if (success) {
                jsonResponse.addProperty("status", "success");
                jsonResponse.addProperty("otp", "Valid");
                jsonResponse.addProperty("message", "Ride booked successfully!");
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                jsonResponse.addProperty("status", "failure");
                jsonResponse.addProperty("message", "Failed to book ride. Invalid OTP or no matching drivers available.");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }

            out.print(gson.toJson(jsonResponse));
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JsonObject errorResponse = new JsonObject();
            errorResponse.addProperty("status", "error");
            errorResponse.addProperty("message", "An error occurred while processing the request.");
            out.print(gson.toJson(errorResponse));
            out.flush();
        }
    }
}

