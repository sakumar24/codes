/*******************************************************************************
 * Copyright © 2014 Progress Software Corporation.  All Rights Reserved.
 ******************************************************************************/
package com.progress.cloudservices.SystemSelfTest;

/**
 * Root exception class, written to provide a uniform exception hierarchy across
 * all the cloud services. 
 * 
 * @author dprasad
 * @since 0.5
 */
public class CloudServicesException extends RuntimeException {

	private static final long serialVersionUID = -4547559198144469869L;

	public CloudServicesException(String errorMessage) {
		super(errorMessage);
	}

	public CloudServicesException() {
		super();
	}

	public CloudServicesException(String message, Throwable cause) {
		super(message, cause);
	}

	public CloudServicesException(Throwable cause) {
		super(cause);
	}

}
