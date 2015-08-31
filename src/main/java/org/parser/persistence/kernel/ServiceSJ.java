package org.parser.persistence.kernel;


import java.io.IOException;

public interface ServiceSJ {
    void init();
    void startVacancy() throws IOException;
    void startResume();
}
