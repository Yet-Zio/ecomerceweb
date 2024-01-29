package com.ecomerce.authentication.models;

public class CommonResponse {

	private boolean status;
	private String message;
	private int statusCode;
	private Object body;

	public CommonResponse(int statusCode, Object body) {
		super();
		this.status = true;
		this.message = "success";
		this.statusCode = statusCode;
		this.body = body;
	}
	
	

	public CommonResponse(String message, int statusCode) {
		super();
		this.message = message;
		this.statusCode = statusCode;
	}



	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getErrorCode() {
		return statusCode;
	}

	public void setErrorCode(int errorCode) {
		statusCode = errorCode;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

}
