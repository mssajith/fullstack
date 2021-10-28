package com.cts.fse.eauction.buyer.sqs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;


@Component
public class MessageListener {
    private final Logger log = LoggerFactory.getLogger(MessageListener.class);
    
	@SqsListener(value="eauction-queue", deletionPolicy=SqsMessageDeletionPolicy.ON_SUCCESS)
	public void processMessage(Message message) {
		log.info("Message is :::" + message.getMsg());
	}
	
}
