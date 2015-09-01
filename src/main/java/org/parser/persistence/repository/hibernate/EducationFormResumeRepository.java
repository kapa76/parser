package org.parser.persistence.repository.hibernate;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.parser.persistence.model.*;
import org.hibernate.Session; import org.hibernate.Transaction; import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EducationFormResumeRepository extends AbstractRepo {

    public EducationFormResume findOne(Long id) {
        return (EducationFormResume) sessionFactory.getCurrentSession().get(EducationFormResume.class, id);
    }


    public EducationFormResume findOne(String name) {
        return null;
    }


    public List<EducationFormResume> findAll() {
        return null;
    }

    public void create(EducationFormResume entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
    }

    public EducationFormResume update(EducationFormResume entity) {
        return null;
    }


    public void delete(EducationFormResume entity) {

    }

    public void deleteById(long entityId) {

    }
}