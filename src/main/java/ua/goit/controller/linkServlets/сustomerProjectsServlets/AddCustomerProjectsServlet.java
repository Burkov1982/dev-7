package ua.goit.controller.linkServlets.сustomerProjectsServlets;

import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.dao.model.Customer;
import ua.goit.dao.model.Project;
import ua.goit.service.HibernateCustomerService;
import ua.goit.service.HibernateProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

            Project project = projectService.findById(Integer.parseInt(req.getParameter("project_id")));
            Customer customer = customerService.findById(Integer.parseInt(req.getParameter("customer_id")));

            if (project!=null && customer!=null){
                customer.addProject(project);
                customerService.update(customer);
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
