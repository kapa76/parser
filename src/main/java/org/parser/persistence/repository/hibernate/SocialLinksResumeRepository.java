package org.parser.persistence.repository.hibernate;


import org.parser.persistence.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SocialLinksResumeRepository extends AbstractRepo {

    public SocialLinksResume findOne(Long id) {
        return (SocialLinksResume) sessionFactory.openSession().get(SocialLinksResume.class, id);
    }


    public SocialLinksResume findOne(String name) {
        return null;
    }


    public List<SocialLinksResume> findAll() {
        return null;
    }


    public void create(SocialLinksResume entity) {
        sessionFactory.openSession().save(entity);
    }


    public SocialLinksResume update(SocialLinksResume entity) {
        return null;
    }


    public void delete(SocialLinksResume entity) {

    }


    public void deleteById(long entityId) {

    }
}