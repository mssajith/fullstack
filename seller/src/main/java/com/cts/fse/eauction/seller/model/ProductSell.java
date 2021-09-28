package com.cts.fse.eauction.seller.model;

import org.springframework.data.annotation.Id;

//@Document("Product")
public class ProductSell {
	@Id
	private String id;

	private Product product;
	private Seller seller;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

}
