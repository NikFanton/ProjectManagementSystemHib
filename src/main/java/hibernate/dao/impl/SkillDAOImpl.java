package hibernate.dao.impl;

import hibernate.dao.SkillDAO;
import hibernate.entities.Skill;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class SkillsDAOImpl implements SkillDAO {
    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Override
    public void add(Skill skill) {

    }

    @Override
    public Skill getById(Long id) {
        return null;
    }

    @Override
    public List<Skill> getAll() {
        return null;
    }

    @Override
    public void update(Skill skill) {

    }

    @Override
    public void delete(Long id) {

    }
}
