package com.example.sina.entity;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Document("search")
@Data

public class DataModel {

    @MongoId
    private String id;
    private String searchEngine;
    private String topic;
    private String sitName;
    private String url;
    @CreatedDate
    private Date insertTimestamp;

}
