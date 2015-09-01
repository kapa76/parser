package org.parser.persistence.repository.hibernate;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.parser.persistence.model.TypeOfWork;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TypeOfWorkRepository extends AbstractRepo {

    public TypeOfWork findOne(Long id) {
        return (TypeOfWork) sessionFactory.getCurrentSession().get(TypeOfWork.class, id);
    }


    public TypeOfWork findOne(String name) {
        return null;
    }


    public List<TypeOfWork> findAll() {
        return null;
    }


    public void create(TypeOfWork entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
    }


    public TypeOfWork update(TypeOfWork entity) {
        return null;
    }


    public void delete(TypeOfWork entity) {

    }


    public void deleteById(long entityId) {

    }

    public TypeOfWork findOne(String name, long id) {
        return (TypeOfWork) sessionFactory.getCurrentSession().createQuery("from type_of_work p where p.name = :name and p.site.id = :id ").setParameter("id", id).setParameter("name", name).uniqueResult();

    }
}