package com.example.sina.rest;

import com.example.sina.entity.DataModel;
import com.example.sina.repository.DataModelRepository;
import com.example.sina.service.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/searchEngine")
public class Controller {
    @Autowired
    Services services;

    @GetMapping("/search/{topic}")
    public ArrayList<String> findResult(@PathVariable String topic) throws IOException {
        return services.callGoogleAndDuckDuckGo(topic);
    }

}
