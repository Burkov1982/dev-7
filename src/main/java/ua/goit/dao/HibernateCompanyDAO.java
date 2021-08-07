package ua.goit.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.goit.dao.model.Company;

import java.util.List;

public class HibernateCompanyDAO implements HibernateDAO<Company>{
    private final SessionFactory sessionFactory;

    public HibernateCompanyDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Company findById(Integer id) throws Exception{
        Company company = null;
        try (Session session = sessionFactory.openSession()) {
            company = session.get(Company.class, id);
            System.out.println(company.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return company;
    }

    @Override
    public void create(Company company) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(company);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Company company) throws Exception{
            try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();
                session.saveOrUpdate(company);
                session.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    @Override
    public void delete(Company company) throws Exception{
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(company);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Company> getAll() throws Exception{
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT a FROM Company a", Company.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
