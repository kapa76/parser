package org.parser.persistence.repository.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractRepo {

    @Autowired
    public SessionFactory sessionFactory;


}
