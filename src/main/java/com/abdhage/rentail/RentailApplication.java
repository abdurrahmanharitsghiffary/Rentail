package com.abdhage.rentail;

import com.abdhage.rentail.common.constants.MailProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({MailProperties.class})
public class RentailApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentailApplication.class, args);
    }

}
