package dev.akozel.cleaningtime.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * CleaningTimeApplication.
 * <p>
 * Date: 25/01/2020
 *
 * @author Andrey Kozel
 */
@SuppressWarnings("HideUtilityClassConstructor")
@SpringBootApplication(scanBasePackages = "dev.akozel.cleaningtime")
public class CleaningTimeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CleaningTimeApplication.class, args);
    }

}
