package com.example.sina.repository;

import com.example.sina.entity.DataModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataModelRepository extends MongoRepository<DataModel,String> {

}
