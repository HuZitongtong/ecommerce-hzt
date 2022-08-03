package com.hzt.config;

import java.util.Properties;
import java.util.Set;


public class Test {
    public static void main(String[] args) {
        Properties properties = System.getProperties();
        Set<Object> keys = properties.keySet();
        for (Object key : keys) {
            System.out.println(key + "================" + properties.getProperty(key.toString()));
        }
    }
    
}
