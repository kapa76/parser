package org.parser.persistence.repository.hibernate;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.parser.persistence.model.Currency;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CurrencyRepository extends AbstractRepo {

    public Currency findOne(Long id) {
        return (Currency) sessionFactory.getCurrentSession().get(Currency.class, id);
    }


    public Currency findOne(String name) {
        return (Currency) sessionFactory.getCurrentSession().createQuery("from currency p where p.name = :name").setParameter("name", name).uniqueResult();
    }


    public List<Currency> findAll() {
        return null;
    }


    public void create(Currency entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
    }


    public Currency update(Currency entity) {
        return null;
    }


    public void delete(Currency entity) {

    }

    public void deleteById(long entityId) {

    }

    public Currency findOne(String name, long id) {
        return (Currency) sessionFactory.getCurrentSession().createQuery("from currency p where p.name = :name and p.site.id = :id ").setParameter("id", id).setParameter("name", name).uniqueResult();

    }
}