package ua.goit.service;

import org.hibernate.SessionFactory;
import ua.goit.dao.HibernateCustomerDAO;
import ua.goit.dao.model.Customer;

import java.util.Set;

public class HibernateCustomerService implements HibernateService<Customer> {
    private final HibernateCustomerDAO dao;

    public HibernateCustomerService(SessionFactory sessionFactory) {
        dao = new HibernateCustomerDAO(sessionFactory);
    }

    @Override
    public Customer create(Customer entity) {
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
    public String delete(Customer entity) {
        try {
            dao.delete(entity.getCustomer_id());
            return "Your request has been processed successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "An error has occurred, please resend the request";
        }
    }

    @Override
    public Customer update(Customer entity) {
        try {
            dao.update(entity);
            return dao.findById(entity.getCustomer_id());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public Customer findById(Integer id) {
        try {
            return dao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public Set<Customer> getAll() {
        try {
            return dao.getAll();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
