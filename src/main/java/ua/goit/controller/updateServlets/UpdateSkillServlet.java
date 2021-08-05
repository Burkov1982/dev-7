package ua.goit.controller.updateServlets;

import ua.goit.dto.SkillDTO;
import ua.goit.service.HibernateSkillService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.goit.service.Converter.toSkill;

@WebServlet("/updateSkill")
public class UpdateSkillServlet extends HttpServlet {
    private final HibernateSkillService service = new HibernateSkillService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/update/updateSkill.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SkillDTO skillDTO = new SkillDTO();
        skillDTO.setSkill_id(Integer.parseInt(req.getParameter("skillId")));
        skillDTO.setBranch(req.getParameter("branch"));
        skillDTO.setStage(req.getParameter("stage"));
        req.setAttribute("result", service.update(toSkill(skillDTO)));
        req.getRequestDispatcher("/view/print/printMessage.jsp").forward(req, resp);
    }
}
