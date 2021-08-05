package ua.goit.controller.getAllServlets;

import ua.goit.service.HibernateSkillService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/skills")
public class SkillsServlet extends HttpServlet {
    private final HibernateSkillService service = new HibernateSkillService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("result", service.getAll());
        req.getRequestDispatcher("/view/print/printMessage.jsp").forward(req, resp);
    }
}
