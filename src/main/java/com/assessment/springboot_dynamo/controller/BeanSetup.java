package com.assessment.springboot_dynamo.controller;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.local.embedded.DynamoDBEmbedded;
import com.amazonaws.services.dynamodbv2.local.main.ServerRunner;
import com.amazonaws.services.dynamodbv2.local.server.DynamoDBProxyServer;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.CreateTableResult;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;


public class BeanSetup {

//    public AmazonDynamoDB ddb;
//    public CreateTableResult createTableResult;

    private DynamoDBProxyServer server;
    private AmazonDynamoDB amazonDynamoDB;

    public void getCreateTable( ) {
        System.setProperty("sqlite4java.library.path", "native-libs");
//        ddb = DynamoDBEmbedded.create().amazonDynamoDB();
//        if(createTableResult == null) {
//            List<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();
//            attributeDefinitions.add(new AttributeDefinition("id", ScalarAttributeType.S));
//            ProvisionedThroughput provisionedthroughput = new ProvisionedThroughput(1000L, 1000L);
//            List<KeySchemaElement> ks = new ArrayList<KeySchemaElement>();
//            ks.add(new KeySchemaElement("id", KeyType.HASH));
//            CreateTableRequest request =
//                    new CreateTableRequest()
//                            .withTableName("Assessment")
//                            .withAttributeDefinitions(attributeDefinitions)
//                            .withKeySchema(ks)
//                            .withProvisionedThroughput(provisionedthroughput);
//            createTableResult = ddb.createTable(request);
//        }


        try {
            final String port ="8000";
            this.server = ServerRunner.createServerFromCommandLineArgs(new String[]{"-inMemory", "-port", port});
            server.start();
            amazonDynamoDB = new AmazonDynamoDBClient(new BasicAWSCredentials("access", "secret"));
            amazonDynamoDB.setEndpoint("http://localhost:" + port);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


    public AmazonDynamoDB getAmazonDynamoDB() {
        return amazonDynamoDB;
    }

    private String getAvailablePort() {
        try (final ServerSocket serverSocket = new ServerSocket(0)) {
            return String.valueOf(serverSocket.getLocalPort());
        } catch (IOException e) {
            throw new RuntimeException("Available port was not found", e);
        }
    }
 }
