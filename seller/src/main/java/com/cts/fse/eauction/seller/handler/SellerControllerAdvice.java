package com.cts.fse.eauction.seller.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cts.fse.eauction.seller.exception.SellerValidationException;

//@ControllerAdvice
public class SellerControllerAdvice {
	@ExceptionHandler({ SellerValidationException.class })
	public String databaseError(SellerValidationException exception) {
		// Nothing to do. Return value 'databaseError' used as logical view name
		// of an error page, passed to view-resolver(s) in usual way.
		System.out.println("The Exception Message is :: "
				+ exception.getErrorMessage());
		return exception.getErrorMessage();
	}

}
