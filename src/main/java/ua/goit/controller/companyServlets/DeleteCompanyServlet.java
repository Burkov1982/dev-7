package ua.goit.controller.companyServlets;

import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.dto.CompanyDTO;
import ua.goit.service.HibernateCompanyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.goit.config.converters.CompanyConverter.toCompany;

@WebServlet("/deleteCompany")
public class DeleteCompanyServlet extends HttpServlet {
    private final HibernateCompanyService hibernateCompanyService =
            new HibernateCompanyService(HibernateDatabaseConnector.getSessionFactory());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/delete/deleteCompany.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            CompanyDTO companyDTO = new CompanyDTO();
            companyDTO.setCompany_id(Integer.parseInt(req.getParameter("companyID")));
            req.setAttribute("result", hibernateCompanyService.delete(toCompany(companyDTO)));
            req.getRequestDispatcher("/view/print/printMessage.jsp").forward(req, resp);
            req.getRequestDispatcher("/view/print/printMessage.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("result", "An error has occurred, please resend the request");
            req.getRequestDispatcher("/view/print/printMessage.jsp").forward(req, resp);
        }
    }
}
