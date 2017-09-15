package com.assessment.springboot_dynamo.repository;

import com.assessment.springboot_dynamo.model.Assessment;
//import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

//@EnableScan
public interface PersistingRepoDynamo extends CrudRepository< Assessment, String> {

    public void save( @Param("user") String user);

}
