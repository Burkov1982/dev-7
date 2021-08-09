package ua.goit.dao;

import org.hibernate.SessionFactory;
import ua.goit.dao.model.Company;

public class HibernateCompanyDAO extends HibernateAbstractDAO<Company>{
    public HibernateCompanyDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Company.class);
    }
}
