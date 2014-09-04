package com.progress.cloudservices.SystemSelfTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.log4j.Logger;

public class TestCloudServicesProperties extends AbstractSystemSelfTest{

	public static final Logger logger = Logger.getLogger(RunTests.class);
	JacksonPojoMapper pojoMapper = new JacksonPojoMapper();
	LogFormat logFormat=new LogFormat();

	@Override
	public void runTest(Properties props)
	{
		String propertyKey = null;
		try
		{
			logFormat.setProduct(props.getProperty("product-name").toString());
			logFormat.setName( "cs.properties test");
			
			String env=props.getProperty("deployment.environment"); 
			
			String goldFileAddress=TenantXmlConstants.cloudServiceGoldPropertiesFileAddress;
			String tenantXmlPropertiesFileAddress=TenantXmlConstants.tenantXmlPropertiesFileAddress;


			String[] listOfProperties = SystemSelfTestUtil.getProperty(tenantXmlPropertiesFileAddress,TenantXmlConstants.listOfProperties, "").split(",");

			Map<String, String> goldFile=SystemSelfTestUtil.getProperties(goldFileAddress);
			Map<String, String> csFile=CommonUtility.getAllCloudServicesProperties();


			for(String key : goldFile.keySet())
			{
				String goldFileValue=goldFile.get(key);
				String csFileValue=csFile.get(key);				
				
				logFormat.setComments("Property_key:"+key);
			
				if(goldFileValue.equals(csFileValue))
				{
					logFormat.setStatus("success");
					logFormat.setDetailedInfo(null);
				}

				else
				{
					logFormat.setStatus("warning");
					HashMap<String,String> infoMap = new HashMap<String, String>();
					infoMap.put("info","Validation of property failed.");
					infoMap.put("key", key);
					infoMap.put("Expected value",goldFileValue);
					infoMap.put("Read value",csFileValue);
					
					logFormat.setDetailedInfo(infoMap);
				}
				logger.info(pojoMapper.toJson(logFormat, true));
			}

			for(int i = 0; i < listOfProperties.length ; i++)
			{
				propertyKey=listOfProperties[i];
				String value=CommonUtility.getCloudServicesProperty(propertyKey,"");
				String expectedFormat =  SystemSelfTestUtil.getProperty(TenantXmlConstants.tenantXmlPropertiesFileAddress ,env+"."+TenantXmlConstants.cloudService+"."+propertyKey+"."+TenantXmlConstants.regex,"");
				
				logFormat.setComments("Property key: "+propertyKey+" value: "+value);

				try{									
					if(isValidCSProperty(propertyKey, value,env, tenantXmlPropertiesFileAddress))
					{
						logFormat.setStatus("success");
						logFormat.setDetailedInfo(null);
					}
					else
					{
						logFormat.setStatus("failure");
						
						HashMap<String,String> infoMap = new HashMap<String, String>();
						infoMap.put("info","Validation of property failed..");
						logFormat.setDetailedInfo(infoMap);
						infoMap.put("key", propertyKey);
						infoMap.put("Expected format",expectedFormat);
												
						logFormat.setDetailedInfo(infoMap);
					}
					logger.info(pojoMapper.toJson(logFormat, true));
				}
				catch (Exception e) 
				{
					logFormat.setStatus("failure");
					
					HashMap<String,String> infoMap = new HashMap<String, String>();
					infoMap.put("info","Exception in Exception in cloud-services.properties test.");
					infoMap.put("Exception",e.toString());
					logFormat.setDetailedInfo(infoMap);
					
					logger.info(pojoMapper.toJson(logFormat, true));
				}

			}
		} 
		catch (Throwable e) 
		{
			logFormat.setProduct(props.getProperty("product-name").toString());
			logFormat.setName( "cs.properties test");
			
			logFormat.setComments("Property : "+propertyKey) ;  
			logFormat.setStatus("failure");
			
			HashMap<String,String> infoMap = new HashMap<String, String>();
			infoMap.put("info","Exception in cloud-services.properties test.");
			infoMap.put("Exception",e.toString());			
			logFormat.setDetailedInfo(infoMap);
			
			logger.info(pojoMapper.toJson(logFormat, true));
		}
	}
}
