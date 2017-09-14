package com.assessment.springboot_dynamo.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Assessment")
public class Assessment {


    private String user;
    private long id;

    public Assessment(String user) {
        this.user = user;
    }

    @DynamoDBAttribute
    public String getUser() {
        return user;
    }

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    public long getId() {
        return id;
    }


}