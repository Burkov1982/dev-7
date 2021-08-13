package ua.goit.controller.companyServlets;

import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.service.HibernateDeveloperService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/company")
public class FindCompanyServlet extends HttpServlet {
    private final HibernateDeveloperService service =
            new HibernateDeveloperService(HibernateDatabaseConnector.getSessionFactory());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/findById/findCompany.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("result", service.findById(Integer.parseInt(req.getParameter("companyID"))).toString());
            req.getRequestDispatcher("/view/print/printMessage.jsp").forward(req, resp);
        } catch (Exception e){
            req.setAttribute("result", "An error has occurred, please resend the request");
            req.getRequestDispatcher("/view/print/printMessage.jsp").forward(req, resp);
        }
    }
}
