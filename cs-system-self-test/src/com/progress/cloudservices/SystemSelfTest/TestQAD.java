package com.progress.cloudservices.SystemSelfTest;

import java.util.HashMap;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

class TestQAD implements TestCaseInterface
{
	static boolean prettyPrint=true;
	static PCNAddress pojo= new PCNAddress();
	static String requestBody;
	private static Logger logger = Logger.getLogger(TestQAD.class.getName());

	@Override
	public void runTest(Properties props) 
	{
		LogFormat logFormat=new LogFormat();
		JacksonPojoMapper pojoMapper = new JacksonPojoMapper();
		try
		{
			requestBody=pojoMapper.toJson(pojo, prettyPrint);
			
			String requestURL = CommonUtility.getCloudServicesProperty("pcn.generation.url",null);

			//	HashMap<String,String> parameters = new HashMap<String, String>();
			//	HashMap<String,String> header = new HashMap<String,String>();

			String username=CommonUtility.getCloudServicesProperty("pcn.basicauth.user",null);
			String password=CommonUtility.getCloudServicesProperty("pcn.basicauth.password",null);

			boolean authorize=true;
			String contentType="String";
			int timeout= Integer.parseInt(props.getProperty("timeout"));

			// Set fixed fields for logs..
			logFormat.setProduct(props.getProperty("product-name").toString());
			logFormat.setName("QAD connection test");
			logFormat.setComments("Checking connection to QAD server using url:"+requestURL+"UserName:"+username);

			CSHttpResponse csResponse = CommonUtility.postRestCall(requestURL,requestBody,authorize,username, password, contentType, timeout);

			String responseReasonPhrase = "";
			int statusCode = csResponse.getStatusCode();

			HttpResponse response = csResponse.getHttpResponse();
			if(response != null) 
			{
				responseReasonPhrase = response.getStatusLine().getReasonPhrase();
			}

			if(statusCode != HttpStatus.SC_OK)
			{
				logFormat.setStatus("failure");
				HashMap<String,String> infoMap = new HashMap<String, String>();
				infoMap.put("info","Error in QAD connection test.");
				infoMap.put("Response_code", Integer.toString(statusCode));
				infoMap.put("Response_Phrase",responseReasonPhrase);
				
				logFormat.setDetailedInfo(infoMap);	
			}
			else
			{
				logFormat.setStatus("success");
				
				HashMap<String,String> infoMap = new HashMap<String, String>();
				infoMap.put("Response_code", Integer.toString(statusCode));
				infoMap.put("Response_Phrase",responseReasonPhrase);
	
				logFormat.setDetailedInfo(infoMap);	
			}
			boolean prettyPrint=true;

			logger.info(pojoMapper.toJson(logFormat, prettyPrint));
		}
		catch (Exception e)
		{
			if(logFormat.getStatus() == null)
				logFormat.setStatus("failure");
			if(logFormat.getDetailedInfo() == null)
			{
				HashMap<String,String> infoMap = new HashMap<String, String>();
				infoMap.put("info","Exception in QAD connection test.");
				infoMap.put("Exception",e.toString());
				
				logFormat.setDetailedInfo(infoMap);
			}

			logger.info(pojoMapper.toJson(logFormat, prettyPrint));
		}
	}
}