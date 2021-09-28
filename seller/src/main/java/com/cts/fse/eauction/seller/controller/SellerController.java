package com.cts.fse.eauction.seller.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import com.cts.fse.eauction.seller.exception.SellerValidationException;
import com.cts.fse.eauction.seller.feignclient.Buyer;
import com.cts.fse.eauction.seller.model.Product;
import com.cts.fse.eauction.seller.repo.ProductRepository;
import com.cts.fse.eauction.seller.service.ProductService;

@Controller
@RestController
@RequestMapping(value = "/seller")
public class SellerController {
	
	@Autowired
	ProductService productService;
	
	ProductRepository productRepository; 
	
	@PostMapping(value = "/addProduct")
	public String addProduct(@Valid @RequestBody Product product) throws SellerValidationException {
		
		/*
		 * if (null != product) { System.out.println("Product Values Are");
		 * System.out.println(product.getProductName()); }
		 * 
		 * productRepository.save(product); return "Saved Successfully";
		 */
		
		return productService.execute(product);
	}
	
	
	@GetMapping(value = "/show-bids/{productId}")
	public List<Buyer> getProductBids(@PathVariable String productId) {
//		Optional<Product> product = productService.getProductById(productId);
		
		return productService.listAllBids(productId);
	}
	
	@PutMapping(value = "/delete/{productId}")
	public void deleteProduct(@PathVariable String productId) throws SellerValidationException {
		productService.deleteProductById(productId);
	}
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
	
	@GetMapping("/test")
	public String test() {
		return "Test Success";
	}
	
	@GetMapping("/clear")
	public void clear() {
		productRepository.deleteAll();
	}
	
	
	@ExceptionHandler({ SellerValidationException.class })
	public String databaseError(SellerValidationException exception) {
		// Nothing to do. Return value 'databaseError' used as logical view name
		// of an error page, passed to view-resolver(s) in usual way.
		System.out.println("The Exception Message is :: " + exception.getErrorMessage());
		return exception.getErrorMessage();
	}
}
