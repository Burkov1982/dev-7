package ua.goit.controller.developerServlets;

import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.service.HibernateDeveloperService;
import ua.goit.view.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/developers")
public class DevelopersServlet extends HttpServlet {
    private final HibernateDeveloperService service =
            new HibernateDeveloperService(HibernateDatabaseConnector.getSessionFactory());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("result", Util.joinSetElements(service.getAll()));
        req.getRequestDispatcher("/view/print/printMessage.jsp").forward(req, resp);
    }


}