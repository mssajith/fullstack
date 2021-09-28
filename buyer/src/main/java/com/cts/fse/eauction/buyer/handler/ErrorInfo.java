package com.cts.fse.eauction.buyer.handler;

public class ErrorInfo {
	private String requestURI;
	private String message;

	public String getRequestURI() {
		return requestURI;
	}

	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ErrorInfo(String requestURI, String message) {
		this.requestURI = requestURI;
		this.message = message;
	}
	
	
	
}
