package com.progress.cloudservices.SystemSelfTest;

import java.net.Inet4Address;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

public class ApplicationStartup
{
	private static final Logger logger = Logger.getLogger(ApplicationStartup.class);
	public static String catalinaHome=null;

	public static void main(String args[])
	{
		try {
			
			// set catalina home..
			catalinaHome = args[0];
			
			logger.debug("CATALINA HOME: "+catalinaHome);

			int NoOfThreads = 1;
			Properties props = CommonUtility.getAllSystemSelfTestProperties();
			
			int repetitionPeriod = Integer.parseInt(props.getProperty("repetition-period"));			
			
			String sourceIP = Inet4Address.getLocalHost().getHostAddress();
			String envName = props.getProperty("deployment.environment");
			
			if(envName==null)
			{
				envName = CommonUtility.getPropertyFromFallBackFile(props.getProperty("fall-back-file-path"),"cs.deployment.environment");
			}
			
			MDC.put("source_ip", sourceIP);
			MDC.put("enviornment_name", envName);
			MDC.put("Unique_Key","System self-test log");
			
			ScheduledExecutorService scheduledExecutorService = Executors
					.newScheduledThreadPool(NoOfThreads);

			// Call functions to run test cases..
			scheduledExecutorService.scheduleAtFixedRate(new RunTests(), 1,
					repetitionPeriod, TimeUnit.SECONDS);
		} catch (Throwable e) 
		{
			logger.info("Exception in system-self-test application startup: ",e);
		}
	}
}
