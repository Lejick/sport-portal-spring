package org.portal.back.util;

import org.apache.log4j.Logger;

public class DualLogger {
    Logger LOGGER;

    public DualLogger(Class className) {
        LOGGER = Logger.getLogger(className);
    }

    public void info(Throwable ex) {
        LOGGER.info(ex.getMessage(), ex);
        ex.printStackTrace();
    }

    public void error(Throwable ex) {
        LOGGER.error(ex.getMessage(), ex);
        ex.printStackTrace();
    }

    public void info(String message) {
        LOGGER.info(message);
        System.out.println(message);
    }
}
