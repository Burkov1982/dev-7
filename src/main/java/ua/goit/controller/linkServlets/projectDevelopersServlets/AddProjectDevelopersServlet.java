package ua.goit.controller.linkServlets.projectDevelopersServlets;

import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.dto.DeveloperDTO;
import ua.goit.dto.ProjectDTO;
import ua.goit.service.HibernateDeveloperService;
import ua.goit.service.HibernateProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

import static ua.goit.service.converters.DeveloperConverter.fromDeveloper;
import static ua.goit.service.converters.DeveloperConverter.toDeveloper;
import static ua.goit.service.converters.ProjectConverter.fromProject;
import static ua.goit.service.converters.ProjectConverter.toProject;

@WebServlet("/addProjectDevelopers")
public class AddProjectDevelopersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/create/links/addProjectDevelopersFrom.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HibernateProjectService projectService = new HibernateProjectService
                    (HibernateDatabaseConnector.getSessionFactory());
            HibernateDeveloperService developerService = new HibernateDeveloperService
                    (HibernateDatabaseConnector.getSessionFactory());

            ProjectDTO projectDTO = fromProject(projectService.findById
                    (Integer.parseInt(req.getParameter("project_id"))));
            DeveloperDTO developerDTO = fromDeveloper(developerService.findById
                    (Integer.parseInt(req.getParameter("developer_id"))));

            Set<ProjectDTO> projects = developerDTO.getProjects();
            projects.add(projectDTO);
            developerDTO.setProjects(projects);

            Set<DeveloperDTO> developers = projectDTO.getDevelopers();
            developers.add(developerDTO);
            projectDTO.setDevelopers(developers);

            developerService.update(toDeveloper(developerDTO));
            projectService.update(toProject(projectDTO));
            req.setAttribute("result", "Your request has been processed successfully");
        } catch (Exception e) {
            req.setAttribute("result", "An error has occurred, please resend the request");
        }
        req.getRequestDispatcher("/view/print/printMessage.jsp").forward(req, resp);
    }
}
