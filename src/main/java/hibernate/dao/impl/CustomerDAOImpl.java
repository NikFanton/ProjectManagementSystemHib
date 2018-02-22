package hibernate.dao.impl;

import hibernate.dao.CustomerDAO;
import hibernate.entities.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    public SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Override
    public void add(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(customer);
        if (customer.getProjects() != null) {
            customer.getProjects().forEach(project -> session.saveOrUpdate(project));
        }
        transaction.commit();
        session.close();
    }

    @Override
    public Customer getById(Long id) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = session.get(Customer.class, id);
        transaction.commit();
        session.close();
        return customer;
    }

    @Override
    public List<Customer> getAll() {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("FROM Customer");
        List result = query.list();
        session.close();
        return result;
    }

    @Override
    public void update(Customer customer) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(customer);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Long id) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = session.get(Customer.class, id);
        session.delete(customer);
        transaction.commit();
        session.close();
    }
}
