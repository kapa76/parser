package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.hibernate.Session; import org.hibernate.Transaction; import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LangLevelResumeRepository extends AbstractRepo {

    public LangLevelResume findOne(Long id) {
        return (LangLevelResume) sessionFactory.getCurrentSession().get(LangLevelResume.class, id);
    }


    public LangLevelResume findOne(String name) {
        return null;
    }


    public List<LangLevelResume> findAll() {
        return null;
    }


    public void create(LangLevelResume entity) {
        Session session = sessionFactory.openSession();         Transaction tx = session.beginTransaction();         session.save(entity);         tx.commit();         session.close();
    }


    public LangLevelResume update(LangLevelResume entity) {
        return null;
    }


    public void delete(LangLevelResume entity) {

    }


    public void deleteById(long entityId) {

    }
}