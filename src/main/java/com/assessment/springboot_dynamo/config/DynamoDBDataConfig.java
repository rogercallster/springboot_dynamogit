package com.assessment.springboot_dynamo.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
//@EnableDynamoDBRepositories(basePackages = "com.assessment.springboot_dynamo.repository")
public class DynamoDBDataConfig {



        @Value("${amazon.dynamodb.endpoint}")
        private String amazonDynamoDBEndpoint;

        @Value("${amazon.aws.accesskey}")
        private String amazonAWSAccessKey;

        @Value("${amazon.aws.secretkey}")
        private String amazonAWSSecretKey;

        @Bean
        public AmazonDynamoDB amazonDynamoDB() {
            AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient(amazonAWSCredentials());
            if (!StringUtils.isEmpty(amazonDynamoDBEndpoint)) {
                amazonDynamoDB.setEndpoint(amazonDynamoDBEndpoint);
            }
            return amazonDynamoDB;
        }

        @Bean
        public AWSCredentials amazonAWSCredentials() {
            return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
        }
}
