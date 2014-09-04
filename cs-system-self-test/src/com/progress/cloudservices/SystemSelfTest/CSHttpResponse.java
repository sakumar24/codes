/*******************************************************************************
 * Copyright © 2014 Progress Software Corporation.  All Rights Reserved.
 ******************************************************************************/
package com.progress.cloudservices.SystemSelfTest;

import org.apache.http.HttpResponse;

/**
 * Abstract class to define a response structure to be sent back after request
 * processing. This is t maintain a uniform experience across the cloud
 * services.
 * 
 * <p>
 * It only declares two fields {@link #statusCode} and {@link #statusMessage} as
 * they are inherent part of any response sent over the wire. Child classes must
 * add upon more details about the responses if required.
 * </p>
 * 
 * It would be better if all the <code>@Controller</code> classes have the
 * <code>@ResponseBody {@link CSHttpResponse} </code> in tehir method
 * signatures.
 * 
 * @author dprasad
 * @since 0.5
 */
public class CSHttpResponse {

	private HttpResponse httpResponse;
	private String response;
	private int statusCode;
	
	public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public HttpResponse getHttpResponse() {
		return httpResponse;
	}

	@Override
	public String toString() {
		return "CSHttpResponse [httpResponse=" + httpResponse + ", statusCode="+ statusCode + ", response="
				+ response + "]";
	}

	public void setHttpResponse(HttpResponse httpResponse) {
		this.httpResponse = httpResponse;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public CSHttpResponse(HttpResponse httpResponse, String response, int statusCode) {
		super();
		this.httpResponse = httpResponse;
		this.response = response;
		this.statusCode = statusCode;
	}

	public CSHttpResponse() {
		super();
	}

}
