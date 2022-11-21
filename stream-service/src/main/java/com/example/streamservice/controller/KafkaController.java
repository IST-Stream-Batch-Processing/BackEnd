package com.example.streamservice.controller;

import com.example.streamservice.config.KafkaConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@RestController
public class KafkaController {

    @PostMapping("/testKafka")
    public void startKafka(@RequestParam("filePath") String filePath, @RequestParam("topic") String topic) throws IOException, InterruptedException {
        //测试用
        filePath = "./stream-service/src/main/resources/file/Users.csv";
        topic = "test";

        KafkaConfig kafkaConfig = new KafkaConfig();
        Properties properties = kafkaConfig.config()
                ;
        //定义一个kafka Producer
        KafkaProducer<String,String> kafkaProducer = new KafkaProducer<>(properties);
        //用缓冲方式读取文本
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        String line;
        while( (line = bufferedReader.readLine()) != null){
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, line);
            //用producer发送数据
            //控制数据流产生频率(真实不用)
            Thread.sleep(1000L);
            kafkaProducer.send(producerRecord);
            System.out.println("1");
        }
        kafkaProducer.close();
    }

}
