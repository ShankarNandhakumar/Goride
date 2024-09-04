package com.ta.Goride.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ta.Goride.dto.Driver;
import com.ta.Goride.services.DriverServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/drivers")
public class DriverController extends HttpServlet {
    private DriverServices driverService;
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        driverService = new DriverServices();
        objectMapper =new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	response.setContentType("application/json");
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "findById":
                findDriverById(request, response);
                break;
            case "available":
                listAvailableDrivers(request, response);
                break;
            default:
                listDrivers(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	response.setContentType("application/json");
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            addDriver(request, response);
        } else if ("update".equals(action)) {
            updateDriver(request, response);
        } else if ("delete".equals(action)) {
            deleteDriver(request, response);
        }
    }

    private void addDriver(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Driver drivers = new Driver();
        drivers.setDriverId(Integer.parseInt(request.getParameter("driverid")));
        drivers.setDrivername(request.getParameter("drivername"));
        drivers.setLicenseno(Long.parseLong(request.getParameter("licenseno")));
        drivers.setVehicletype(request.getParameter("vehicletype"));
        drivers.setAvailable(Boolean.parseBoolean(request.getParameter("available")));
        request.setAttribute("drivers", drivers);
        driverService.addDriver(drivers);
        request.getRequestDispatcher("/driverlist.jsp").forward(request, response);
    }

    private void findDriverById(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Driver driver = driverService.findDriverById(id);
        if(driver!=null)
        {
        	response.getWriter().write(objectMapper.writeValueAsString(driver));
        	response.sendRedirect("driverlist.jsp");
        	
        }
        else {
        	response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        	response.getWriter().write("{\"message\":\"driver not found\"}");
        	
        }
    }

    private void updateDriver(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Driver driver = new Driver();
        driver.setDriverId(Integer.parseInt(request.getParameter("driverid")));
        driver.setDrivername(request.getParameter("drivername"));
        driver.setLicenseno(Long.parseLong(request.getParameter("licenseno")));
        driver.setVehicletype(request.getParameter("vehicletype"));
        driver.setAvailable(Boolean.parseBoolean(request.getParameter("available")));

        driverService.updateDriver(driver);
        response.getWriter().write(objectMapper.writeValueAsString(driver));
    }

    private void deleteDriver(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        driverService.deleteDriver(id);
        response.getWriter().write("{\"message\":\"Driver deleted successfully\"}");
    }

    private void listDrivers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Driver> drivers = driverService.getAllDrivers();
      response.getWriter().write(objectMapper.writeValueAsString(drivers)); 
        request.setAttribute("drivers", drivers);
        request.getRequestDispatcher("/driverlist.jsp").forward(request, response);
    }

    private void listAvailableDrivers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Driver> availableDrivers = driverService.findAvailableDrivers();
        response.getWriter().write(objectMapper.writeValueAsString(availableDrivers));
        request.setAttribute("drivers", availableDrivers);
        request.getRequestDispatcher("/available.jsp").forward(request, response);
    }
}
