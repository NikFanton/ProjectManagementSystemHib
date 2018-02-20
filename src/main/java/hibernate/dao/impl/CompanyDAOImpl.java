package hibernate.dao.impl;

import hibernate.dao.CompanyDAO;
import hibernate.entities.Company;
import hibernate.entities.Customer;
import hibernate.entities.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class CompanyDAOImpl implements CompanyDAO {
    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Override
    public void add(Company company) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(company);
        if (company.getProjects() != null) {
            company.getProjects().forEach(project -> session.saveOrUpdate(project));
        }
        transaction.commit();
        session.close();
    }

    @Override
    public Company getById(Long id) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Company company = session.get(Company.class, id);
        session.close();
        return company;
    }

    @Override
    public List<Company> getAll() {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("FROM Company");
        List result = query.list();
        session.close();
        return result;
    }

    @Override
    public void update(Company company) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(company);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Long id) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Company company = session.get(Company.class, id);
        session.delete(company);
        transaction.commit();
        session.close();
    }

    public static void main(String[] args) {
        CompanyDAOImpl companyDAO = new CompanyDAOImpl();
        companyDAO.getAll().forEach(System.out::println);
        companyDAO.sessionFactory.close();
    }
}
