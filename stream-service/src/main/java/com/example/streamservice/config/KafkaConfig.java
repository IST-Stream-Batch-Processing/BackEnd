package com.example.streamservice.config;

import org.springframework.context.annotation.Bean;

import java.util.Properties;

public class KafkaConfig {

    public Properties config(){
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers","hadoop102:9092");
        properties.setProperty("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        return properties;
    }
}
