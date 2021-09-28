package com.cts.fse.eauction.buyer.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "products")//), url = "http://localhost:8102")
public interface SellerServiceInterface {
	 @RequestMapping(value = "/seller/show-bids/{productId}", method = RequestMethod.GET)
	 public Product getProduct(@PathVariable("productId") String productId);
}
