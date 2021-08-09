package ua.goit.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class HibernateAbstractDAO<T> implements HibernateDAO<T> {
    private final static Logger LOG = LoggerFactory.getLogger(HibernateAbstractDAO.class);

    private final SessionFactory sessionFactory;
    private final Class<T> entityClass;


    protected HibernateAbstractDAO(SessionFactory sessionFactory, Class<T> entityClass) {
        this.sessionFactory = sessionFactory;
        this.entityClass = entityClass;
    }

    @Override
    public void create(T entity) throws Exception{
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception ex) {
            LOG.error("create. ", ex);
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
        }
    }

    @Override
    public T findById(int id) throws Exception{
        T entity = null;
        try (Session session = sessionFactory.openSession()) {
            entity = session.get(entityClass, id);
        } catch (Exception ex) {
            LOG.error("read. ", ex);
        }
        return entity;
    }

    @Override
    public void update(T entity) throws Exception{
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception ex) {
            LOG.error("update. ", ex);
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void delete(int id) throws Exception{
        T toDelete = findById(id);
        if (Objects.nonNull(toDelete)) {
            Transaction transaction = null;
            try (Session session = sessionFactory.openSession()) {
                transaction = session.beginTransaction();
                session.delete(toDelete);
                transaction.commit();

            } catch (Exception ex) {
                LOG.error("delete. ", ex);
                if (Objects.nonNull(transaction)) {
                    transaction.rollback();
                }
            }
        }
    }

    @Override
    public Set<T> getAll() throws Exception{
        Set<T> entities = null;
        try (Session session = sessionFactory.openSession()) {
            entities = new HashSet<>(session.createQuery("FROM " + entityClass.getName(), entityClass).list());
        } catch (Exception ex) {
            LOG.error("readAll. ", ex);
        }
        return entities;
    }
}
