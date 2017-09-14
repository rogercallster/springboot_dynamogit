package com.assessment.springboot_dynamo.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TelemetryService {
    @RequestMapping("/api")
    public String hello() {
        return "hello from ctrller";
    }
}
