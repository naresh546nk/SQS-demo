package com.sqs.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class SQSConfig {
	@Value("${cloud.aws.access-key}")
	private String accessKey;
	@Value("${cloud.aws.secret-key}")
	private String secretKey;

    @Bean
    public QueueMessagingTemplate queueMessagingTemplet() {
    	return new QueueMessagingTemplate(buildAmazonSQSAsync());
    }


    private AmazonSQSAsync buildAmazonSQSAsync(){
        return AmazonSQSAsyncClientBuilder
                .standard()
                .withRegion(Regions.US_WEST_1)
                .build();
    }

    //Better to not put cradential here:
/*
    private AmazonSQSAsync buildAmazonSQSAsync(){
        return AmazonSQSAsyncClientBuilder
                .standard()
                .withRegion(Regions.US_WEST_1)
                .withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials(accessKey,secretKey)
                        )
                ).build();
    }
*/


}
