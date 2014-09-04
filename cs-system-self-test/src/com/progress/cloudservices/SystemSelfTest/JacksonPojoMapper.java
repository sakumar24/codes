/*******************************************************************************
 * Copyright © 2014 Progress Software Corporation.  All Rights Reserved.
 ******************************************************************************/
package com.progress.cloudservices.SystemSelfTest;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * This utility can be used for mapping to and from Json. It leverages
 * Jackson 1.9.5 apis.
 * 
 * @author vseth
 * 
 */
public class JacksonPojoMapper {

	private static ObjectMapper m = new ObjectMapper();
	private static JsonFactory jf = new JsonFactory();

	public String toJson(Object pojo, boolean prettyPrint)
	{
		try
		{
			StringWriter sw = new StringWriter();
			JsonGenerator jg = jf.createJsonGenerator(sw);
			if (prettyPrint) {
				jg.useDefaultPrettyPrinter();
			}
			m.writeValue(jg, pojo);
			return sw.toString();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void toJson(Object pojo, FileWriter fw, boolean prettyPrint)
	{
		try
		{
			JsonGenerator jg = jf.createJsonGenerator(fw);
			if (prettyPrint) {
				jg.useDefaultPrettyPrinter();
			}
			m.writeValue(jg, pojo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
