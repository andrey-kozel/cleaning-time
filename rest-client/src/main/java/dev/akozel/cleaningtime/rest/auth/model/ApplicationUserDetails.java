package dev.akozel.cleaningtime.rest.auth.model;

import lombok.Getter;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

@Getter
public class ApplicationUserDetails extends User {

    private Long id;

    public ApplicationUserDetails(Long id, String username, String password) {
        super(username, password, Collections.emptyList());
        this.id = id;
    }

}
