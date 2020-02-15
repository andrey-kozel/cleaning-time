package dev.akozel.cleaningtime.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "dev.akozel.cleaningtime")
public class CleaningTimeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CleaningTimeApplication.class, args);
    }

}
