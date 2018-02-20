package hibernate.dao.impl;

import hibernate.dao.DeveloperDAO;
import hibernate.entities.Developer;
import hibernate.entities.Skill;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeveloperDAOImpl implements DeveloperDAO {
    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Override
    public void add(Developer developer) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(developer);
        if (developer.getSkills() != null) {
            developer.getSkills().forEach(skill -> session.saveOrUpdate(skill));
        }
        transaction.commit();
        session.close();
    }

    @Override
    public Developer getById(Long id) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Developer developer = session.get(Developer.class, id);
        transaction.commit();
        session.close();
        return developer;
    }

    @Override
    public List<Developer> getAll() {
        Session session = this.sessionFactory.openSession();
        List result = session.createQuery("FROM Developer").list();
        session.close();
        return result;
    }

    @Override
    public void update(Developer developer) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(developer);
        if (developer.getSkills() != null) {
            developer.getSkills().forEach(skill -> session.saveOrUpdate(skill));
        }
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Long id) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Developer developer = session.get(Developer.class, id);
        session.delete(developer);
        transaction.commit();
        session.close();
    }

    public static void main(String[] args) {
        DeveloperDAOImpl developerDAO = new DeveloperDAOImpl();
//        Developer developer = developerDAO.getById(34L);
//        Set<Skill> skills = new HashSet<>();
//        skills.add(new Skill("test1", "middle"));
//        skills.add(new Skill("test2", "junior"));
//        developer.setSkills(skills);
//        developer.setName("Test - OK");
//        developerDAO.add(developer);
//        developerDAO.delete(35L);
        developerDAO.getAll().forEach(d -> System.out.println(d));
        developerDAO.sessionFactory.close();
    }
}
