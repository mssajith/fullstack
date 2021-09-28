package com.cts.fse.eauction.buyer.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.fse.eauction.buyer.exception.BuyerValidationException;
import com.cts.fse.eauction.buyer.feignclient.Product;
import com.cts.fse.eauction.buyer.feignclient.SellerServiceInterface;
import com.cts.fse.eauction.buyer.model.Buyer;
import com.cts.fse.eauction.buyer.repo.BuyerRepository;
import com.cts.fse.eauction.buyer.service.BuyerService;

@RestController
@RequestMapping("/buyer")
public class BuyerController {
	@Autowired
	BuyerRepository buyerRepository;
	@Autowired
	BuyerService buyerService;
	@Autowired
	SellerServiceInterface sellerServiceInterface;
	
	@GetMapping("/test")
	public String test() {
		return "Test Success";
	}
	
	@GetMapping("/clear")
	public void clear() {
		buyerRepository.deleteAll();
	}
	
	@PostMapping("/place-bid")
	public String placeBid(@RequestBody Buyer buyer) {
		System.out.println(buyer.getFirstName());
		System.out.println(buyer.getLastName());
		buyerService.execute(buyer);
		return "success";
	}
	
	 @GetMapping("/fetchProduct/{id}")
	 public Product fetchProduct(@PathVariable String id) {

	  return sellerServiceInterface.getProduct(id);
	 }
	
	 @PutMapping("/delete/{productId}")
	 public void deletProduct(@PathVariable String productId) throws BuyerValidationException {
		 
		 buyerService.deleteProduct(productId);
	 }
	 
	 @GetMapping("/fetchAllBids/{productId}")
	 public List<Buyer> getAllBids(@PathVariable String productId){
		 return buyerService.getAllBidsByProduct(productId);
	 }
	 
	 @PutMapping("update-bid/{productId}/{buyerEmailld}/{newBidAmount}")
	 public void updateBidAmount(@PathVariable String productId, @PathVariable String buyerEmailId, @PathVariable String newBidAmount) throws BuyerValidationException {
		 buyerService.updateBidAmount(productId, buyerEmailId, newBidAmount);
	 }
}
