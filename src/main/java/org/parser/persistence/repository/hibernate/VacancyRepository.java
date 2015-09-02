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
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        if (this.isCommit()) tx.commit();
        session.close();
        return entity;
    }


    public void delete(Vacancy entity) {

    }


    public void deleteById(long entityId) {

    }

    public boolean exist(Long id_client, long internal_id) {
        Vacancy v = (Vacancy) sessionFactory.getCurrentSession().createQuery("from vacancy p where p.id_client = :id_client and p.internal_id = :internal_id ").setParameter("internal_id", internal_id).setParameter("id_client", id_client).uniqueResult();
        if( v != null )
            return true;
        else return false;
    }
}