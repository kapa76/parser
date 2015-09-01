package org.parser.persistence.repository.hibernate;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.parser.persistence.model.EducationHistory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EducationHistoryRepository extends AbstractRepo {

    public EducationHistory findOne(Long id) {
        return (EducationHistory) sessionFactory.getCurrentSession().get(EducationHistory.class, id);
    }


    public EducationHistory findOne(String name) {
        return null;
    }


    public List<EducationHistory> findAll() {
        return null;
    }


    public void create(EducationHistory entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
    }


    public EducationHistory update(EducationHistory entity) {
        return null;
    }


    public void delete(EducationHistory entity) {

    }


    public void deleteById(long entityId) {

    }
}