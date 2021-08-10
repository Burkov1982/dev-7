package ua.goit.service;

import org.hibernate.SessionFactory;
import ua.goit.dao.HibernateDeveloperDAO;
import ua.goit.dao.model.Developer;

import java.util.Set;

public class HibernateDeveloperService implements HibernateService<Developer>{
    private final HibernateDeveloperDAO dao;

    public HibernateDeveloperService(SessionFactory sessionFactory) {
        dao = new HibernateDeveloperDAO(sessionFactory);
    }

    @Override
    public Developer create(Developer entity) {
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
    public String delete(Developer entity) {
        try {
            dao.delete(entity.getDeveloper_id());
            return "Your request has been processed successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "An error has occurred, please resend the request";
        }
    }

    @Override
    public Developer update(Developer entity) {
        try {
            dao.update(entity);
            return dao.findById(entity.getDeveloper_id());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public Developer findById(Integer id) {
        try {
            return dao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public Set<Developer> getAll() {
        try {
            return dao.getAll();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
