package com.flz.demo.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "demo-prop-test")
public class TestProp {
    private String str1;
    private Integer int1;

    public TestProp(String str1, Integer int1) {
        this.str1 = str1;
        this.int1 = int1;
    }

    public TestProp() {
    }

    public String getStr1() {
        return str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public Integer getInt1() {
        return int1;
    }

    public void setInt1(Integer int1) {
        this.int1 = int1;
    }
}
