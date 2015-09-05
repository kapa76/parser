package org.parser.persistence.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.parser.persistence.model.Gender;
import org.parser.persistence.model.WordList;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WordListRepository  extends AbstractRepo {

    public List<WordList> findAll(int id) {
        return null; //List<SearchWords> sessionFactory.getCurrentSession().createQuery("from search_words p where p.system = :id ").setParameter("id", type).list();
    }

    public void create(WordList entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
    }

    public WordList update(WordList entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
        return entity;
    }

    public void delete(WordList entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(entity);
        tx.commit();
        session.close();
    }


}