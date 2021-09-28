package com.cts.fse.eauction.buyer.repo;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cts.fse.eauction.buyer.model.Buyer;

@Repository
@EnableScan
//public interface BuyerRepository extends MongoRepository<Buyer, String> {
public interface BuyerRepository extends CrudRepository<Buyer, String> {
	
	
	List<Buyer> findByProductId(String productId);
	
	Buyer findByProductIdAndEmail(String productId, String email);
	
}


