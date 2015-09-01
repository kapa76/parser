package org.parser.persistence.repository.hibernate;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.parser.persistence.model.MaritalStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MaritalStatusRepository extends AbstractRepo {

    public MaritalStatus findOne(Long id) {
        return (MaritalStatus) sessionFactory.getCurrentSession().get(MaritalStatus.class, id);
    }


    public MaritalStatus findOne(String name) {
        return null;
    }


    public List<MaritalStatus> findAll() {
        return null;
    }


    public void create(MaritalStatus entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
    }


    public MaritalStatus update(MaritalStatus entity) {
        return null;
    }


    public void delete(MaritalStatus entity) {

    }


    public void deleteById(long entityId) {

    }

    public MaritalStatus findOne(String name, long id) {
        return (MaritalStatus) sessionFactory.getCurrentSession().createQuery("from marital_status p where p.name = :name and p.site.id = :id ").setParameter("id", id).setParameter("name", name).uniqueResult();

    }
}