package org.parser.persistence.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.parser.persistence.model.Gender;
import org.parser.persistence.model.SearchWords;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SearchWordRepository extends AbstractRepo {

    public List<SearchWords> findAll(int type) {
        return null; //List<SearchWords> sessionFactory.getCurrentSession().createQuery("from search_words p where p.system = :id ").setParameter("id", type).list();
    }

    public void create(SearchWords entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
    }

    public SearchWords update(SearchWords entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
        return entity;
    }

    public void delete(SearchWords entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(entity);
        tx.commit();
        session.close();
    }

    public SearchWords findOne(String word_name, int id) {
        return (SearchWords) sessionFactory.getCurrentSession().createQuery("from search_words p where p.word_name = :word_name and p.system = :id ").setParameter("id", id).setParameter("word_name", word_name).uniqueResult();

    }
}