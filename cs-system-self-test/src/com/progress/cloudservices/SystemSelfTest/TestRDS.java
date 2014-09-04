package com.progress.cloudservices.SystemSelfTest;

import java.sql.*;
import java.util.HashMap;
import java.util.Properties;
import org.apache.log4j.Logger;

class TestRDS implements TestCaseInterface
{
	private static Logger logger = Logger.getLogger(TestRDS.class.getName());	
	@Override
	public void runTest(Properties props) 
	{
		LogFormat logFormat=new LogFormat();
		JacksonPojoMapper pojoMapper = new JacksonPojoMapper();
		boolean prettyPrint=true;
		try
		{						
			Connection connection = null;

			// Set fixed fields for logs..
			logFormat.setProduct(props.getProperty("product-name").toString());
			logFormat.setName("RDS connection test");

			// Read values from server.xml	
			SystemSelfTestUtil.SetDbVariables(props);

			String dbUrl=props.getProperty("databaseUrl");
			String jdbcDriver=props.getProperty("jdbcDriver");
			String username=props.getProperty("username");
			String password=props.getProperty("password");

			connection = DriverManager.getConnection(dbUrl,username,password);
			logFormat.setComments("Checking connection to RDS, using JDBC Driver: "+jdbcDriver+" and DBUrl: "+dbUrl+"UserName"+username);

			//Register JDBC driver
			Class.forName(jdbcDriver);

			// Open a connection
			connection = DriverManager.getConnection(dbUrl,username,password);

			connection.close();
			logFormat.setStatus("success");
			
			logger.info(pojoMapper.toJson(logFormat, prettyPrint));
		}
		catch(Throwable e)
		{
			if(logFormat.getComments() == null)
				logFormat.setComments("Failure reading server.xml.");
			logFormat.setStatus("failure");
			{
				HashMap<String,String> infoMap = new HashMap<String, String>();
				infoMap.put("info","Exception in RDS connection test.");
				infoMap.put("Exception",e.toString());

				logFormat.setDetailedInfo(infoMap);
			}
			logger.info(pojoMapper.toJson(logFormat, prettyPrint));
		}
	}
}
