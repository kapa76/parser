package org.parser.service;

import org.parser.persistence.kernel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;


@Controller
@RequestMapping("/srv")
public class ControllerService {

    private static final Logger logger =
            LoggerFactory.getLogger(ControllerService.class);

    @Autowired
    private ServiceSJ serviceSJ;

    @Autowired
    private ServiceHH serviceHH;

    @Autowired
    private ServiceLI serviceLI;

    @Autowired
    private ServiceMK serviceMK;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public String list() {
        logger.debug("exec: list");
        return "vopa";
    }


    @RequestMapping(value = "/startVacancy", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void startVacancy(@RequestParam(value = "system_name") String name) throws IOException {
        logger.debug("start vacancy loading: " + name);

        switch (name) {
            case "hh":
                serviceHH.startVacancy();
                break;
            case "JS":
                serviceSJ.startVacancy();
                break;
            case "moykrug":
                serviceMK.startVacancy();
                break;
            case "linkedIn":
                serviceLI.startVacancy();
                break;
        }

    }

    @RequestMapping(value = "/startResume", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void startResume(@RequestParam(value = "system_name") String name) throws IOException {
        logger.debug("start vacancy loading: " + name);

        switch (name) {
            case "hh":
                serviceHH.startResume();
                break;
            case "JS":
                serviceSJ.startResume();
                break;
            case "moykrug":
                serviceMK.startResume();
                break;
            case "linkedIn":
                serviceLI.startResume();
                break;
        }
    }
}
