/*******************************************************************************
 * Copyright � 2014 Progress Software Corporation.  All Rights Reserved.
 ******************************************************************************/
package com.progress.cloudservices.SystemSelfTest;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.axis.encoding.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

/**
 * Common Utility class which defines utility methods
 * 
 * @author dprasad
 * @since 0.5
 */
public final class CommonUtility {

	private static Map<String, String> csPropertiesMap = null;

	private static Map<String,String> userProfileMapper;
	private static Map<String,String> ldapProfileMapper;
	static {

		userProfileMapper = new HashMap<String,String>();
		userProfileMapper.put("o","officeName");
		userProfileMapper.put("givenName","firstName");
		userProfileMapper.put("sn","lastName");
		userProfileMapper.put("telephoneNumber","telephoneNumber");
		userProfileMapper.put("displayName","displayName");
		userProfileMapper.put("l","city");
		userProfileMapper.put("mail","email");
		userProfileMapper.put("uid","progressId");
		userProfileMapper.put("postalCode","shippingAddressPostalCode");
		userProfileMapper.put("businessCategory","businessCategory");
		userProfileMapper.put("title","title");
		userProfileMapper.put("cn","contactName");
		userProfileMapper.put("description","jobDescription");
		userProfileMapper.put("st","state");
		userProfileMapper.put("street","shippingAddressStreet");
		userProfileMapper.put("userPassword","password");
		userProfileMapper.put("userAccountDisabled","accountDisabled");
		userProfileMapper.put("sendWelcomeMessage","sendWelcomeMessage");
		userProfileMapper.put("nextUrl","nextUrl");

		ldapProfileMapper = new HashMap<String,String>();
		for(String key : userProfileMapper.keySet()) {
			String value = userProfileMapper.get(key);
			ldapProfileMapper.put(value,key);
		}

	}

	public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(CommonServicesConstants.DATE_FORMAT);

	public static final SimpleDateFormat simpleDateOnlyFormat = new SimpleDateFormat(CommonServicesConstants.DATE_ONLY_FORMAT);
	public static final SimpleDateFormat simpleDateFullFormat = new SimpleDateFormat(CommonServicesConstants.DATE_WITH_TIMESTAMP_FORMAT);

	/**
	 * Method to convert a given object into byte array, this is required while
	 * saving blob data types into DB using Hibernate.
	 * 
	 * @param o
	 *            , the object which needs to be converted into byte array
	 * @return byte array equivalent of the object
	 * @throws Exception 
	 */
	public static final byte[] getByteArrayFromObject(Object o) throws Exception {
		if (o == null)
			return new byte[] {};

		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream out;
		out = new ObjectOutputStream(byteOut);
		out.writeObject(o);

		return byteOut.toByteArray();
	}

	/**
	 * Method to convert a byte array into an object, required to retrieve blob
	 * data types from Hibernate.
	 * 
	 * @param b
	 *            , byte array which needs to be converted into an object
	 * @return object equivalent of byte array
	 * @throws Exception 
	 */
	public static final Object getObjectFromByteArray(byte[] b) throws Exception {
		if (b == null || b.length == 0)
			return null;

		ByteArrayInputStream byteIn = new ByteArrayInputStream(b);
		ObjectInputStream in = null;
		Object o = null;

		in = new ObjectInputStream(byteIn);
		o = in.readObject();

		return o;
	}

	public static final String getStringFromByteArray(byte[] b) {
		if (b == null || b.length == 0)
			return null;

		return new String(b);
	}

	public static final boolean isNullOrEmpty(String s){
		if(s != null && !s.isEmpty())
			return false;
		return true;
	}
	public static List<String> getListFromCSVString(String csvString) {
		String [] returnArray = new String[0];
		if(!isNullOrEmpty(csvString)) {
			String[] splitIt = csvString.split(",");
			returnArray = splitIt;
			if(splitIt.length>0) {
				returnArray = new String[splitIt.length];
				int i = 0;
				for(String b :splitIt) returnArray[i++]=b.trim(); 
			}
		}
		return Arrays.asList(returnArray);
	}



	public static File getFileFromInputStream(InputStream inputStream, String fileName) throws Exception {
		OutputStream outputStream = null;
		File file = null;
		try {
			// write the inputStream to a FileOutputStream
			file = new File(fileName);
			outputStream = new FileOutputStream(file);

			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}

		} catch (IOException e) {
			throw new Exception(e);

		} finally {
			if (outputStream != null) {
				try {
					outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
					throw new Exception(e);
				}
			}
		}

		return file;
	}

	public static CSHttpResponse postRestCall(String requestURL, String requestBody,
			boolean authorize, String username, String password, String contentType, int timeout) {
		DefaultHttpClient httpclient = null;		
		try {

			HttpParams params = new BasicHttpParams();

			HttpConnectionParams.setConnectionTimeout(params, timeout);
			HttpConnectionParams.setSoTimeout(params, timeout);

			httpclient = new DefaultHttpClient(params);
			HttpPost httpPost = new HttpPost(requestURL);
			if(authorize) {
				httpPost.setHeader("Authorization", base64Encode(username, password));
			}
			httpPost.setHeader("Content-Type", contentType);
			httpPost.setEntity(new StringEntity(requestBody));
			HttpResponse response = httpclient.execute(httpPost);
			String output = null;
			int statusCode = 0; 
			if(response.getStatusLine() != null ) {
				statusCode = response.getStatusLine().getStatusCode();
			}

			if(response.getEntity() != null && response.getEntity().getContent() != null) {					
				output = EntityUtils.toString(response.getEntity());
				//		logger.info("Response status:: " + response.getStatusLine() + " Output:: "+ output);
			}

			CSHttpResponse csResponse = new CSHttpResponse(response, output, statusCode);
			return csResponse;
		} catch (Exception e) {
			throw new CloudServicesException(e);
		}
		finally {
			if(httpclient != null) {
				httpclient.getConnectionManager().shutdown();
			}
		}
	}


	public static String getRestCall(String requestURL, boolean authorize, String username, String password,Map<String, String> headers, int timeout) {
		String output = null;
		CSHttpResponse csResponse = getRestCallHttpResponse(requestURL, authorize, username,
				password, headers, timeout);;

				String responseReasonPhrase = "";
				int statusCode = csResponse.getStatusCode();

				HttpResponse response = csResponse.getHttpResponse();
				if(response != null) {
					responseReasonPhrase = response.getStatusLine().getReasonPhrase();
				}

				if(statusCode != HttpStatus.SC_OK) {
					throw new CloudServicesException("Error making the request for " + requestURL + " :: "+ statusCode + " " + responseReasonPhrase);
				}else{
					output = csResponse.getResponse();
				}

				return output;
	}

	public static CSHttpResponse getRestCallHttpResponse(String requestURL,
			boolean authorize, String username, String password,
			Map<String, String> headers, int timeout)
					throws CloudServicesException {
		DefaultHttpClient httpclient = null;		
		try {

			HttpParams params = new BasicHttpParams();

			HttpConnectionParams.setConnectionTimeout(params, timeout);
			HttpConnectionParams.setSoTimeout(params, timeout);

			httpclient = new DefaultHttpClient(params);	

			HttpGet httpGet = new HttpGet(requestURL);
			if(headers instanceof Map) {
				for(Entry<String, String> e : headers.entrySet()){
					httpGet.addHeader(e.getKey(), e.getValue());
				}
			}

			if(authorize) {
				httpGet.setHeader("Authorization", base64Encode(username, password));
			}

			HttpResponse response = httpclient.execute(httpGet);
			String output = null;
			int statusCode = 0; 
			if(response != null) {
				if(response.getStatusLine() != null ) {
					statusCode = response.getStatusLine().getStatusCode();
				}
				if(response.getEntity() != null ) {
					output = EntityUtils.toString(response.getEntity());
					//		if (logger.isDebugEnabled())
					//			    logger.debug("Response status:: " + response.getStatusLine() + " Output:: "+ output);
				}
				else {
					throw new CloudServicesException("Error making the request for " + requestURL + " :: "+ response.getStatusLine());
				}
			}
			else {
				throw new CloudServicesException("Error making the request for " + requestURL);
			}

			CSHttpResponse csResponse = new CSHttpResponse(response, output, statusCode);
			return csResponse;
		} catch (Exception e) {
			throw new CloudServicesException(e);
		}
		finally {
			if(httpclient != null) {
				httpclient.getConnectionManager().shutdown();
			}
		}
	}

	public static String postRestCallWithParams(String requestURL, Map<String, String> parameters,
			boolean authorize, String username, String password, String contentType, int timeout) {
		DefaultHttpClient httpclient = null;

		try {

			HttpParams params = new BasicHttpParams();

			HttpConnectionParams.setConnectionTimeout(params, timeout);
			HttpConnectionParams.setSoTimeout(params, timeout);

			httpclient = new DefaultHttpClient(params);	

			List<NameValuePair> pairs = new ArrayList<NameValuePair>();
			for (Entry<String, String> parameter : parameters.entrySet()) {
				if (parameter.getValue() != null) {
					pairs.add(new BasicNameValuePair(parameter.getKey(),
							parameter.getValue()));
				}
			}
			HttpPost httpPost = new HttpPost(requestURL);
			httpPost.setEntity(new UrlEncodedFormEntity(pairs, CommonServicesConstants.UTF8_CHARACTER_ENCODING));

			if(authorize) {
				httpPost.setHeader("Authorization", base64Encode(username, password));
			}
			HttpResponse response = httpclient.execute(httpPost);
			String output = null;
			if(response != null) {
				if(response.getEntity() != null && response.getEntity().getContent() != null) {
					output = EntityUtils.toString(response.getEntity());
					//		logger.info("Response status:: " + response.getStatusLine() + " Output:: "+ output);
				}
				if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					return output;
				}
				else {
					throw new CloudServicesException("Error making the request for " + requestURL + " :: " + response.getStatusLine());
				}
			}
			else {
				throw new CloudServicesException("Error making the request for " + requestURL);
			}
		} catch (Exception e) {
			throw new CloudServicesException(e);
		}
		finally {
			if(httpclient != null) {
				httpclient.getConnectionManager().shutdown();
			}
		}
	}

	public static String convertInputStreamToString(InputStream inputStream)
			throws IOException {
		if (inputStream != null) {
			StringBuilder stringBuilder = new StringBuilder();
			String line;
			try {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(inputStream,
								CommonServicesConstants.UTF8_CHARACTER_ENCODING));
				while ((line = reader.readLine()) != null) {
					stringBuilder.append(line).append("\n");
				}
			} finally {
				inputStream.close();
			}

			return stringBuilder.toString();
		} else {
			return null;
		}
	}

	public static String getCloudServicesProperty(String key,
			String defaultValue) throws Exception {
			String value = getAllCloudServicesProperties().get(key);
			if (!isNullOrEmpty(value)) {
				return value;
			}
		return defaultValue;
	}

	public static Map<String, String> getAllCloudServicesProperties() throws Exception 
	{
		try {
			if (csPropertiesMap == null) 
			{
				csPropertiesMap = new HashMap<String, String>();

				InputStream is = new FileInputStream(ApplicationStartup.catalinaHome+"/conf/cloud-services.properties");
				if (is != null)
				{
					Properties props = new Properties();
					props.load(is);
					for (String propName : props.stringPropertyNames()) {
						csPropertiesMap
						.put(propName, props.getProperty(propName));
					}
				}
				
			}
		} catch (Exception e)
		{
			throw new Exception("Error reading the cloud-services.properties file, Path used:"+ApplicationStartup.catalinaHome+"/conf/cloud-services.properties",e);
		}
		return csPropertiesMap;
	}	
	public static Properties getAllSystemSelfTestProperties() throws Exception
	{
		Properties properties = new Properties();
		try 
		{
			InputStream is = new FileInputStream(ApplicationStartup.catalinaHome+"/conf/system-self-test.properties");
			if (is != null)
			{
				properties.load(is);
			}
		} 
		catch (Exception e) 
		{
			throw new Exception("Exception reading system Self-Test Properties:",e);
		}
		return properties;
	}	

	
	public static String getPropertyFromFallBackFile(String filePath,String property) throws Exception
	{
		String value=null;
		Properties properties = new Properties();
		try 
		{
			InputStream is = new FileInputStream(filePath);
			if (is != null)
			{
				properties.load(is);
				value = properties.getProperty(property);
			}
		} 
		catch (Exception e) 
		{
			throw new Exception("Exception reading fall back Properties file:",e);
		}
		return value;
	}

	public static String base64Encode(String username, String password) {
		String encodedCredentials = Base64.encode((username+":"+password).getBytes());
		String authHeader = "Basic " + new String(encodedCredentials);
		return authHeader;
	}
}
