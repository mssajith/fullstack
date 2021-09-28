package com.cts.fse.eauction.seller.repo;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cts.fse.eauction.seller.model.Product;

@Repository
@EnableScan
//public interface ProductRepository extends MongoRepository<Product, String> {
public interface ProductRepository extends CrudRepository<Product, String> {
	public Product findByProductName(String productName);
}
