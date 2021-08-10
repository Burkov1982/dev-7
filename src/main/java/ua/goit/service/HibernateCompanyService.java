package ua.goit.service;

import org.hibernate.SessionFactory;
import ua.goit.dao.HibernateCompanyDAO;
import ua.goit.dao.model.Company;

import java.util.Set;

public class HibernateCompanyService implements HibernateService<Company>{
    private final HibernateCompanyDAO dao;

    public HibernateCompanyService(SessionFactory sessionFactory){
        dao = new HibernateCompanyDAO(sessionFactory);
    }

    @Override
    public Company findById(Integer id) {
        try {
            return dao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public Company create(Company company) {
        try {
            Integer identifier = dao.create(company);
            if (identifier!=null) {
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
    public Company update(Company company) {
        try {
            dao.update(company);
            return dao.findById(company.getCompany_id());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public String delete(Company company){
        try {
            dao.delete(company.getCompany_id());
            return "Your request has been processed successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "An error has occurred, please resend the request";
        }
    }

    @Override
    public Set<Company> getAll() {
        try {
            return dao.getAll();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
