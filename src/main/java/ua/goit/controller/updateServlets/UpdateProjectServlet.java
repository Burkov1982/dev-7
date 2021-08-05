package ua.goit.controller.updateServlets;

import ua.goit.dto.ProjectDTO;
import ua.goit.service.HibernateProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.goit.service.Converter.toProject;

@WebServlet("/updateProject")
public class UpdateProjectServlet extends HttpServlet {
    private final HibernateProjectService service = new HibernateProjectService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/update/updateProject.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProject_id(Integer.parseInt(req.getParameter("projectId")));
        projectDTO.setProject_name(req.getParameter("projectName"));
        projectDTO.setProject_description(req.getParameter("projectDescription"));
        try {
            projectDTO.setCost(Integer.parseInt(req.getParameter("projectCost")));
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        req.setAttribute("result", service.update(toProject(projectDTO)));
        req.getRequestDispatcher("/view/print/printMessage.jsp").forward(req, resp);
    }
}
