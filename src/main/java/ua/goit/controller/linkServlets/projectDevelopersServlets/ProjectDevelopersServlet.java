package ua.goit.controller.linkServlets.projectDevelopersServlets;

import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.dao.model.Project;
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

import static ua.goit.config.converters.DeveloperConverter.fromDeveloper;
import static ua.goit.config.converters.ProjectConverter.fromProject;

@WebServlet("/projectDevelopers")
public class ProjectDevelopersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/getAll/links/projectDevelopersFrom.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HibernateDeveloperService developerService = new HibernateDeveloperService
                    (HibernateDatabaseConnector.getSessionFactory());
            HibernateProjectService projectService = new HibernateProjectService
                    (HibernateDatabaseConnector.getSessionFactory());

            if (req.getParameter("table").equals("project")) {
                Project project = projectService.findById(Integer.parseInt(req.getParameter("id")));
                if (project!=null){
                    ProjectDTO projectDTO = fromProject(project);
                    req.setAttribute("result", projectDTO.toStringWithAssociative("developer"));
                } else {
                    req.setAttribute("result", "An error has occurred, please resend the request");
                }
            } else {
                DeveloperDTO developerDTO = fromDeveloper(developerService.findById(Integer.parseInt(req.getParameter("id"))));
                req.setAttribute("result", developerDTO.toStringWithAssociative("project"));
            }
            req.getRequestDispatcher("/view/print/printMessage.jsp").forward(req, resp);
        } catch (Exception e){
            req.setAttribute("result", "An error has occurred, please resend the request");
            req.getRequestDispatcher("/view/print/printMessage.jsp").forward(req, resp);
        }
    }
}
