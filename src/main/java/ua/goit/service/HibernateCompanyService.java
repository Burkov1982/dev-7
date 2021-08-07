package ua.goit.service;

import org.hibernate.SessionFactory;
import ua.goit.dao.HibernateCompanyDAO;
import ua.goit.dao.model.Company;
import ua.goit.view.Util;

import java.util.List;

public class HibernateCompanyService implements HibernateService<Company>{
    private final HibernateCompanyDAO dao;

    public HibernateCompanyService(SessionFactory sessionFactory){
        dao = new HibernateCompanyDAO(sessionFactory);
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
    public String create(Company company) {
        try {
            dao.create(company);
            return "Your request has been processed successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "An error has occurred, please resend the request";
        }
    }

    @Override
    public String update(Company company) {
        try {
            dao.update(company);
            return dao.findById(company.getCompany_id()).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "An error has occurred, please resend the request";
        }
    }

    @Override
    public String delete(Company company){
        try {
            dao.delete(company);
            return "Your request has been processed successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "An error has occurred, please resend the request";
        }
    }

    @Override
    public String getAll() {
        try {
            List<Company> companies = dao.getAll();
            return Util.joinListElements(companies);
        } catch (Exception e){
            e.printStackTrace();
            return "An error has occurred, please resend the request";
        }
    }
}
