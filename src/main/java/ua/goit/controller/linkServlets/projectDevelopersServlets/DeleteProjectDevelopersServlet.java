package ua.goit.controller.linkServlets.projectDevelopersServlets;

import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.dao.model.Developer;
import ua.goit.dao.model.Project;
import ua.goit.service.HibernateDeveloperService;
import ua.goit.service.HibernateProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteProjectDevelopers")
public class DeleteProjectDevelopersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/delete/links/deleteProjectDevelopersForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HibernateProjectService projectService = new HibernateProjectService
                    (HibernateDatabaseConnector.getSessionFactory());
            HibernateDeveloperService developerService = new HibernateDeveloperService
                    (HibernateDatabaseConnector.getSessionFactory());

            Project project = projectService.findById(Integer.parseInt(req.getParameter("project_id")));
            Developer developer = developerService.findById(Integer.parseInt(req.getParameter("developer_id")));

            project.removeDeveloper(developer);

            projectService.update(project);
            developerService.delete(developer);
            req.setAttribute("result", "Your request has been processed successfully");
        } catch (Exception e) {
            req.setAttribute("result", "An error has occurred, please resend the request");
        }
        req.getRequestDispatcher("/view/print/printMessage.jsp").forward(req, resp);
    }
}
