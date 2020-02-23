package dev.akozel.cleaningtime.rest.feature.auth.service;

import dev.akozel.cleaningtime.core.user.domain.ApplicationUser;
import dev.akozel.cleaningtime.core.user.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * UserDetailsServiceImpl. Service that loads user for spring
 * <p>
 * Date: 22/02/2020
 *
 * @author Andrey Kozel
 */
@Component
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

    private ApplicationUserService applicationUserService;

    @Autowired
    public UserDetailsServiceImpl(ApplicationUserService applicationUserService) {
        this.applicationUserService = applicationUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        ApplicationUser applicationUser = applicationUserService.getByEmail(email);
        if (applicationUser == null) {
            throw new RuntimeException("User not found");
        }
        return new User(applicationUser.getEmail(), applicationUser.getPassword(), Collections.emptyList());
    }
}
