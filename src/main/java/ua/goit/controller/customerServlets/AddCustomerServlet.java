package ua.goit.controller.customerServlets;

import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.dto.CustomerDTO;
import ua.goit.service.HibernateCustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.goit.config.converters.CustomerConverter.toCustomer;

@WebServlet("/addCustomer")
public class AddCustomerServlet extends HttpServlet {
    private final HibernateCustomerService service =
            new HibernateCustomerService(HibernateDatabaseConnector.getSessionFactory());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/create/addCustomer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomer_name(req.getParameter("customerName"));
        req.setAttribute("result", service.create(toCustomer(customerDTO)).toString());
        req.getRequestDispatcher("/view/print/printMessage.jsp").forward(req, resp);
    }
}
