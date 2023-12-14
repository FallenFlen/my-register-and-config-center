package com.flz.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class ConfigDemoController {
    @Value("${myconf.testv1}:DEFAULT")
    private String testVa1;

    @GetMapping("/test1")
    public String test1() {
        return testVa1;
    }
}
