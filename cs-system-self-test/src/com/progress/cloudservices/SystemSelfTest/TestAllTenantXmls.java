package com.progress.cloudservices.SystemSelfTest;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.progress.cloudservices.CommonTenantConfig.TenantConfig;

public class TestAllTenantXmls extends AbstractSystemSelfTest
{
	public static final Logger logger = Logger.getLogger(RunTests.class);
	JacksonPojoMapper pojoMapper = new JacksonPojoMapper();
	LogFormat logFormat=new LogFormat();

	@Override
	public void runTest(Properties props)
	{
		String currentTenantName=null;		
		try
		{
			logFormat.setProduct(props.getProperty("product-name").toString());
			String env=props.getProperty("deployment.environment"); 

			String propertiesFileAddress =TenantXmlConstants.tenantXmlPropertiesFileAddress;

			String XmlNamekey=env+"."+TenantXmlConstants.xmlNames;
			String allTenantNames = SystemSelfTestUtil.getProperty(propertiesFileAddress, XmlNamekey, "");

			String[] tenantNames = null;

			if(allTenantNames!=null)
				tenantNames = allTenantNames.split(",");
			else
				logger.debug("Failed to read all Tenant names from tenant xml file.");

			List<String> statusCode500Urls = null;
			List<String> statusCode400Urls = null;			

			// read these urls from properties file..
			String tempStatusCode500Urls = SystemSelfTestUtil.getProperty(propertiesFileAddress, "statusCode500Urls", "");
			String tempStatusCode400Urls = SystemSelfTestUtil.getProperty(propertiesFileAddress, "statusCode400Urls", "");

			if(tempStatusCode400Urls!=null)
				statusCode400Urls = Arrays.asList(tempStatusCode400Urls.split(","));
			else
				logger.debug("Failed to read all statusCode400Urls from tenant xml file.");

			if(tempStatusCode500Urls!=null)
				statusCode500Urls = Arrays.asList(tempStatusCode500Urls.split(","));
			else
				logger.debug("Failed to read all statusCode500Urls from tenant xml file.");

			for(int i = 0; i < tenantNames.length ; i++)
			{
				currentTenantName=tenantNames[i];

				try {					
					//reads from db and returns TenantConfig
					InputStream tenantConfigInputStream = SystemSelfTestUtil.getTenantConfig(props,currentTenantName);										
					TenantConfig tenantConfig=SystemSelfTestUtil.readTenantConfigFromXMLFile(tenantConfigInputStream);

					String allUrlString = SystemSelfTestUtil.getProperty(propertiesFileAddress,"listOfUrlsToTest", "");	

					if(allUrlString.isEmpty())
						logger.debug("Can't read list of all urls.");

					List<String> listOfUrlsToTest = Arrays.asList(allUrlString.split(","));

					HashMap<String,String> allUrlsMap=SystemSelfTestUtil.extractUrls(listOfUrlsToTest,tenantConfig);

					ArrayList<String> missingUrlsList  = new ArrayList<String>();

					String requiredUrlsInEachTenant=SystemSelfTestUtil.getProperty(propertiesFileAddress,"requiredUrlsInEachTenant","");
					if(requiredUrlsInEachTenant!=null)
					{
						String listForEachTenant=requiredUrlsInEachTenant.split(";")[i];

						for(String currentUrl:listForEachTenant.split(","))
						{
							if(allUrlsMap.containsKey(currentUrl)==false)
							{
								missingUrlsList.add(currentUrl);
							}

						}
						logFormat.setName("MissingUrls"+"_"+currentTenantName);
						logFormat.setComments("Test for all required urls in "+currentTenantName);

						if(! missingUrlsList.isEmpty())
						{
							logFormat.setStatus("warning");
							HashMap<String, String> infoMap = new HashMap<String, String>();
							infoMap.put("info",  "All required urls are not there in "+currentTenantName);
							infoMap.put("Missing urls",missingUrlsList.toString());

							logFormat.setDetailedInfo(infoMap);
						}
						else
						{
							logFormat.setStatus("success");
							logFormat.setDetailedInfo(null);
						}
						logger.info(pojoMapper.toJson(logFormat, true));
					}

					if(allUrlsMap!=null)
					{
						for( Entry<String, String> entry: allUrlsMap.entrySet())
						{
							String key = entry.getKey();
							String value = entry.getValue();


							// set name of test as urlName_tenantxmlName test	
							logFormat.setName( key+"_"+currentTenantName+" test.");

							if(value!=null) // Null check
							{
								String regex = SystemSelfTestUtil.getProperty(propertiesFileAddress,env+"."+currentTenantName+"."+key+"."+TenantXmlConstants.regex,"");
								if(SystemSelfTestUtil.getProperty(propertiesFileAddress,env+"."+currentTenantName+"."+key+"."+TenantXmlConstants.regex,"")!="")
								{
									boolean valid;

									try
									{
										valid=isValidTenantProperty(key, value, env, currentTenantName, propertiesFileAddress);

										for( String actualValue: value.split(","))
										{
											actualValue = actualValue.trim();
											logFormat.setComments("Url-key: "+key+" value: "+actualValue);
											CSHttpResponse cshttpResponse=invokeUrl(key, actualValue, env, currentTenantName, propertiesFileAddress, tenantConfig);

											int statusCode = cshttpResponse.getStatusCode();
											String responseReasonPhrase = cshttpResponse.getHttpResponse().getStatusLine().getReasonPhrase();

											if(statusCode==200 || (statusCode400Urls.contains(key) && statusCode==400) || (statusCode500Urls.contains(key) && statusCode==500))
											{
												if(!valid) // Invalid url but passed invocation 
												{
													logFormat.setStatus("warning");

													HashMap<String,String> infoMap = new HashMap<String, String>();
													infoMap.put("info","Url responded coorectly while Invoking but failed validation");
													infoMap.put("Url key",key);
													infoMap.put("Expected format",regex);
													infoMap.put("Read value",actualValue);
													infoMap.put("Response_code", Integer.toString(statusCode));
													infoMap.put("Response_Phrase",responseReasonPhrase);

													logFormat.setDetailedInfo(infoMap);
												}
												else	// valid url and passed invocation
												{
													logFormat.setStatus("success");

													HashMap<String,String> infoMap = new HashMap<String, String>();
													infoMap.put("Url key",key);
													infoMap.put("Response_code", Integer.toString(statusCode));
													infoMap.put("Response_Phrase",responseReasonPhrase);

													logFormat.setDetailedInfo(infoMap);
												}
											}
											else
											{
												if(!valid) // Invalid url and failure in Invocation
												{
													logFormat.setStatus("failure");

													HashMap<String,String> infoMap = new HashMap<String, String>();
													infoMap.put("info","Url Failed Validation as well as Invocation");
													infoMap.put("Url key",key);
													infoMap.put("Expected format",regex);
													infoMap.put("Read value",actualValue);
													infoMap.put("Response_code", Integer.toString(statusCode));
													infoMap.put("Response_Phrase",responseReasonPhrase);

													logFormat.setDetailedInfo(infoMap);
												}
												else	// valid url but failed invocation..
												{
													logFormat.setStatus("failure");
													HashMap<String,String> infoMap = new HashMap<String, String>();
													infoMap.put("info","Url Failed Invocation");
													infoMap.put("Url key",key);
													infoMap.put("Response_code", Integer.toString(statusCode));
													infoMap.put("Response_Phrase",responseReasonPhrase);

													logFormat.setDetailedInfo(infoMap);
												}
											}	
											// Log result for test of each url .
											logger.info(pojoMapper.toJson(logFormat, true));
										} 
									}
									catch (Exception e) 
									{
										logFormat.setStatus("failure");

										HashMap<String,String> infoMap = new HashMap<String, String>();
										infoMap.put("info","Exception in Tenant xml test.");
										infoMap.put("Exception",e.toString());
										logFormat.setDetailedInfo(infoMap);

										logger.info(pojoMapper.toJson(logFormat, true));
									}
								}
							}
						}
					}
				} catch (Exception e)
				{
					logFormat.setName("Tenant-xml_"+currentTenantName+" test");
					logFormat.setComments("Tenant name: "+currentTenantName);
					logFormat.setStatus("failure");

					HashMap<String,String> infoMap = new HashMap<String, String>();
					infoMap.put("info","Exception in Tenant xml test.");
					infoMap.put("Exception",e.toString());
					logFormat.setDetailedInfo(infoMap);

					logger.info(pojoMapper.toJson(logFormat, true));
				}
			}
		} 
		catch (Throwable e) 
		{
			logFormat.setName("Tenant-xml_"+currentTenantName+" test");
			logFormat.setComments("Tenant name: "+currentTenantName);
			logFormat.setStatus("failure");

			HashMap<String,String> infoMap = new HashMap<String, String>();
			infoMap.put("info","Exception in Tenant xml test.");
			infoMap.put("Exception",e.toString());			
			logFormat.setDetailedInfo(infoMap);

			logger.info(pojoMapper.toJson(logFormat, true));
		}
	}
}
