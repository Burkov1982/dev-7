package ua.goit.controller.linkServlets.сompanyProjectsServlets;

import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.dao.model.Company;
import ua.goit.dao.model.Project;
import ua.goit.dto.CompanyDTO;
import ua.goit.dto.ProjectDTO;
import ua.goit.service.HibernateCompanyService;
import ua.goit.service.HibernateProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.goit.config.converters.CompanyConverter.fromCompany;
import static ua.goit.config.converters.ProjectConverter.fromProject;

@WebServlet("/companyProjects")
public class CompanyProjectsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/getAll/links/companyProjectsFrom.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HibernateCompanyService companyService = new HibernateCompanyService
                    (HibernateDatabaseConnector.getSessionFactory());
            HibernateProjectService projectService = new HibernateProjectService
                    (HibernateDatabaseConnector.getSessionFactory());

            if (req.getParameter("table").equals("project")) {
                Project project = projectService.findById(Integer.parseInt(req.getParameter("id")));
                if (project!=null){
                    ProjectDTO projectDTO = fromProject(project);
                    req.setAttribute("result", projectDTO.toStringWithAssociative("company"));
                } else {
                    req.setAttribute("result", "An error has occurred, please resend the request");
                }
            } else {
                Company company = companyService.findById(Integer.parseInt(req.getParameter("id")));
                if (company!=null){
                    CompanyDTO companyDTO = fromCompany(company);
                    req.setAttribute("result", companyDTO.toStringWithAssociative());
                } else {
                    req.setAttribute("result", "An error has occurred, please resend the request");
                }
            }
            req.getRequestDispatcher("/view/print/printMessage.jsp").forward(req, resp);
        } catch (Exception e){
            req.setAttribute("result", "An error has occurred, please resend the request");
            req.getRequestDispatcher("/view/print/printMessage.jsp").forward(req, resp);
        }
    }
}
