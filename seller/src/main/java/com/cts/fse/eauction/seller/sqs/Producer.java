package com.cts.fse.eauction.seller.sqs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Component;


@Component
public class Producer {

	Logger logger = LoggerFactory.getLogger(Producer.class);
	
	@Autowired
	private QueueMessagingTemplate queueMessagingTemplate; 
	
    @Value("${amazon.aws.sqs.url}")
    private String endpoint;
    
    public void sendMessage(Message msg){
    	queueMessagingTemplate.convertAndSend(endpoint, msg);
    }
	
}
