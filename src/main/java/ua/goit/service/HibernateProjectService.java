package ua.goit.service;

import org.hibernate.SessionFactory;
import ua.goit.dao.HibernateProjectDAO;
import ua.goit.dao.model.Project;
import ua.goit.view.Util;

import java.util.List;

public class HibernateProjectService implements HibernateService<Project> {
    private final HibernateProjectDAO dao;

    public HibernateProjectService(SessionFactory sessionFactory) {
        dao = new HibernateProjectDAO(sessionFactory);
    }

    @Override
    public String create(Project entity) {
        try {
            dao.create(entity);
            return "Your request has been processed successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "An error has occurred, please resend the request";
        }
    }

    @Override
    public String delete(Project entity) {
        try {
            dao.delete(entity);
            return "Your request has been processed successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "An error has occurred, please resend the request";
        }
    }

    @Override
    public String update(Project entity) {
        try {
            dao.update(entity);
            return dao.findById(entity.getProject_id()).toString();
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
            List<Project> projects = dao.getAll();
            return Util.joinListElements(projects);
        } catch (Exception e){
            e.printStackTrace();
            return "An error has occurred, please resend the request";
        }
    }
}
