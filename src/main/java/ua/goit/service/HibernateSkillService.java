package ua.goit.service;

import org.hibernate.SessionFactory;
import ua.goit.dao.HibernateSkillDAO;
import ua.goit.dao.model.Skill;

import java.util.Set;

public class HibernateSkillService implements HibernateService<Skill>{
    private final HibernateSkillDAO dao;

    public HibernateSkillService(SessionFactory sessionFactory) {
        dao = new HibernateSkillDAO(sessionFactory);
    }

    @Override
    public Skill create(Skill entity) {
        try {
            Integer identifier = dao.create(entity);
            if (identifier!=null){
                return dao.findById(identifier);
            } else {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public String delete(Skill entity) {
        try {
            dao.delete(entity.getSkill_id());
            return "Your request has been processed successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "An error has occurred, please resend the request";
        }
    }

    @Override
    public Skill update(Skill entity) {
        try {
            dao.update(entity);
            return dao.findById(entity.getSkill_id());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public Skill findById(Integer id) {
        try {
            return dao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public Set<Skill> getAll() {
        try {
            Set<Skill> skills = dao.getAll();
            return dao.getAll();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }

    }
}
