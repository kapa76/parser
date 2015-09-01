package org.parser.persistence.repository.hibernate;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.parser.persistence.model.SocialLinksResume;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SocialLinksResumeRepository extends AbstractRepo {

    public SocialLinksResume findOne(Long id) {
        return (SocialLinksResume) sessionFactory.getCurrentSession().get(SocialLinksResume.class, id);
    }


    public SocialLinksResume findOne(String name) {
        return (SocialLinksResume) sessionFactory.getCurrentSession().createQuery("from social_links_resume p where p.name = :name").setParameter("name", name).uniqueResult();
    }


    public List<SocialLinksResume> findAll() {
        return null;
    }


    public void create(SocialLinksResume entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
    }


    public SocialLinksResume update(SocialLinksResume entity) {
        return null;
    }


    public void delete(SocialLinksResume entity) {

    }


    public void deleteById(long entityId) {

    }

    public SocialLinksResume findOne(String name, long id) {
        return (SocialLinksResume) sessionFactory.getCurrentSession().createQuery("from social_links_resume p where p.name = :name and p.site.id = :id").setParameter("name", name).setParameter("id", id).
                uniqueResult();
    }
}