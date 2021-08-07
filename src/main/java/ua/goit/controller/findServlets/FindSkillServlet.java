package ua.goit.controller.findServlets;

import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.service.HibernateSkillService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/skill")
public class FindSkillServlet extends HttpServlet {
    private final HibernateSkillService service =
            new HibernateSkillService(HibernateDatabaseConnector.getSessionFactory());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/findById/findSkill.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("result", service.findById(Integer.parseInt(req.getParameter("skillID"))));
        req.getRequestDispatcher("/view/print/printMessage.jsp").forward(req, resp);
    }

}
