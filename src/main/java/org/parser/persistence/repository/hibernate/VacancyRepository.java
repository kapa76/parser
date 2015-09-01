package org.parser.persistence.repository.hibernate;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.parser.persistence.model.Vacancy;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VacancyRepository extends AbstractRepo {

    public Vacancy findOne(Long id) {
        return (Vacancy) sessionFactory.getCurrentSession().get(Vacancy.class, id);
    }


    public Vacancy findOne(String name) {
        return null;
    }


    public List<Vacancy> findAll() {
        return null;
    }


    public void create(Vacancy entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        if (this.isCommit()) tx.commit();
        session.close();
    }


    public Vacancy update(Vacancy entity) {
        return null;
    }


    public void delete(Vacancy entity) {

    }


    public void deleteById(long entityId) {

    }
}