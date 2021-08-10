package ua.goit.controller.projectServlets;

import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.service.HibernateProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/project")
public class FindProjectServlet extends HttpServlet {
    private final HibernateProjectService service =
            new HibernateProjectService(HibernateDatabaseConnector.getSessionFactory());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/findById/findProject.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("result", service.findById(Integer.parseInt(req.getParameter("projectID"))).toString());
        req.getRequestDispatcher("/view/print/printMessage.jsp").forward(req, resp);
    }

}
