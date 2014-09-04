package com.progress.cloudservices.SystemSelfTestListener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleListener;
import org.apache.log4j.Logger;

public class StartupListener implements LifecycleListener
{
	private static Logger logger = Logger.getLogger(StartupListener.class.getName());
	public static Map<String,String> propertiesMap = null;

	@Override
	public void lifecycleEvent(LifecycleEvent event)
	{
		try
		{
			if (Lifecycle.AFTER_START_EVENT.equals(event.getType()))
			{ 
				// read system-self-test.properties into propertiesMap
				getAllProperties();

				String run= propertiesMap.get("enable-test");
				if(run.equals("true"))
				{
					logger.info("---Running System-self test---");

					String runApplication = propertiesMap.get("run-application");
					String catalinaHome = propertiesMap.get("catalina-home");

					runProcess(runApplication+" "+catalinaHome);	
				}
				else
				{
					logger.info("-- System-self test is disabled for this enviornment.--");
				}
			}
		}
		catch (Throwable e)
		{
			logger.info("Exception in system-self-test Listener:",e);
		}

	}
	private static void printLines(String name, InputStream ins) throws Exception
	{
		String line = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(ins));
		while ((line = in.readLine()) != null) 
		{
			System.out.println(name + " " + line);
		}
	}

	private static void runProcess(String command) 
	{
		try
		{
			Process pro = Runtime.getRuntime().exec(command);
			logger.info("Process : "+pro.toString());
		
			// print error/sysouts (if any) on console.
			printLines(command + " stdout:", pro.getInputStream());
			printLines(command + " stderr:", pro.getErrorStream());
		} 
		catch (Throwable e) 
		{
			logger.info("Exception in running the system-self-test peocess: ",e);
		}
	}

	public static void getAllProperties() {
			try {
				InputStream is = StartupListener.class
						.getResourceAsStream("/system-self-test.properties");
				if (is != null) {
					propertiesMap = new HashMap<String, String>();
					Properties props = new Properties();
					props.load(is);
					for (String propName : props.stringPropertyNames()) {
						propertiesMap
						.put(propName, props.getProperty(propName));
					}
				}
				else
				{
					logger.info("Can't read the system-self-test.properties file.");
				}
			} catch (Exception e) {
				logger.info("Exception Reading system-self-test.properties file:",e);
			}
	}	
}