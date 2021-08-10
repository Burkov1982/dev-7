package ua.goit.controller.linkServlets.сompanyProjectsServlets;

import ua.goit.config.HibernateDatabaseConnector;
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
import java.util.Set;

import static ua.goit.service.converters.CompanyConverter.fromCompany;
import static ua.goit.service.converters.CompanyConverter.toCompany;
import static ua.goit.service.converters.ProjectConverter.fromProject;
import static ua.goit.service.converters.ProjectConverter.toProject;

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

            ProjectDTO projectDTO = fromProject(projectService.findById
                    (Integer.parseInt(req.getParameter("project_id"))));
            CompanyDTO companyDTO = fromCompany(companyService.findById
                    (Integer.parseInt(req.getParameter("company_id"))));

            Set<ProjectDTO> projects = companyDTO.getProjects();
            projects.add(projectDTO);
            companyDTO.setProjects(projects);

            Set<CompanyDTO> companies = projectDTO.getCompanies();
            companies.add(companyDTO);
            projectDTO.setCompanies(companies);

            companyService.update(toCompany(companyDTO));
            projectService.update(toProject(projectDTO));
            req.setAttribute("result", "Your request has been processed successfully");
        } catch (Exception e) {
            req.setAttribute("result", "An error has occurred, please resend the request");
        }
        req.getRequestDispatcher("/view/print/printMessage.jsp").forward(req, resp);
    }
}
