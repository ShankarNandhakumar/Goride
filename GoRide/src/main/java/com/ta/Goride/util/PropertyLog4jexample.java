package com.ta.Goride.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropertyLog4jexample {

    // Initialize the logger using LogManager
    static Logger logger = LogManager.getLogger(PropertyLog4jexample.class);

    public static void main(String[] args) {
    	String log4jConfigFile = "src/main/resources/log4j2.xml";
    	System.setProperty("log4j.configurationFile", log4jConfigFile);
        // Log messages at different levels
        logger.debug("This is a debug message");
        logger.info("This is an information");
        logger.warn("This is a warning");
        logger.error("This is an error");
        logger.fatal("This is a fatal error");
     
    }
}

