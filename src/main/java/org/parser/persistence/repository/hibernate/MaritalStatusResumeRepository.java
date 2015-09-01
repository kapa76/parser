package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.hibernate.Session; import org.hibernate.Transaction; import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MaritalStatusResumeRepository extends AbstractRepo {

    public MaritalStatusResume findOne(Long id) {
        return (MaritalStatusResume) sessionFactory.getCurrentSession().get(MaritalStatusResume.class, id);
    }


    public MaritalStatusResume findOne(String name) {
        return null;
    }


    public List<MaritalStatusResume> findAll() {
        return null;
    }


    public void create(MaritalStatusResume entity) {
        Session session = sessionFactory.openSession();         Transaction tx = session.beginTransaction();         session.save(entity);         tx.commit();         session.close();
    }


    public MaritalStatusResume update(MaritalStatusResume entity) {
        return null;
    }


    public void delete(MaritalStatusResume entity) {

    }


    public void deleteById(long entityId) {

    }
}