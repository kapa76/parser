package org.parser.persistence.kernel.factory;

import org.parser.persistence.kernel.ServiceHH;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Repository
@Transactional
public class ServiceHHImpl implements ServiceHH {

    private static final Logger logger =
            LoggerFactory.getLogger(ServiceHHImpl.class);

    @Override
    public void init() {

    }

    @Override
    public void startVacancy() {

    }

    @Override
    public void startResume() {

    }
}
