/*******************************************************************************
 * Copyright © 2014 Progress Software Corporation.  All Rights Reserved.
 ******************************************************************************/
package com.progress.cloudservices.SystemSelfTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;


public class HttpInvoker {

	public static final String UTF8_CHARACTER_ENCODING = "UTF-8";
//	public static Log logger = LogFactory.getLog(HttpInvoker.class);
	
	public static String requestRESTPost(String requestURL,
			Map<String, String> parameters, Map<String, String> headers, int timeout) throws Exception {
		String output = null;
		CSHttpResponse csResponse = requestRESTPostResponse(requestURL, parameters, headers, timeout);
		
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
	
	public static CSHttpResponse requestRESTPostResponse(String requestURL,
			Map<String, String> parameters, Map<String, String> headers, int timeout) throws Exception {
		if (requestURL != null && requestURL.length() > 0 && parameters != null) {
	/*		if (logger.isTraceEnabled()) {
				logger.trace("The URL getting posted is : " + requestURL
						+ " and the paramters are : " + parameters
						+ " timeout : " + timeout);
			}
		*/
			HttpClient httpClient = new DefaultHttpClient();

			HttpParams params = httpClient.getParams();
			if (timeout > -1) {
				params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,
						timeout);
				params.setParameter(CoreConnectionPNames.SO_TIMEOUT, timeout);
			}

			HttpPost httpPost = new HttpPost(requestURL);
			
			for(Entry<String, String> e : headers.entrySet()){
				httpPost.addHeader(e.getKey(), e.getValue());
			}
			

			List<NameValuePair> pairs = new ArrayList<NameValuePair>();
			for (Entry<String, String> parameter : parameters.entrySet()) {
				if (parameter.getValue() != null) {
					pairs.add(new BasicNameValuePair(parameter.getKey(),
							parameter.getValue()));
				}
			}

			try {
				UrlEncodedFormEntity sendentity = new UrlEncodedFormEntity(
						pairs, UTF8_CHARACTER_ENCODING);
				httpPost.setEntity(sendentity);
				HttpResponse response = httpClient.execute(httpPost);
				String output = null;
				int statusCode = 0;
				if(response != null) {
					if(response.getStatusLine() != null) {
						statusCode = response.getStatusLine().getStatusCode();
					}
					
					if(response.getEntity() != null ) {
						output = EntityUtils.toString(response.getEntity());
						
		//				logger.info("Response status:: " + response.getStatusLine() + " Output:: "+ output);
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
				throw new Exception(
						"Failed while making request to the URL:"
								+ requestURL + ". The post parameters are : "
								+ parameters + "Reason: " + e.getMessage());
			} finally {
				if(httpClient != null) {
					httpClient.getConnectionManager().shutdown();
				}
			}
		} else {
			throw new Exception(
					"Failed while making request. Invalid Input. The URL, parameters can not be null");
		}

	}

}
