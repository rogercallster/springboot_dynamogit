package com.assessment.springboot_dynamo.controller;


import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.local.embedded.DynamoDBEmbedded;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.CreateTableResult;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.amazonaws.services.dynamodbv2.model.TableDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RestController
public class TelemetryService {




    @RequestMapping("/api")
    public String hello() {
       BeanSetup setup = new BeanSetup();
        CreateTableResult createTableResult = null;
       setup.getCreateTable();
        AmazonDynamoDB     ddb = setup.getAmazonDynamoDB();

        if(createTableResult == null) {
            List<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();
            attributeDefinitions.add(new AttributeDefinition("id", ScalarAttributeType.S));
            ProvisionedThroughput provisionedthroughput = new ProvisionedThroughput(1000L, 1000L);
            List<KeySchemaElement> ks = new ArrayList<KeySchemaElement>();
            ks.add(new KeySchemaElement("id", KeyType.HASH));
            CreateTableRequest request =
                    new CreateTableRequest()
                            .withTableName("Assessment")
                            .withAttributeDefinitions(attributeDefinitions)
                            .withKeySchema(ks)
                            .withProvisionedThroughput(provisionedthroughput);
            createTableResult = ddb.createTable(request);

        }

        return "done";
    }
}
