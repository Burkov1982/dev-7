package ua.goit.controller.skillServlets;

import ua.goit.config.HibernateDatabaseConnector;
import ua.goit.dto.SkillDTO;
import ua.goit.service.HibernateSkillService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.goit.config.converters.SkillConverter.toSkill;

@WebServlet("/deleteSkill")
public class DeleteSkillServlet extends HttpServlet {
    private final HibernateSkillService service =
            new HibernateSkillService(HibernateDatabaseConnector.getSessionFactory());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/delete/deleteSkill.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            SkillDTO skillDTO = new SkillDTO();
            skillDTO.setSkill_id(Integer.parseInt(req.getParameter("skillID")));
            String result = service.delete(toSkill(skillDTO));
            req.setAttribute("result", result);
            req.getRequestDispatcher("/view/print/printMessage.jsp").forward(req, resp);
        } catch (Exception e){
            req.setAttribute("result", "An error has occurred, please resend the request");
            req.getRequestDispatcher("/view/print/printMessage.jsp").forward(req, resp);
        }
    }
}
