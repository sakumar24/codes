package com.progress.cloudservices.SystemSelfTest;

import java.util.HashMap;
import java.util.Properties;
import org.apache.log4j.Logger;

class TestEloqua implements TestCaseInterface
{
	private static Logger logger = Logger.getLogger(TestEloqua.class.getName());
	@Override
	public void runTest(Properties props) 
	{
		LogFormat logFormat=new LogFormat();	
		boolean prettyPrint=true;
		JacksonPojoMapper pojoMapper = new JacksonPojoMapper();
		try
		{		
			String requestURL = CommonUtility.getCloudServicesProperty("eloqua.webservice.url",null)+"?uid=rreddy&atc=NULL"; // Hard coded value for 'uid'.
			String username=CommonUtility.getCloudServicesProperty("eloqua.webservice.username",null);
			String password=CommonUtility.getCloudServicesProperty("eloqua.webservice.password",null);

			HashMap<String,String> parameters = new HashMap<String, String>();
			//	HashMap<String,String> header = new HashMap<String,String>();

			boolean authorize=true;
			String output=null;
			
			int timeout= Integer.parseInt(props.getProperty("timeout"));

			// Set fixed fields for logs..
			logFormat.setProduct(props.getProperty("product-name").toString());
			logFormat.setName("Eloqua connection test");
			logFormat.setComments("Checking connection to Eloqua server using url:"+requestURL);		

			output=CommonUtility.postRestCallWithParams(requestURL,parameters,authorize,username,password,"String",timeout);

			if(output.compareTo("Success") > 1 && output!=null)
			{
				logFormat.setStatus("success");
			}
			else
			{
				logFormat.setStatus("failure");	
				
				HashMap<String,String> infoMap = new HashMap<String, String>();
				infoMap.put("info","Error in Eloqua connection test.");
				infoMap.put("Response_Phrase",output);
				logFormat.setDetailedInfo(infoMap);		
			}

			logger.info(pojoMapper.toJson(logFormat, prettyPrint));
		}
		catch (Throwable e)
		{
			if(logFormat.getStatus() == null)
				logFormat.setStatus("failure");
			if(logFormat.getDetailedInfo() == null)
			{
				HashMap<String,String> infoMap = new HashMap<String, String>();
				infoMap.put("info","Exception in Eloqua connection test.");
				infoMap.put("Exception",e.toString());
				
				logFormat.setDetailedInfo(infoMap);
				
			}
			logger.info(pojoMapper.toJson(logFormat, prettyPrint));
		}
	}
}