package ua.goit.service;

import org.hibernate.SessionFactory;
import ua.goit.dao.HibernateProjectDAO;
import ua.goit.dao.model.Project;

import java.util.Set;

public class HibernateProjectService implements HibernateService<Project> {
    private final HibernateProjectDAO dao;

    public HibernateProjectService(SessionFactory sessionFactory) {
        dao = new HibernateProjectDAO(sessionFactory);
    }

    @Override
    public Project create(Project entity) {
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
    public String delete(Project entity) {
        try {
            dao.delete(entity.getProject_id());
            return "Your request has been processed successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "An error has occurred, please resend the request";
        }
    }

    @Override
    public Project update(Project entity) {
        try {
            dao.update(entity);
            return dao.findById(entity.getProject_id());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public Project findById(Integer id) {
        try {
            return dao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public Set<Project> getAll() {
        try {
            Set<Project> projects = dao.getAll();
            return dao.getAll();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
