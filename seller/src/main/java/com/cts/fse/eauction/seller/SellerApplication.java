package com.cts.fse.eauction.seller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Repository;

import com.cts.fse.eauction.seller.model.Product;
import com.cts.fse.eauction.seller.model.Seller;
import com.cts.fse.eauction.seller.repo.ProductRepository;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SellerApplication {//implements CommandLineRunner{

	
	public static void main(String[] args) {
		SpringApplication.run(SellerApplication.class, args);
	}

	
	/*
	 * @Autowired private ProductRepository productRepository;
	 * 
	 * public void run(String... args) throws Exception { // TODO Auto-generated
	 * method stub Product prod = new Product(); prod.setProductName("Cycle Stand");
	 * prod.setStartingPrice(1500); prod.setBidEndDate("2021-09-07");
	 * 
	 * Seller seller = new Seller(); seller.setFirstName("Seller FirstName");
	 * seller.setLastName("Seller LastName");
	 * 
	 * prod.setSeller(seller);
	 * 
	 * 
	 * productRepository.deleteAll(); productRepository.save(prod);
	 * 
	 * }
	 */

}
