package ua.goit.controller.linkServlets.сompanyProjectsServlets;

import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.dao.model.Company;
import ua.goit.dao.model.Project;
import ua.goit.service.HibernateCompanyService;
import ua.goit.service.HibernateProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addCompanyProjects")
public class AddCompanyProjectsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/create/links/addCompanyProjectsFrom.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HibernateProjectService projectService = new HibernateProjectService
                    (HibernateDatabaseConnector.getSessionFactory());
            HibernateCompanyService companyService = new HibernateCompanyService
                    (HibernateDatabaseConnector.getSessionFactory());

            Project project = projectService.findById(Integer.parseInt(req.getParameter("project_id")));
            Company company = companyService.findById(Integer.parseInt(req.getParameter("company_id")));

            if (project!=null && company!=null){
                company.addProject(project);
                companyService.update(company);
                projectService.update(project);
                req.setAttribute("result", "Your request has been processed successfully");
            } else {
                req.setAttribute("result", "An error has occurred, please resend the request");
            }
            req.getRequestDispatcher("/view/print/printMessage.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("result", "An error has occurred, please resend the request");
            req.getRequestDispatcher("/view/print/printMessage.jsp").forward(req, resp);
        }
    }
}
