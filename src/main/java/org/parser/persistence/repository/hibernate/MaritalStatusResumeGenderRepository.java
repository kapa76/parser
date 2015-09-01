package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.hibernate.Session; import org.hibernate.Transaction; import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MaritalStatusResumeGenderRepository extends AbstractRepo {

    public MaritalStatusResumeGender findOne(Long id) {
        return (MaritalStatusResumeGender) sessionFactory.getCurrentSession().get(MaritalStatusResumeGender.class, id);
    }


    public MaritalStatusResumeGender findOne(String name) {
        return null;
    }


    public List<MaritalStatusResumeGender> findAll() {
        return null;
    }


    public void create(MaritalStatusResumeGender entity) {
        Session session = sessionFactory.openSession();         Transaction tx = session.beginTransaction();         session.save(entity);         tx.commit();         session.close();
    }


    public MaritalStatusResumeGender update(MaritalStatusResumeGender entity) {
        return null;
    }


    public void delete(MaritalStatusResumeGender entity) {

    }


    public void deleteById(long entityId) {

    }
}