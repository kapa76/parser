package org.parser.persistence.repository.hibernate;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.parser.persistence.model.LangLevel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LangLevelRepository extends AbstractRepo {

    public LangLevel findOne(Long id) {
        return (LangLevel) sessionFactory.getCurrentSession().get(LangLevel.class, id);
    }


    public LangLevel findOne(String name) {
        return null;
    }


    public List<LangLevel> findAll() {
        return null;
    }


    public void create(LangLevel entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
    }


    public LangLevel update(LangLevel entity) {
        return null;
    }


    public void delete(LangLevel entity) {

    }


    public void deleteById(long entityId) {

    }

    public LangLevel findOne(String name, long id) {
        return (LangLevel) sessionFactory.getCurrentSession().createQuery("from lang_level p where p.name = :name and p.site.id = :id ").setParameter("id", id).setParameter("name", name).uniqueResult();

    }
}