package ua.goit.controller.createServlets;

import ua.goit.dto.CompanyDTO;
import ua.goit.service.CompanyService;
import ua.goit.service.HibernateCompanyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.goit.service.Converter.toCompany;

@WebServlet("/addCompany")
public class AddCompanyServlet extends HttpServlet {
    private final HibernateCompanyService hibernateCompanyService = new HibernateCompanyService();
    private final CompanyService service = new CompanyService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/create/addCompany.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CompanyDTO dto = new CompanyDTO();
        dto.setCompany_name(req.getParameter("companyName"));
        dto.setHeadquarters(req.getParameter("headquarters"));
        /*
            CompanyDTO companyDTO = service.create(dto);
            req.setAttribute("result", companyDTO);
            req.getRequestDispatcher("/view/print/printCompany.jsp").forward(req, resp);
         */
        req.setAttribute("result", hibernateCompanyService.create(toCompany(dto)));
        req.getRequestDispatcher("/view/print/printMessage.jsp").forward(req, resp);
    }
}
