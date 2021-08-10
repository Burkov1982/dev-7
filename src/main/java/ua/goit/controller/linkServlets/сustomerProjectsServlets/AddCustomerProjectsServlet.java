package ua.goit.controller.linkServlets.сustomerProjectsServlets;

import ua.goit.config.HibernateDatabaseConnector;
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
import java.util.Set;

import static ua.goit.service.converters.CustomerConverter.fromCustomer;
import static ua.goit.service.converters.CustomerConverter.toCustomer;
import static ua.goit.service.converters.ProjectConverter.fromProject;
import static ua.goit.service.converters.ProjectConverter.toProject;

@WebServlet("/addCustomerProjects")
public class AddCustomerProjectsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/create/links/addCustomerProjectsForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HibernateProjectService projectService = new HibernateProjectService
                    (HibernateDatabaseConnector.getSessionFactory());
            HibernateCustomerService customerService = new HibernateCustomerService
                    (HibernateDatabaseConnector.getSessionFactory());

            ProjectDTO projectDTO = fromProject(projectService.findById
                    (Integer.parseInt(req.getParameter("project_id"))));
            CustomerDTO customerDTO = fromCustomer(customerService.findById
                    (Integer.parseInt(req.getParameter("customer_id"))));

            Set<ProjectDTO> projects = customerDTO.getProjects();
            projects.add(projectDTO);
            customerDTO.setProjects(projects);

            Set<CustomerDTO> customers = projectDTO.getCustomers();
            customers.add(customerDTO);
            projectDTO.setCustomers(customers);

            customerService.update(toCustomer(customerDTO));
            projectService.update(toProject(projectDTO));
            req.setAttribute("result", "Your request has been processed successfully");
        } catch (Exception e) {
            req.setAttribute("result", "An error has occurred, please resend the request");
        }
        req.getRequestDispatcher("/view/print/printMessage.jsp").forward(req, resp);
    }
}
