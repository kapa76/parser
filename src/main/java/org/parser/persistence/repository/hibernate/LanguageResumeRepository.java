package org.parser.persistence.repository.hibernate;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.parser.persistence.model.LanguageResume;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LanguageResumeRepository extends AbstractRepo {

    public LanguageResume findOne(Long id) {
        return (LanguageResume) sessionFactory.getCurrentSession().get(LanguageResume.class, id);
    }


    public LanguageResume findOne(String name) {
        return null;
    }


    public List<LanguageResume> findAll() {
        return null;
    }


    public void create(LanguageResume entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
    }


    public LanguageResume update(LanguageResume entity) {
        return null;
    }


    public void delete(LanguageResume entity) {

    }


    public void deleteById(long entityId) {

    }

    public LanguageResume findOne(String name, long id) {
        return (LanguageResume) sessionFactory.getCurrentSession().createQuery("from language_resume p where p.name = :name and p.site.id = :id ").setParameter("id", id).setParameter("name", name).uniqueResult();

    }
}