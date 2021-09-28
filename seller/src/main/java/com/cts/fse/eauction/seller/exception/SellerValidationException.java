package com.cts.fse.eauction.seller.exception;

public class SellerValidationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6021553315478808060L;
	
	public String errorMessage;
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public SellerValidationException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

}
