package ua.goit.controller.projectServlets;

import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.dto.ProjectDTO;
import ua.goit.service.HibernateProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.goit.config.converters.ProjectConverter.toProject;

@WebServlet("/deleteProject")
public class DeleteProjectServlet extends HttpServlet {
    private final HibernateProjectService service =
            new HibernateProjectService(HibernateDatabaseConnector.getSessionFactory());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/delete/deleteProject.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ProjectDTO projectDTO = new ProjectDTO();
            projectDTO.setProject_id(Integer.parseInt(req.getParameter("projectID")));
            String result = service.delete(toProject(projectDTO));
            req.setAttribute("result", result);
            req.getRequestDispatcher("/view/print/printMessage.jsp").forward(req, resp);
        } catch (Exception e){
            req.setAttribute("result", "An error has occurred, please resend the request");
            req.getRequestDispatcher("/view/print/printMessage.jsp").forward(req, resp);
        }
    }
}
