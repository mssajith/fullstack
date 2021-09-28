package com.cts.fse.eauction.buyer.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.fse.eauction.buyer.exception.BuyerValidationException;
import com.cts.fse.eauction.buyer.feignclient.Product;
import com.cts.fse.eauction.buyer.feignclient.SellerServiceInterface;
import com.cts.fse.eauction.buyer.model.Buyer;
import com.cts.fse.eauction.buyer.repo.BuyerRepository;

@Service
public class BuyerService {
	@Autowired
	BuyerRepository buyerRepository;
	
	@Autowired
	SellerServiceInterface sellerServiceInterface;
	
	//2021-09-05
	DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public void execute(Buyer buyer) {
		
		System.out.println(buyer.getFirstName());
		System.out.println(buyer.getLastName());		
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
		
	public void updateBidAmount(String productId, String email, String bidAmount) throws BuyerValidationException {
		Product product = sellerServiceInterface.getProduct(productId);
		
		String bidEndDate = product.getBidEndDate();
		
		Date bidDate = null;
		try {
			bidDate = sdf.parse(bidEndDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if (bidDate.compareTo(Calendar.getInstance().getTime()) <= 0) {
			System.out.println("Bid Date is Before Current Date ");
			throw new BuyerValidationException("Bid has already Ended");
		}
		
		
		Buyer buyer = buyerRepository.findByProductIdAndEmail(productId, email);
		
		if (null != buyer) {
			buyer.setBidAmount(bidAmount);
			buyerRepository.save(buyer);
		} else {
			throw new BuyerValidationException("Could not Find the Bid");
		}
		
		
	}
}


