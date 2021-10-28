package com.cts.fse.eauction.seller.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;

@Configuration
public class SQSConfig {
    @Value("${amazon.aws.region}")
    private String region;

    @Value("${amazon.aws.accesskey}")
    private String accessKey;

    @Value("${amazon.aws.secretkey}")
    private String secretKey;
    
    @Bean
    public QueueMessagingTemplate queueMessagingTemplate() {
    	return new QueueMessagingTemplate(amazonSQSAsync());
    }
    
    @Bean
    @Primary
    public AmazonSQSAsync amazonSQSAsync() {
    	return AmazonSQSAsyncClientBuilder.standard().withRegion(Regions.US_EAST_2)
    			.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
    			.build();
    }
}
