package org.parser.persistence.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.parser.persistence.model.History;
import org.parser.persistence.model.LangLevel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HistoryRepository extends AbstractRepo {

    public History findOne(Long id) {
        return (History) sessionFactory.getCurrentSession().get(History.class, id);
    }

    public History findOne(String name) {
        return null;
    }

    public List<History> findAll() {
        return null;
    }

    public void create(History entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
    }

    public History findOne(String name, long id) {
        return null; //(History) sessionFactory.getCurrentSession().createQuery("from histoy p where p.name = :name and p.site.id = :id ").setParameter("id", id).setParameter("name", name).uniqueResult();

    }
}