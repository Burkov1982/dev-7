package ua.goit.controller.developerServlets;

import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.service.HibernateDeveloperService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/developer")
public class FindDeveloperServlet extends HttpServlet {
    private final HibernateDeveloperService service =
            new HibernateDeveloperService(HibernateDatabaseConnector.getSessionFactory());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/findById/findDeveloper.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("result", service.findById(Integer.parseInt(req.getParameter("developerID"))).toString());
        req.getRequestDispatcher("/view/print/printMessage.jsp").forward(req, resp);
    }

}
