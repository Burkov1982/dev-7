package ua.goit.controller.linkServlets.developerSkillsServlets;

import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.dto.DeveloperDTO;
import ua.goit.dto.SkillDTO;
import ua.goit.service.HibernateDeveloperService;
import ua.goit.service.HibernateSkillService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.goit.config.converters.DeveloperConverter.fromDeveloper;
import static ua.goit.config.converters.SkillConverter.fromSkill;

@WebServlet("/developerSkills")
public class DeveloperSkillsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/getAll/links/developerSkillsFrom.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HibernateDeveloperService developerService = new HibernateDeveloperService
                (HibernateDatabaseConnector.getSessionFactory());
        HibernateSkillService skillService = new HibernateSkillService
                (HibernateDatabaseConnector.getSessionFactory());
        if (req.getParameter("table").equals("developer")) {
            DeveloperDTO developerDTO = fromDeveloper(developerService.findById(Integer.parseInt(req.getParameter("id"))));
            req.setAttribute("result", developerDTO.toStringWithAssociative("skill"));
        } else {
            SkillDTO skillDTO = fromSkill(skillService.findById(Integer.parseInt(req.getParameter("id"))));
            req.setAttribute("result", skillDTO.toStringWithAssociative());
        }
        req.getRequestDispatcher("/view/print/printMessage.jsp").forward(req, resp);
    }
}
