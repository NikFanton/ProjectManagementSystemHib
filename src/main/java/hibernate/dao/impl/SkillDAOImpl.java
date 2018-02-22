package hibernate.dao.impl;

import hibernate.dao.SkillDAO;
import hibernate.entities.Skill;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class SkillDAOImpl implements SkillDAO {
    public SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Override
    public void add(Skill skill) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(skill);
        transaction.commit();
        session.close();
    }

    @Override
    public Skill getById(Long id) {
        Session session = sessionFactory.openSession();
        Skill skill = session.get(Skill.class, id);
        session.close();
        return skill;
    }

    @Override
    public List<Skill> getAll() {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("FROM Skill");
        List result = query.list();
        session.close();
        return result;
    }

    @Override
    public void update(Skill skill) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(skill);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Long id) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Skill skill = session.get(Skill.class, id);
        session.delete(skill);
        transaction.commit();
        session.close();
    }
}
