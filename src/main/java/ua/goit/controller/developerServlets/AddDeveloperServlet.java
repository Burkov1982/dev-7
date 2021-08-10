package ua.goit.controller.developerServlets;

import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.dto.DeveloperDTO;
import ua.goit.service.HibernateDeveloperService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.goit.service.converters.DeveloperConverter.toDeveloper;

@WebServlet("/addDeveloper")
public class AddDeveloperServlet extends HttpServlet {
    private final HibernateDeveloperService service =
            new HibernateDeveloperService(HibernateDatabaseConnector.getSessionFactory());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/create/addDeveloper.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DeveloperDTO developerDTO = new DeveloperDTO();
        developerDTO.setFirst_name(req.getParameter("firstname"));
        developerDTO.setLast_name(req.getParameter("lastname"));
        developerDTO.setGender(req.getParameter("gender"));
        developerDTO.setSalary(Integer.parseInt(req.getParameter("salary")));
        req.setAttribute("result", service.create(toDeveloper(developerDTO)).toString());
        req.getRequestDispatcher("/view/print/printMessage.jsp").forward(req, resp);
    }
}
