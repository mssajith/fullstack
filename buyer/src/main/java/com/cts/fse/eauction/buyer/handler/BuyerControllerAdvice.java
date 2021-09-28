package com.cts.fse.eauction.buyer.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import com.cts.fse.eauction.buyer.exception.BuyerValidationException;

@ControllerAdvice
public class BuyerControllerAdvice {

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

	@ResponseStatus(HttpStatus.BAD_REQUEST)

	@ExceptionHandler({ BuyerValidationException.class })

	@ResponseBody
	ErrorInfo handleBadRequest(HttpServletRequest req, BuyerValidationException ex) {
		System.out.println("Inside Controller Advice :::");
		return new ErrorInfo(req.getRequestURI(), ex.getErrorMessage());
	}

	/*
	 * @ExceptionHandler({ BuyerValidationException.class }) public String
	 * databaseError(BuyerValidationException exception) { // Nothing to do. Return
	 * value 'databaseError' used as logical view name // of an error page, passed
	 * to view-resolver(s) in usual way.
	 * System.out.println("The Exception Message is :: " +
	 * exception.getErrorMessage()); return exception.getErrorMessage(); }
	 */

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
