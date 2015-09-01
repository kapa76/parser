package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.Children;
import org.hibernate.Session; import org.hibernate.Transaction; import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChildrenRepository extends AbstractRepo {

    public Children findOne(Long id) {
        return (Children) sessionFactory.getCurrentSession().get(Children.class, id);
    }


    public Children findOne(String name) {
        return (Children)sessionFactory.getCurrentSession().createQuery("from children p where p.name = :name").setParameter("name", name).uniqueResult();
    }


    public List<Children> findAll() {
        return null;
    }


    public void create(Children entity) {
        Session session = sessionFactory.openSession();         Transaction tx = session.beginTransaction();         session.save(entity);         tx.commit();         session.close();
    }


    public Children update(Children entity) {
        return null;
    }


    public void delete(Children entity) {

    }


    public void deleteById(long entityId) {

    }
}