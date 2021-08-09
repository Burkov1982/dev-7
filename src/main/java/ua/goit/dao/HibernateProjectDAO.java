package ua.goit.dao;

import org.hibernate.SessionFactory;
import ua.goit.dao.model.Project;

public class HibernateProjectDAO extends HibernateAbstractDAO<Project> {
    public HibernateProjectDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Project.class);
    }
}