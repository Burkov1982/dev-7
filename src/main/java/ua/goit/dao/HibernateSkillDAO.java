package ua.goit.dao;

import org.hibernate.SessionFactory;
import ua.goit.dao.model.Skill;

public class HibernateSkillDAO extends HibernateAbstractDAO<Skill> {
    public HibernateSkillDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Skill.class);
    }
}
