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
        tx.commit();
        session.close();
    }


    public Vacancy update(Vacancy entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
        return entity;
    }


    public void delete(Vacancy entity) {

    }


    public void deleteById(long entityId) {

    }

    public boolean findByInternal(Long id) {
        Integer count = (Integer)  sessionFactory.getCurrentSession().createQuery("select count(*) from vacancy p where p.vacancy_internale_id = :id").setParameter("id", id).uniqueResult();
        if (count > 0 )
            return true;
        else return false;
    }

    public boolean findByLink(String url, int type) {
        Integer count = (Integer) sessionFactory.getCurrentSession().createQuery("select count(*) from vacancy p where p.url = :link and p.systemId = :id").setParameter("link", url).setParameter("id", type).uniqueResult();
        if (count > 0)
            return true;
        else return false;
    }
}