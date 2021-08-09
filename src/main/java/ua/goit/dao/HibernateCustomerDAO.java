package ua.goit.dao;

import org.hibernate.SessionFactory;
import ua.goit.dao.model.Customer;

public class HibernateCustomerDAO extends HibernateAbstractDAO<Customer> {
    public HibernateCustomerDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Customer.class);
    }
}
