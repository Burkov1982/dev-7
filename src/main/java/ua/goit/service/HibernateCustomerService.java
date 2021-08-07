package ua.goit.service;

import org.hibernate.SessionFactory;
import ua.goit.dao.HibernateCustomerDAO;
import ua.goit.dao.model.Customer;
import ua.goit.view.Util;

import java.util.List;

public class HibernateCustomerService implements HibernateService<Customer> {
    private final HibernateCustomerDAO dao;

    public HibernateCustomerService(SessionFactory sessionFactory) {
        dao = new HibernateCustomerDAO(sessionFactory);
    }

    @Override
    public String create(Customer entity) {
        try {
            dao.create(entity);
            return "Your request has been processed successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "An error has occurred, please resend the request";
        }
    }

    @Override
    public String delete(Customer entity) {
        try {
            dao.delete(entity);
            return "Your request has been processed successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "An error has occurred, please resend the request";
        }
    }

    @Override
    public String update(Customer entity) {
        try {
            dao.update(entity);
            return dao.findById(entity.getCustomer_id()).toString();
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
            List<Customer> customers = dao.getAll();
            return Util.joinListElements(customers);
        } catch (Exception e){
            e.printStackTrace();
            return "An error has occurred, please resend the request";
        }
    }
}
