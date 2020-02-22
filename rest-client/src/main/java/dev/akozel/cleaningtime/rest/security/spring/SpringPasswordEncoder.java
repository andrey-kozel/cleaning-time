package dev.akozel.cleaningtime.rest.security.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * SpringPasswordEncoder.
 * <p>
 * Date: 22/02/2020
 *
 * @author Andrey Kozel
 */
@Configuration
public class SpringPasswordEncoder {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
