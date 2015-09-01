package org.parser.persistence.repository.hibernate;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.parser.persistence.model.PublishedResume;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PublishedResumeRepository extends AbstractRepo {

    public PublishedResume findOne(Long id) {
        return (PublishedResume) sessionFactory.getCurrentSession().get(PublishedResume.class, id);
    }


    public PublishedResume findOne(String name) {
        return null;
    }


    public List<PublishedResume> findAll() {
        return null;
    }


    public void create(PublishedResume entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
    }


    public PublishedResume update(PublishedResume entity) {
        return null;
    }

    public void delete(PublishedResume entity) {

    }


    public void deleteById(long entityId) {

    }

    public PublishedResume findOne(String name, long id) {
        return (PublishedResume) sessionFactory.getCurrentSession().createQuery("from published_resume p where p.name = :name and p.site.id = :id ").setParameter("id", id).setParameter("name", name).uniqueResult();

    }
}