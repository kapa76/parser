package org.parser.persistence.repository.hibernate;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.parser.persistence.model.Language;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LanguageRepository extends AbstractRepo {

    public Language findOne(Long id) {
        return (Language) sessionFactory.getCurrentSession().get(Language.class, id);
    }


    public Language findOne(String name) {
        return null;
    }


    public List<Language> findAll() {
        return null;
    }


    public void create(Language entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
    }


    public Language update(Language entity) {
        return null;
    }


    public void delete(Language entity) {

    }


    public void deleteById(long entityId) {

    }

    public Language findOne(String name, long id) {
        return (Language) sessionFactory.getCurrentSession().createQuery("from language p where p.name = :name and p.site.id = :id ").setParameter("id", id).setParameter("name", name).uniqueResult();

    }
}