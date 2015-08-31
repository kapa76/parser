package org.parser.persistence.kernel.factory;

import org.parser.persistence.kernel.ServiceMK;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Repository
@Transactional
public class ServiceMKImpl implements ServiceMK {
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
