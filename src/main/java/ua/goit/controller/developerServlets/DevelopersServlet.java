package ua.goit.controller.developerServlets;

import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.dao.model.Developer;
import ua.goit.dao.model.Skill;
import ua.goit.service.HibernateDeveloperService;
import ua.goit.view.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.StringJoiner;

@WebServlet("/developers")
public class DevelopersServlet extends HttpServlet {
    private final HibernateDeveloperService service =
            new HibernateDeveloperService(HibernateDatabaseConnector.getSessionFactory());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringJoiner joiner = new StringJoiner("\\n");
        for (Developer developer:service.getAll()) {
            StringBuilder str = null;
            for (Skill skill:developer.getSkills()) {
                str = (str == null ? new StringBuilder("null") : str).append(skill.toString());
            }
            String developerStr = developer.toString();
            joiner.add(developerStr + (str == null ? null : str.toString()) + "\\n ---------------------------");
        }
        System.out.println(joiner);
        req.setAttribute("result", Util.joinSetElements(service.getAll()));
        req.getRequestDispatcher("/view/print/printMessage.jsp").forward(req, resp);
    }


}
