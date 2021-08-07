package ua.goit.service;

import org.hibernate.SessionFactory;
import ua.goit.dao.HibernateDeveloperDAO;
import ua.goit.dao.model.Developer;
import ua.goit.view.Util;

import java.util.List;

public class HibernateDeveloperService implements HibernateService<Developer>{
    private final HibernateDeveloperDAO dao;

    public HibernateDeveloperService(SessionFactory sessionFactory) {
        dao = new HibernateDeveloperDAO(sessionFactory);
    }

    @Override
    public String create(Developer entity) {
        try {
            dao.create(entity);
            return "Your request has been processed successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "An error has occurred, please resend the request";
        }
    }

    @Override
    public String delete(Developer entity) {
        try {
            dao.delete(entity);
            return "Your request has been processed successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "An error has occurred, please resend the request";
        }
    }

    @Override
    public String update(Developer entity) {
        try {
            dao.update(entity);
            return dao.findById(entity.getDeveloper_id()).toString();
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
            List<Developer> developers = dao.getAll();
            return Util.joinListElements(developers);
        } catch (Exception e){
            e.printStackTrace();
            return "An error has occurred, please resend the request";
        }
    }
}
