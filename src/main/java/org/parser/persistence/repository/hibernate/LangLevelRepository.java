package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.hibernate.Session; import org.hibernate.Transaction; import org.springframework.stereotype.Repository;

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
        Session session = sessionFactory.openSession();         Transaction tx = session.beginTransaction();         session.save(entity);         tx.commit();         session.close();
    }


    public LangLevel update(LangLevel entity) {
        return null;
    }


    public void delete(LangLevel entity) {

    }


    public void deleteById(long entityId) {

    }
}