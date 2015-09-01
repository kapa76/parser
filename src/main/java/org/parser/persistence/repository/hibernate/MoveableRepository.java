package org.parser.persistence.repository.hibernate;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.parser.persistence.model.Moveable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MoveableRepository extends AbstractRepo {

    public Moveable findOne(Long id) {
        return (Moveable) sessionFactory.getCurrentSession().get(Moveable.class, id);
    }


    public Moveable findOne(String name) {
        return null;
    }


    public List<Moveable> findAll() {
        return null;
    }


    public void create(Moveable entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
    }


    public Moveable update(Moveable entity) {
        return null;
    }


    public void delete(Moveable entity) {

    }


    public void deleteById(long entityId) {

    }

    public Moveable findOne(String name, long id) {
        return (Moveable) sessionFactory.getCurrentSession().createQuery("from moveable p where p.name = :name and p.site.id = :id ").setParameter("id", id).setParameter("name", name).uniqueResult();

    }
}