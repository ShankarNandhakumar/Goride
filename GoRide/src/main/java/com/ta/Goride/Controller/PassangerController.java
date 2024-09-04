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
import com.google.gson.Gson;

@WebServlet("/passanger")
public class PassangerController extends HttpServlet {

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

        if (id != null) {
            Passanger passanger = passangerService.findPassangerById(Integer.parseInt(id));
            String passangerJson = gson.toJson(passanger);
            response.getWriter().write(passangerJson);
        } else {
            List<Passanger> passangers = passangerService.getAllPassangers();
            String passangersJson = gson.toJson(passangers);
            response.getWriter().write(passangersJson);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("_method");
        if ("put".equalsIgnoreCase(method)) {
            doPut(request, response);
        } else if ("delete".equalsIgnoreCase(method)) {
            doDelete(request, response);
        } else {
            Passanger passanger = new Passanger();
            passanger.setPassangername(request.getParameter("passangername"));
            passanger.setPreferences(request.getParameter("preferences"));
            passanger.setContactno(Long.parseLong(request.getParameter("contactno")));
            passangerService.addPassanger(passanger);
            response.sendRedirect("passanger.jsp");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Passanger passanger = new Passanger();
        passanger.setPassangerid(Integer.parseInt(request.getParameter("passangerid")));
        passanger.setPassangername(request.getParameter("passangername"));
        passanger.setPreferences(request.getParameter("preferences"));
        passanger.setContactno(Long.parseLong(request.getParameter("contactno")));
        passangerService.updatePassanger(passanger);
        response.sendRedirect("passanger.jsp");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null) {
            passangerService.deletePassanger(Integer.parseInt(id));
        }
        response.sendRedirect("passanger.jsp");
    }


   
}
