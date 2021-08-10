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

import static ua.goit.service.converters.CompanyConverter.toCompany;

@WebServlet("/updateCompany")
public class UpdateCompanyServlet extends HttpServlet {
    private final HibernateCompanyService hibernateCompanyService =
            new HibernateCompanyService(HibernateDatabaseConnector.getSessionFactory());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/update/updateCompany.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CompanyDTO dto = new CompanyDTO();
        dto.setCompany_id(Integer.parseInt(req.getParameter("companyId")));
        dto.setCompany_name(req.getParameter("companyName"));
        dto.setHeadquarters(req.getParameter("headquarters"));
        req.setAttribute("result", hibernateCompanyService.update(toCompany(dto)).toString());
        req.getRequestDispatcher("/view/print/printMessage.jsp").forward(req, resp);
    }
}
