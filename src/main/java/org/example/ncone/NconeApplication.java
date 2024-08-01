package org.example.ncone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NconeApplication {

    public static void main(String[] args) {
        SpringApplication.run(NconeApplication.class, args);
    }

}
