package ua.goit.controller.linkServlets.developerSkillsServlets;

import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.dao.model.Developer;
import ua.goit.dao.model.Skill;
import ua.goit.service.HibernateDeveloperService;
import ua.goit.service.HibernateSkillService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteDeveloperSkills")
public class DeleteDeveloperSkillsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/delete/links/deleteDeveloperSkillsFrom.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HibernateSkillService skillService = new HibernateSkillService
                    (HibernateDatabaseConnector.getSessionFactory());
            HibernateDeveloperService developerService = new HibernateDeveloperService
                    (HibernateDatabaseConnector.getSessionFactory());

            Skill skill = skillService.findById(Integer.parseInt(req.getParameter("skill_id")));
            Developer developer = developerService.findById(Integer.parseInt(req.getParameter("developer_id")));
            skill.removeDeveloper(developer);
            skillService.update(skill);
            developerService.update(developer);
            req.setAttribute("result", "Your request has been processed successfully");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("result", "An error has occurred, please resend the request");
        }
        req.getRequestDispatcher("/view/print/printMessage.jsp").forward(req, resp);
    }
}
