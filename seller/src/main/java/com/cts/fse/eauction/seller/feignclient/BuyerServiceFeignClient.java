package com.cts.fse.eauction.seller.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "bids")//, url = "http://localhost:8103")
public interface BuyerServiceFeignClient {
	 @RequestMapping(value = "/buyer/fetchAllBids/{productId}", method = RequestMethod.GET)
	 public List<Buyer> getProductBids(@PathVariable("productId") String productId);
}
