package com.progress.cloudservices.SystemSelfTest;

import java.util.HashMap;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

class TestAria implements TestCaseInterface
{
	private static Logger logger = Logger.getLogger(TestAria.class.getName());	
	
	@Override
	public void runTest(Properties props) 
	{
		LogFormat logFormat=new LogFormat();
		boolean prettyPrint=true;
		JacksonPojoMapper pojoMapper = new JacksonPojoMapper();
		try
		{
			String requestURL = CommonUtility.getCloudServicesProperty("ws_url",null);

			HashMap<String,String> parameters = new HashMap<String, String>();
			HashMap<String,String> header = new HashMap<String,String>();
			parameters.put("auth_key",CommonUtility.getCloudServicesProperty("auth_key",""));
			parameters.put("client_no", CommonUtility.getCloudServicesProperty("client_no",""));
			parameters.put("output_format", "json");
			parameters.put("rest_call", "get_current_system_version");

			// Set fixed fields for logs..
			
			int timeout= Integer.parseInt(props.getProperty("timeout"));

			logFormat.setProduct(props.getProperty("product-name").toString());
			logFormat.setName("Aria connection test");
			logFormat.setComments("Checking connection to Aria server using url:"+requestURL);

			CSHttpResponse csResponse = HttpInvoker.requestRESTPostResponse(requestURL, parameters, header, timeout);

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
				infoMap.put("info","Error in Aria connection test.");
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

			logger.info(pojoMapper.toJson(logFormat, prettyPrint));

		}
		catch (Throwable e)
		{
			if(logFormat.getStatus() == null)
				logFormat.setStatus("failure");
			if(logFormat.getDetailedInfo() == null)
			{
				HashMap<String,String> infoMap = new HashMap<String, String>();
				infoMap.put("info","Exception in Aria connection test.");
				infoMap.put("Exception",e.toString());
				
				logFormat.setDetailedInfo(infoMap);
			}
			
			logger.info(pojoMapper.toJson(logFormat, prettyPrint));			
		}
	}
}
