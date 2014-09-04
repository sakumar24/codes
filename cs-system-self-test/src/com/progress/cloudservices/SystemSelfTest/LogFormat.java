package com.progress.cloudservices.SystemSelfTest;


import java.io.Serializable;
import java.util.HashMap;

public class LogFormat implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private String Name;
	private String Status;
	private String Comments;
	private HashMap<String,String> DetailedInfo;
	private String Product;
	
	public LogFormat()
	{
		this.Name = null;
		this.Comments = null;
		this.Status = null;
		this.setDetailedInfo(null);
		this.Product = null;
	}
		
	public String getProduct() {
		return Product;
	}

	public void setProduct(String product) {
		Product = product;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getComments() {
		return Comments;
	}

	public void setComments(String comments) {
		Comments = comments;
	}

	public HashMap<String,String> getDetailedInfo() {
		return DetailedInfo;
	}
	public void setDetailedInfo(HashMap<String,String> detailedInfo) {
		DetailedInfo = detailedInfo;
	}

}