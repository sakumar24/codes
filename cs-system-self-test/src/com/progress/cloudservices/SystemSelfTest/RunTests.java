package com.progress.cloudservices.SystemSelfTest;

import java.util.Properties;
import java.util.UUID;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

class RunTests implements Runnable
{
	final Logger logger = Logger.getLogger(RunTests.class);
	
/*	public RunTests(Hashtable context)
	{
		for(Object key : context.keySet())
		{
			MDC.put(key.toString(), context.get(key).toString());
		}
	}
*/

	@Override
	public void run()
	{		
		try
		{
			UUID t = java.util.UUID.randomUUID();
			String iteration_Id = t.toString();
			
			MDC.put("iteration_Id", iteration_Id);
			
			logger.info("Executing Tests");

			// Read the class name of the Test Cases and call functions to run test cases..
			
			Properties props = CommonUtility.getAllSystemSelfTestProperties();

			String classNames = props.getProperty("test-case-classes");
			String className[] = classNames.split(";");

			for(int i=0;i<className.length;i++ )
			{
				// load the class using reflection and call the runTest function
				Class<?> testClass = Class.forName(className[i]);

				Method method = testClass.getMethod("runTest",Properties.class); 		
				method.invoke(testClass.newInstance(),props);
			}
		} 
		catch (Throwable e)		
		{
			logger.info("Exception while running Tests :",e);
		}
		finally
		{
			// clear iteration_Id value..
			logger.info("End of Tests.");
			MDC.remove("iteration_Id");
		}
	}
}
