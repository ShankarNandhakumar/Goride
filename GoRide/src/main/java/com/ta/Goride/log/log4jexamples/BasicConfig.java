package com.ta.Goride.log.log4jexamples;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BasicConfig {

	static Logger logger= LogManager.getLogger(BasicConfig.class);
	
	public static void main(String[] args)
	{
		
		logger.debug("this is debug message");
		logger.info("this is information");
		logger.warn("this is warning");
		logger.error("this is error");
		logger.fatal("This is fatal merror");
		
	}

}
