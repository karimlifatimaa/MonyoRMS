package com.example.monyorms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.monyorms.client")
public class MonyoRmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonyoRmsApplication.class, args);
    }

}
