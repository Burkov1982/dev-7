package ua.goit.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.goit.dao.model.Developer;

import java.util.List;

public class HibernateDeveloperDAO implements HibernateDAO<Developer>{
    private final SessionFactory sessionFactory;

    public HibernateDeveloperDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Developer findById(Integer id) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Developer.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void create(Developer entity) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Developer entity) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Developer entity) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Developer> getAll() throws Exception {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT a FROM Developer a", Developer.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
