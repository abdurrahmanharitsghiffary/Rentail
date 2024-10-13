package com.abdhage.rentail;

import com.abdhage.rentail.common.constants.MailProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties({MailProperties.class})
public class RentailApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentailApplication.class, args);
    }

}
