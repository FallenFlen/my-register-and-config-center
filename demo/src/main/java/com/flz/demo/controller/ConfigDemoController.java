package com.flz.demo.controller;

import com.flz.configservice.starter.dynamic.DynamicValue;
import com.flz.demo.properties.TestProp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class ConfigDemoController {
    @DynamicValue
    @Value("${myconf:DEFAULT}")
    private String testVa1;
    private final TestProp testProp;

    public ConfigDemoController(TestProp testProp) {
        this.testProp = testProp;
    }

    @GetMapping("/test1")
    public String test1() {
        return testVa1;
    }

    @GetMapping("/test-prop")
    public TestProp getTestProp() {
        return testProp;
    }
}
