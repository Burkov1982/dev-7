package ua.goit.controller.skillServlets;

import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.service.HibernateSkillService;
import ua.goit.view.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/skills")
public class SkillsServlet extends HttpServlet {
    private final HibernateSkillService service =
            new HibernateSkillService(HibernateDatabaseConnector.getSessionFactory());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("result", Util.joinSetElements(service.getAll()));
        req.getRequestDispatcher("/view/print/printMessage.jsp").forward(req, resp);
    }
}
