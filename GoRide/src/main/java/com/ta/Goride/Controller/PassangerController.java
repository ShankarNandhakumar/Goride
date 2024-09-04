package com.ta.Goride.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ta.Goride.dto.Passanger;
import com.ta.Goride.services.PassangerServices;
import com.ta.Goride.util.PropertyLog4jexample;
import com.google.gson.Gson;
import org.apache.logging.log4j.*;

@WebServlet("/passanger")
public class PassangerController extends HttpServlet {
	
	
private static final Logger logger=LogManager.getLogger(PassangerController.class);
    private PassangerServices passangerService;
    private Gson gson;

    public PassangerController() {
        this.passangerService = new PassangerServices();
        this.gson = new Gson(); // Use Gson to handle JSON
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        response.setContentType("application/json");
        logger.info("Receive the get request message:{}",id);
        if (id != null){
        	try {
            Passanger passanger = passangerService.findPassangerById(Integer.parseInt(id));
            String passangerJson = gson.toJson(passanger);
            response.getWriter().write(passangerJson);
            logger.info("passanger data retrieved successfully:{}",id);
        	}catch(NumberFormatException e) {
        		logger.error("Invalid id :{}",id,e);
        		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\":\"Invalid Id\"}");
        	}
        } else {
            List<Passanger> passangers = passangerService.getAllPassangers();
            String passangersJson = gson.toJson(passangers);
            response.getWriter().write(passangersJson);
           logger.info("Passanger data retrieved Successfully");
           
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("_method");
        logger.info("process post request:{}",method);
        if ("put".equalsIgnoreCase(method)) {
        	logger.debug("Redirecting to put method");
            doPut(request, response);
        } else if ("delete".equalsIgnoreCase(method)) {
        	logger.debug("redirect to delete method");
            doDelete(request, response);
        } else {
            Passanger passanger = new Passanger();
            passanger.setPassangername(request.getParameter("passangername"));
            passanger.setPreferences(request.getParameter("preferences"));
            passanger.setContactno(Long.parseLong(request.getParameter("contactno")));
            logger.info("Add passangers:{}",passanger);
            passangerService.addPassanger(passanger);
            logger.info("Redirect to driver.jsp file");
            response.sendRedirect("Driver.jsp");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Passanger passanger = new Passanger();
        passanger.setPassangerid(Integer.parseInt(request.getParameter("passangerid")));
        passanger.setPassangername(request.getParameter("passangername"));
        passanger.setPreferences(request.getParameter("preferences"));
        passanger.setContactno(Long.parseLong(request.getParameter("contactno")));
       logger.info("update passanger : {}",passanger);
        passangerService.updatePassanger(passanger);
        logger.info("Redirect to driver.jsp file");
        response.sendRedirect("Driver.jsp");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null) {
            passangerService.deletePassanger(Integer.parseInt(id));
        }
        logger.info("redirect to driver.jsp file");
        response.sendRedirect("Driver.jsp");
    }


   
}
