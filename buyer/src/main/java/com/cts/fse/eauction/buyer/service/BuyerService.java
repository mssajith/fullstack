package com.cts.fse.eauction.buyer.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.fse.eauction.buyer.exception.BuyerValidationException;
import com.cts.fse.eauction.buyer.feignclient.Product;
import com.cts.fse.eauction.buyer.feignclient.SellerServiceInterface;
import com.cts.fse.eauction.buyer.model.Buyer;
import com.cts.fse.eauction.buyer.repo.BuyerRepository;

@Service
public class BuyerService {
	private static final Log logger = LogFactory.getLog(BuyerService.class);
	@Autowired
	BuyerRepository buyerRepository;
	
	@Autowired
	SellerServiceInterface sellerServiceInterface;
	
	//2021-09-05
	DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public void execute(Buyer buyer) {
		buyerRepository.save(buyer);
	}
	
	public void deleteProduct(String productId) throws BuyerValidationException {
		
		System.out.println("The Produt id is ::: " + productId);
		
		  Product product = sellerServiceInterface.getProduct(productId);
		  
		  if (null == product) {
			  // deletes the product
			  buyerRepository.deleteById(productId);
		  } else {
			  throw new BuyerValidationException("A Bid is Placed on the Product id :: " + productId);
		  }
	}

	public List<Buyer>  getAllBidsByProduct(String productId) {
		return buyerRepository.findByProductId(productId);
	}
		
	/**
	 * Updates the Bid Amount
	 * 
	 * @param productId
	 * @param email
	 * @param bidAmount
	 * @throws BuyerValidationException
	 */
	public void updateBidAmount(String productId, String email, String bidAmount) throws BuyerValidationException {
//		validateProductBidDate(productId);
		
		Buyer buyer = buyerRepository.findByProductIdAndEmail(productId, email);
		
		if (null != buyer) {
			buyer.setBidAmount(bidAmount);
			buyerRepository.save(buyer);
		} else {
			throw new BuyerValidationException("Could not Find the Bid");
		}
		
		
	}

	/**
	 * Validates the Product Bid Date 
	 * 
	 * @param productId
	 * @throws BuyerValidationException
	 */
	private void validateProductBidDate(String productId)
			throws BuyerValidationException {
		Product product = sellerServiceInterface.getProduct(productId);
		
		String bidEndDate = product.getBidEndDate();
		
		Date bidDate = null;
		try {
			bidDate = sdf.parse(bidEndDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if (bidDate.compareTo(Calendar.getInstance().getTime()) <= 0) {
			logger.info("Bid Date is Before Current Date ");
			throw new BuyerValidationException("Bid has already Ended");
		}
	}
}


