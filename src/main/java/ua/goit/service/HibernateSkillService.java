package ua.goit.service;

import org.hibernate.SessionFactory;
import ua.goit.dao.HibernateSkillDAO;
import ua.goit.dao.model.Skill;
import ua.goit.view.Util;

import java.util.List;

public class HibernateSkillService implements HibernateService<Skill>{
    private final HibernateSkillDAO dao;

    public HibernateSkillService(SessionFactory sessionFactory) {
        dao = new HibernateSkillDAO(sessionFactory);
    }

    @Override
    public String create(Skill entity) {
        try {
            dao.create(entity);
            return "Your request has been processed successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "An error has occurred, please resend the request";
        }
    }

    @Override
    public String delete(Skill entity) {
        try {
            dao.delete(entity);
            return "Your request has been processed successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "An error has occurred, please resend the request";
        }
    }

    @Override
    public String update(Skill entity) {
        try {
            dao.update(entity);
            return dao.findById(entity.getSkill_id()).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "An error has occurred, please resend the request";
        }

    }

    @Override
    public String findById(Integer id) {
        try {
            return dao.findById(id).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "An error has occurred, please resend the request";
        }
    }

    @Override
    public String getAll() {
        try {
            List<Skill> skills = dao.getAll();
            return Util.joinListElements(skills);
        } catch (Exception e){
            e.printStackTrace();
            return "An error has occurred, please resend the request";
        }

    }
}
