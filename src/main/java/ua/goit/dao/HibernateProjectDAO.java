package ua.goit.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.goit.dao.model.Project;

import java.util.List;

public class HibernateProjectDAO implements HibernateDAO<Project> {
    private final SessionFactory sessionFactory;

    public HibernateProjectDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Project findById(Integer id) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Project.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void create(Project entity) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Project entity) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Project entity) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Project> getAll() throws Exception {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT a FROM Project a", Project.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
