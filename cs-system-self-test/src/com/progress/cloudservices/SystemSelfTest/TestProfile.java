package com.progress.cloudservices.SystemSelfTest;

import java.util.HashMap;
import java.util.Properties;
import org.apache.log4j.Logger;

class TestProfile implements TestCaseInterface
{
	private static Logger logger = Logger.getLogger(TestQAD.class.getName());
	@Override
	public void runTest(Properties props) 
	{
		boolean prettyPrint=true;
		LogFormat logFormat=new LogFormat();
		JacksonPojoMapper pojoMapper = new JacksonPojoMapper();

		try
		{		
			String UserId=props.getProperty("profile-userid");		

			String requestURL = CommonUtility.getCloudServicesProperty("profile.webservice.url",null)+UserId;

			String username=CommonUtility.getCloudServicesProperty("profile.webservice.auth.username",null);
			String password=CommonUtility.getCloudServicesProperty("profile.webservice.auth.password",null);

			HashMap<String,String> parameters = new HashMap<String, String>();
			//		HashMap<String,String> header = new HashMap<String,String>();

			boolean authorize=true;
			String output=null;
			int timeout= Integer.parseInt(props.getProperty("timeout"));

			// Set fixed fields for logs..

			logFormat.setProduct(props.getProperty("product-name").toString());
			logFormat.setName("Profile connection test");
			logFormat.setComments("Checking connection to Profile server using url:"+requestURL+"UserName:"+username);

			output=CommonUtility.postRestCallWithParams(requestURL,parameters,authorize,username,password,"String",timeout);

			if(output.contains("Success"))
			{
				logFormat.setStatus("success");				
			}
			else
			{
				logFormat.setStatus("failure");
				
				HashMap<String,String> infoMap = new HashMap<String, String>();
				infoMap.put("info","Error in Progress-profile connection test.");
				infoMap.put("Response_Phrase",output);
				
				logFormat.setDetailedInfo(infoMap);
			}
			logger.info(pojoMapper.toJson(logFormat, prettyPrint));
		}	
		catch (Exception e)
		{
			if(logFormat.getStatus() == null)
				logFormat.setStatus("failure");
			if(logFormat.getDetailedInfo() == null)
			{
				HashMap<String,String> infoMap = new HashMap<String, String>();
				infoMap.put("info","Exception in Progress-Profile connection test.");
				infoMap.put("Exception",e.toString());
				
				logFormat.setDetailedInfo(infoMap);
			}

			logger.info(pojoMapper.toJson(logFormat, true));
		}
	}
}