package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.hibernate.Session; import org.hibernate.Transaction; import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EducationRepository extends AbstractRepo {

    public Education findOne(Long id) {
        return (Education) sessionFactory.getCurrentSession().get(Education.class, id);
    }


    public Education findOne(String name) {
        return null;
    }


    public List<Education> findAll() {
        return null;
    }


    public void create(Education entity) {
        Session session = sessionFactory.openSession();         Transaction tx = session.beginTransaction();         session.save(entity);         tx.commit();         session.close();
    }


    public Education update(Education entity) {
        return null;
    }


    public void delete(Education entity) {

    }


    public void deleteById(long entityId) {

    }
}