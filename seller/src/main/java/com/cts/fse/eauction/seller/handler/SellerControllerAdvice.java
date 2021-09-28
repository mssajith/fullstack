package com.cts.fse.eauction.seller.handler;


import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cts.fse.eauction.seller.exception.SellerValidationException;
import com.cts.fse.eauction.seller.model.ErrorInfo;

//@ControllerAdvice
public class SellerControllerAdvice {

	/*
	 * @ExceptionHandler(SellerValidationException.class) public
	 * ResponseEntity<Object> handleNodataFoundException( SellerValidationException
	 * ex, WebRequest request) {
	 * 
	 * System.out.println("Exception Error Message is :: " + ex.getErrorMessage());
	 * 
	 * Map<String, Object> body = new LinkedHashMap<>(); body.put("timestamp",
	 * LocalDateTime.now()); body.put("message", ex.getErrorMessage());
	 * 
	 * return new ResponseEntity<>(body, HttpStatus.NOT_FOUND); }
	 */

	/*
	 * @ResponseStatus(HttpStatus.BAD_REQUEST)
	 * 
	 * @ExceptionHandler({SellerValidationException.class})
	 * 
	 * @ResponseBody ErrorInfo handleBadRequest(HttpServletRequest req, Exception
	 * ex) { System.out.println("Inside Controller Advice :::"); return new
	 * ErrorInfo(req.getRequestURI(), ex); }
	 */
	@ExceptionHandler({ SellerValidationException.class })
	public String databaseError(SellerValidationException exception) {
		// Nothing to do. Return value 'databaseError' used as logical view name
		// of an error page, passed to view-resolver(s) in usual way.
		System.out.println("The Exception Message is :: " + exception.getErrorMessage());
		return exception.getErrorMessage();
	}
	
	
	/*
	 * @ExceptionHandler(SellerValidationException.class)
	 * 
	 * @ResponseBody ErrorInfo ResponseEntity<Object> businessErrorhandler(
	 * HttpServletRequest request, SellerValidationException exception) { //LOGGER.
	 * error("*************************Inside businessErrorhandler : Exception occured "
	 * , exception); Map<String, Object> body = new LinkedHashMap<>();
	 * body.put("timestamp", LocalDateTime.now()); body.put("message",
	 * exception.getErrorMessage()); return new ErrorInfo(request.getRequestURL(),
	 * exception); }
	 */

}
