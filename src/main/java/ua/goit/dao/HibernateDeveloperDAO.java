package ua.goit.dao;

import org.hibernate.SessionFactory;
import ua.goit.dao.model.Developer;

public class HibernateDeveloperDAO extends HibernateAbstractDAO<Developer> {
    public HibernateDeveloperDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Developer.class);
    }
}
