package com.progress.cloudservices.SystemSelfTest;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;












import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.progress.cloudservices.CommonTenantConfig.DataSourcesConfig;
import com.progress.cloudservices.CommonTenantConfig.EndPoints;
import com.progress.cloudservices.CommonTenantConfig.KeyValuePairType;
import com.progress.cloudservices.CommonTenantConfig.TenantAccountUrl;
import com.progress.cloudservices.CommonTenantConfig.TenantAdditionalPropsConfig;
import com.progress.cloudservices.CommonTenantConfig.TenantConfig;
import com.progress.cloudservices.CommonTenantConfig.UIServiceConfig;

public class SystemSelfTestUtil
{
	//public static final Logger logger = Logger.getLogger(RunTests.class);
	public static HashMap<String,String> extractUrls(List<String> listOfUrlsToTest,TenantConfig tenant) 
	{

		HashMap<String,String> allUrlsMap = new HashMap<String, String>();

		TenantAdditionalPropsConfig tenantAdditionalProps=tenant.getTenantAdditionalProps();
		TenantAccountUrl tenantCorporateUrl=tenant.getTenantCorporateUrl();
		TenantAccountUrl tenantPartnerUrl=tenant.getTenantPartnerUrl();
		DataSourcesConfig dataSource = tenant.getDataSources();

		UIServiceConfig uiService=tenant.getUiService();

		EndPoints endPoints=tenant.getEndPoints();

		if(dataSource!=null && listOfUrlsToTest.contains(TenantXmlConstants.dataSourceUrl)){
			if(dataSource.getDataSource()!=null)
				if(dataSource.getDataSource().get(0)!=null)
					if(dataSource.getDataSource().get(0).getImage()!=null)
						allUrlsMap.put(TenantXmlConstants.dataSourceUrl,dataSource.getDataSource().get(0).getImage().getSrc());
		}
		if(listOfUrlsToTest.contains(TenantXmlConstants.tenantBaseURL))
		{
			allUrlsMap.put(TenantXmlConstants.tenantBaseURL,tenant.getTenantBaseURL());
		}
		if(listOfUrlsToTest.contains(TenantXmlConstants.tenantLogoURL))
		{
			allUrlsMap.put(TenantXmlConstants.tenantLogoURL,tenant.getTenantLogoURL());
		}
		if(tenantAdditionalProps!=null){

			for ( KeyValuePairType key :  tenantAdditionalProps.getTenantProp())
			{
				if(listOfUrlsToTest.contains(key.getKey())){
					/*if(key.getKey().equals("logoutUrl")){

						for( String entry: key.getValue().split(",")){
							System.out.println("-----------------------------adding "+ entry +"+");
							allUrlsMap.put(key.getKey(), entry);
						}

					}*/
					allUrlsMap.put(key.getKey(), key.getValue());   
				}
				/*	else{
					System.out.println(key.getKey()+" is not der");
				}*/
			}
		}

		if(tenantCorporateUrl!=null && listOfUrlsToTest.contains(TenantXmlConstants.tenantCorporateBaseUrl)){
			allUrlsMap.put(TenantXmlConstants.tenantCorporateBaseUrl,tenantCorporateUrl.getBaseUrl());
		}
		if(tenantPartnerUrl!=null && listOfUrlsToTest.contains(TenantXmlConstants.tenantPartnerBaseUrl)){
			allUrlsMap.put(TenantXmlConstants.tenantPartnerBaseUrl,tenantPartnerUrl.getBaseUrl());
		}

		if(uiService!=null && listOfUrlsToTest.contains(TenantXmlConstants.tenantRedirectURL)){
			allUrlsMap.put(TenantXmlConstants.tenantRedirectURL,uiService.getTenantRedirectURL());
		}

		if(endPoints!=null){
			if(endPoints.getAccountProvisionApi()!=null && listOfUrlsToTest.contains(TenantXmlConstants.accountProvisionApiEndPointURL))
				allUrlsMap.put(TenantXmlConstants.accountProvisionApiEndPointURL,endPoints.getAccountProvisionApi().getEndpointUrl());
			if(endPoints.getAccountNotificationApi()!=null && listOfUrlsToTest.contains(TenantXmlConstants.accountNotificationApiEndPointURL))
				allUrlsMap.put(TenantXmlConstants.accountNotificationApiEndPointURL,endPoints.getAccountNotificationApi().getEndPointURL());
			if(endPoints.getPConsoleCardConfig()!=null && listOfUrlsToTest.contains(TenantXmlConstants.pConsoleFrontUrl))
				allUrlsMap.put(TenantXmlConstants.pConsoleFrontUrl,endPoints.getPConsoleCardConfig().getPConsoleFrontUrl());
		}
		return allUrlsMap;

	}

	public static void SetDbVariables(Properties PropertiesFile) throws Exception
    {
           File XmlFile = new File(ApplicationStartup.catalinaHome+"/conf/server.xml");

           DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
           DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

           Document doc = dBuilder.parse(XmlFile);

           doc.getDocumentElement().normalize();

           NodeList nList = doc.getElementsByTagName("GlobalNamingResources");
           NodeList cList = nList.item(0).getChildNodes();

           for (int temp = 0; temp < cList.getLength(); temp++)
           {
                  Node nNode = cList.item(temp);
                  if (nNode.getNodeType() == Node.ELEMENT_NODE)
                  {
                        NamedNodeMap nameNodeMap = nNode.getAttributes();
                        if(nameNodeMap.getNamedItem("name").toString().matches("(.*)jdbc/csdb(.*)"))
                        {
                               PropertiesFile.put("databaseUrl", nameNodeMap.getNamedItem("url").getNodeValue());
                               PropertiesFile.put("username", nameNodeMap.getNamedItem("username").getNodeValue());
                               PropertiesFile.put("password", nameNodeMap.getNamedItem("password").getNodeValue());
                               PropertiesFile.put("jdbcDriver", nameNodeMap.getNamedItem("driverClassName").getNodeValue());
                        }
                  }                    
           }
    }

	
	public static CSHttpResponse testUrl(EndPoints.AuthConfig authConfig,String requestURL)
	{
		String username = "";
		String password = "";
		boolean authorize=true;

		if(authConfig!=null){
			username = authConfig.getUsername();
			password = authConfig.getPassword();
		}

		CSHttpResponse csResponse=CommonUtility.getRestCallHttpResponse(requestURL,authorize, username, password,null, 0);

		return csResponse;
	}
	public static CSHttpResponse testOem(TenantConfig tenant,String requestURL)
	{
		String username = "";
		String password = "";
		String requestBody="";
		boolean authorize=true;
			TenantAdditionalPropsConfig tenantAdditionalProps=tenant.getTenantAdditionalProps();
		if(tenantAdditionalProps!=null){
			for ( KeyValuePairType key :  tenantAdditionalProps.getTenantProp())
			{
				if(key.getKey().matches(TenantXmlConstants.oem_username))
					username=key.getValue();
				if(key.getKey().matches(TenantXmlConstants.oem_password))
					password=key.getValue();
			}
		}
		CSHttpResponse csResponse= CommonUtility.postRestCall( requestURL,requestBody,authorize,username, password, "", 300000);
		return csResponse;
	}

	public static CSHttpResponse testAccountProvisionApi(EndPoints.AuthConfig authConfig,String url)
	{
		String username = authConfig.getUsername();
		String password = authConfig.getPassword();

		ObjectNode objNode = new ObjectMapper().createObjectNode();
		objNode.put("progressId", "admin123");
		objNode.put("firstName", "srireddy");
		objNode.put("lastName", "srireddy");
		objNode.put("emailAddress", "sri1reddy@gmail.com".toLowerCase());
		objNode.put("autoProvision", false);
		objNode.put("planName", "Evaluation");
		objNode.put("accountType", "CORPORATE_ONLY");
		objNode.put("streetAddr1","streetAddress");
		objNode.put("city","city");
		objNode.put("zip","zip");

		String requestBody=objNode.toString();
		int CONNECT_TIMEOUT = 300 * 1000;

		CSHttpResponse csResponse = CommonUtility.postRestCall(url,requestBody, true, username, password,"application/json", CONNECT_TIMEOUT);
		return csResponse;
	}


	public static CSHttpResponse testAccountNotificationApi(EndPoints.AuthConfig authConfig,String url) throws Exception
	{
		String requestBody ="";
		String username = authConfig.getUsername();
		String password = authConfig.getPassword();

		String contentType = CommonUtility.getCloudServicesProperty(IUIServicesBillingConstants.UISERVICE_CONTENT_TYPE,
				CommonServicesConstants.APPLICATION_CONTENT_TYPE);
		ObjectNode account = new ObjectMapper().createObjectNode();
		account.put("accountId",0);
		account.put("progressId","sep20u1");
		account.put("firstName" ,"srireddy");
		account.put("lastName" ,"srireddy");
		account.put("emailAddress" ,"sri1reddy@gmail.com");
		account.put("tenantName" ,"easyl-main-int");
		account.put("loginName","ckumar");
		account.put("language" ,"en_US");
		account.put("timeZoneId", "GMT");
		account.put("openId", "test");
		account.put("userCreationDate", "2014-01-03 18:52:28");
		account.put("lastModifiedDate", "2014-01-03 18:55:46");
		account.put("accountType", "INDIVIDUAL");
		account.put("eval", "FALSE");
		account.put("accountCreationDate", "2014-01-03 18:51:48");
		account.put("planName", "Easyl Artist");
		account.put("evalExpiryDate", "2014-04-04 18:51:48");
		account.put("accountStatus", "ACTIVE");
		account.put("billingId", "14863832");
		account.put("billingStatus", "ACTIVE_TRIAL");
		requestBody=account.toString();

		CSHttpResponse postRestCall = CommonUtility.postRestCall(url, requestBody, true, username, password,contentType, 30000);
		return postRestCall;
	}

	public static InputStream getTenantConfig(Properties PropertiesFile,String tenantName) throws Exception 
    {
           InputStream tenantConfig;
           Connection connection = null;
           final String dbUrl=PropertiesFile.getProperty("databaseUrl");
           final String jdbcDriver=PropertiesFile.getProperty("jdbcDriver");
           final String username=PropertiesFile.getProperty("username");
           final String password=PropertiesFile.getProperty("password");
           
           
           Class.forName(jdbcDriver);

           // Open a connection
           connection = DriverManager.getConnection(dbUrl,username,password);
           Statement stmt = connection.createStatement();
           String sql="select * from CS_TENANT where TENANT_NAME=\""+tenantName+"\";";

           ResultSet rs=stmt.executeQuery(sql);
           rs.next();
           tenantConfig = rs.getBlob("TENANT_CONFIG").getBinaryStream();
           return tenantConfig;
    }


	public static synchronized TenantConfig readTenantConfigFromXMLFile(InputStream tenatConfigInputStream) throws Exception 
	{
		JAXBContext jaxbContext = JAXBContext.newInstance(CommonServicesConstants.TENANT_CONFIG_BASE_PACKAGE);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();			
		JAXBElement<?> tenant = (JAXBElement<?>) unmarshaller.unmarshal(tenatConfigInputStream);
		TenantConfig tenantConfig = (TenantConfig )tenant.getValue();
		return tenantConfig;
	}

	public static String getProperty(String propertiesFileAddress, String key,
			String defaultValue) throws Exception {
		String value = getProperties(propertiesFileAddress).get(key);
		if (!isNullOrEmpty(value)) {
			return value;
		}
		return defaultValue;
	}

	public static Map<String, String> getProperties(String propertiesFileAddress) throws Exception {

		HashMap<String, String> propertiesMap = new HashMap<String, String>();

		InputStream is = CommonUtility.class.getResourceAsStream(propertiesFileAddress);
		if (is != null) {		
			Properties props = new Properties();
			props.load(is);
			for (String propName : props.stringPropertyNames()) {
				propertiesMap
				.put(propName, props.getProperty(propName));
			}
		}
		else{
			System.out.println(propertiesFileAddress+" is NULL");
		}
		return propertiesMap;
	}

	public static final boolean isNullOrEmpty(String s){
		if(s != null && !s.isEmpty())
			return false;
		return true;
	}
}