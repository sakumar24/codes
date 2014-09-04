package com.progress.cloudservices.SystemSelfTest;


import com.progress.cloudservices.CommonTenantConfig.TenantConfig;
import com.progress.cloudservices.CommonTenantConfig.EndPoints.AuthConfig;

abstract public class AbstractSystemSelfTest implements TestCaseInterface
{
	public boolean isValidTenantProperty(String urlName,String url,String env,String tenantName,String propertiesFileAddress) throws Exception
	{
			String regexKey=env+"."+tenantName+"."+urlName+"."+TenantXmlConstants.regex;
			return url.matches(SystemSelfTestUtil.getProperty(propertiesFileAddress, regexKey, ""));	
	}
	public boolean isValidCSProperty(String key,String value,String env,String propertiesFileAddress) throws Exception
	{
		String regexKey=env+"."+TenantXmlConstants.cloudService+"."+key+"."+TenantXmlConstants.regex;
		return value.matches(SystemSelfTestUtil.getProperty(propertiesFileAddress, regexKey, ""));			
	}

	public CSHttpResponse invokeUrl(String urlName,String url,String env,String tenantName,String propertiesFileAddress,TenantConfig tenantConfig) throws Exception 
	{
			String PropertiesKey=env+"."+tenantName+"."+urlName+"."+TenantXmlConstants.invokeWith;
			
			AuthConfig AuthConfig=null;
			if(tenantConfig.getEndPoints()!=null)
				 AuthConfig = tenantConfig.getEndPoints().getAuthConfig();
		
			String property = SystemSelfTestUtil.getProperty(propertiesFileAddress,PropertiesKey,"");
			
			if(property.equals(TenantXmlConstants.getRestCall))
				return SystemSelfTestUtil.testUrl(AuthConfig,url);
			else if(property.equals(TenantXmlConstants.testAccountNotificationApiEndPointURL))
				return	SystemSelfTestUtil.testAccountNotificationApi(AuthConfig, url);
			else if(property.equals(TenantXmlConstants.testAccountProvisionApiEndPointURL))
				return SystemSelfTestUtil.testAccountProvisionApi(AuthConfig, url);
			else if(property.equals(TenantXmlConstants.testOem))
                return SystemSelfTestUtil.testOem(tenantConfig,url);
         			
			
/*			switch(SystemSelfTestUtil.getProperty(propertiesFileAddress,PropertiesKey,"")){
			case TenantXmlConstants.getRestCall:
				return SystemSelfTestUtil.TestUrl(AuthConfig,url);
			case TenantXmlConstants.AccountNotificationApiEndPointURL:
				return	SystemSelfTestUtil.TestaccountNotificationApi(AuthConfig, url);
			case TenantXmlConstants.AccountProvisionApiEndPointURL:
				return SystemSelfTestUtil.TestaccountProvisionApi(AuthConfig, url);
			}
	*/	
			return null;
			}
}
