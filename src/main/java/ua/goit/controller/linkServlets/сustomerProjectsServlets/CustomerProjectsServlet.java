package ua.goit.controller.linkServlets.сustomerProjectsServlets;

import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.dao.model.Customer;
import ua.goit.dao.model.Project;
import ua.goit.dto.CustomerDTO;
import ua.goit.dto.ProjectDTO;
import ua.goit.service.HibernateCustomerService;
import ua.goit.service.HibernateProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.goit.config.converters.CustomerConverter.fromCustomer;
import static ua.goit.config.converters.ProjectConverter.fromProject;

@WebServlet("/customerProjects")
public class CustomerProjectsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/getAll/links/customerProjectsForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HibernateCustomerService customerService = new HibernateCustomerService
                    (HibernateDatabaseConnector.getSessionFactory());
            HibernateProjectService projectService = new HibernateProjectService
                    (HibernateDatabaseConnector.getSessionFactory());

            if (req.getParameter("table").equals("project")) {
                Project project = projectService.findById(Integer.parseInt(req.getParameter("id")));
                if (project!=null){
                    ProjectDTO projectDTO = fromProject(project);
                    req.setAttribute("result", projectDTO.toStringWithAssociative("customer"));
                } else {
                    req.setAttribute("result", "An error has occurred, please resend the request");
                }
            } else {
                Customer customer = customerService.findById(Integer.parseInt(req.getParameter("id")));
                if (customer!=null){
                    CustomerDTO customerDTO = fromCustomer(customer);
                    req.setAttribute("result", customerDTO.toStringWithAssociative());
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
