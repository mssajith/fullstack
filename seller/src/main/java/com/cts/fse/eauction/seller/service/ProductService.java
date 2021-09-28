package com.cts.fse.eauction.seller.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.fse.eauction.seller.constants.ProductCategory;
import com.cts.fse.eauction.seller.exception.SellerValidationException;
import com.cts.fse.eauction.seller.feignclient.Buyer;
import com.cts.fse.eauction.seller.feignclient.BuyerServiceFeignClient;
import com.cts.fse.eauction.seller.model.Product;
import com.cts.fse.eauction.seller.repo.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	//2021-09-05
	DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	BuyerServiceFeignClient buyerServiceFeignClient;
	
	public String execute(Product product) throws SellerValidationException {
		if (null != product) {
			System.out.println("Product Values Are");
			System.out.println(product.getProductName());
			
			validateBidEndDate(product.getBidEndDate());
			validateCategory(product);
			validate(product.getStartingPrice());
			
			product.getBidEndDate();
			
		}
		
		productRepository.save(product);
		return "Saved Successfully";
	}

	public Optional<Product> getProductById(String productId) {
		return productRepository.findById(productId);
	}
	
	
	public List<Buyer> listAllBids(String productId) {
	return buyerServiceFeignClient.getProductBids(productId);	
	}
	
	public void deleteProductById(String productId) throws SellerValidationException {
		Optional<Product> product = productRepository.findById(productId);

		
		if (product.isPresent()) {
			String bidEndDate = product.get().getBidEndDate();
			Date bidDate = null;
			try {
				bidDate = sdf.parse(bidEndDate);
			} catch (ParseException e) {
				e.printStackTrace();
				throw new SellerValidationException("Product Bid Date coversion Error :: " + bidEndDate);
				
			}
			
			System.out.println("Bid End Date AFter Conversion " + bidDate.toString());
			
			if (bidDate.compareTo(Calendar.getInstance().getTime()) <= 0) {
				throw new SellerValidationException("Product Bid End Date is Already Expired , Cannot Delete the Product :: " + productId);
			}
			
		}else {
			throw new SellerValidationException("Product is Empty with Product Id :: " + productId);
		}
		
		List<Buyer> bidsList= listAllBids(productId);
		
		if (bidsList.isEmpty()) {
			logger.debug("Deleting the Product");
			productRepository.deleteById(productId);
		} else {
			throw new SellerValidationException("There are Bids Placed on this Product , Cannot delete when there is active Bids on a Product");
		}
		
	}
	

	private void validateCategory(Product product) throws SellerValidationException {
		if (Arrays.stream(ProductCategory.values()).anyMatch(e-> e.getCategroyName().equalsIgnoreCase(product.getCategory()))) {
			System.out.println("Product Category is ::: " + product.getCategory());
		} else {
			throw new SellerValidationException("Product Category " + product.getCategory() + " is not a Valid Category.");
		}
	}
	
	
	private void validateBidEndDate(String bidEndDate) throws SellerValidationException {
		System.out.println("The Bid End Date is :: " + bidEndDate);
		try {
			Date bidDate = sdf.parse(bidEndDate);
			
			System.out.println("Bid End Date AFter Conversion " + bidDate.toString());
			
			if (bidDate.compareTo(Calendar.getInstance().getTime()) <= 0) {
			
//			if (bidDate.before(Calendar.getInstance().getTime())) {
				System.out.println("Bid Date is Before Current Date ");
				throw new SellerValidationException("Bid End Date should be a future Date");
			}
			
			logger.info("Bid End Date :: " + bidDate.toString());
			logger.info("Current Date :: " + Calendar.getInstance().getTime().toString());
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new SellerValidationException("Bid End Date should be a future Date");
		}
		
	}
	
	private void validate(String startingPrice) throws SellerValidationException {
		try {
		Integer.parseInt(startingPrice);
		} catch (Exception ex) {
			throw new SellerValidationException("Starting Price should be a Number");
		}
	}
	
}
