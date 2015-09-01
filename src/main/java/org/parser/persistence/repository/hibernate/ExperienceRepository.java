package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.hibernate.Session; import org.hibernate.Transaction; import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExperienceRepository extends AbstractRepo {

    public Experience findOne(Long id) {
        return (Experience) sessionFactory.getCurrentSession().get(Experience.class, id);
    }


    public Experience findOne(String name) {
        return null;
    }


    public List<Experience> findAll() {
        return null;
    }


    public void create(Experience entity) {
        Session session = sessionFactory.openSession();         Transaction tx = session.beginTransaction();         session.save(entity);         tx.commit();         session.close();
    }


    public Experience update(Experience entity) {
        return null;
    }


    public void delete(Experience entity) {

    }


    public void deleteById(long entityId) {

    }
}