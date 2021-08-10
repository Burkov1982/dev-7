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
import java.util.Set;

import static ua.goit.service.converters.DeveloperConverter.fromDeveloper;
import static ua.goit.service.converters.DeveloperConverter.toDeveloper;
import static ua.goit.service.converters.SkillConverter.fromSkill;
import static ua.goit.service.converters.SkillConverter.toSkill;

@WebServlet("/addDeveloperSkills")
public class AddDeveloperSkillsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/create/links/addDeveloperSkillsFrom.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HibernateSkillService skillService = new HibernateSkillService
                    (HibernateDatabaseConnector.getSessionFactory());
            HibernateDeveloperService developerService = new HibernateDeveloperService
                    (HibernateDatabaseConnector.getSessionFactory());
            SkillDTO skillDTO = fromSkill(skillService.findById
                    (Integer.parseInt(req.getParameter("skill_id"))));
            DeveloperDTO developerDTO = fromDeveloper(developerService.findById
                    (Integer.parseInt(req.getParameter("developer_id"))));

            Set<SkillDTO> skills = developerDTO.getSkills();
            skills.add(skillDTO);
            developerDTO.setSkills(skills);

            Set<DeveloperDTO> developers = skillDTO.getDevelopers();
            developers.add(developerDTO);
            skillDTO.setDevelopers(developers);

            developerService.update(toDeveloper(developerDTO));
            skillService.update(toSkill(skillDTO));
            req.setAttribute("result", "Your request has been processed successfully");
        } catch (Exception e) {
            req.setAttribute("result", "An error has occurred, please resend the request");
        }
        req.getRequestDispatcher("/view/print/printMessage.jsp").forward(req, resp);
    }
}
