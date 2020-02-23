package dev.akozel.cleaningtime.rest.feature.auth.domain;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * AuthToken. Implementation of the spring authentication to be stored in context
 * <p>
 * Date: 22/02/2020
 *
 * @author Andrey Kozel
 */
public class AuthToken implements Authentication {

    private String token;
    private UserDetails userDetails;
    private boolean authenticated;

    public AuthToken(String token, UserDetails userDetails) {
        this.token = token;
        this.userDetails = userDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userDetails.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return userDetails.getPassword();
    }

    @Override
    public Object getDetails() {
        return userDetails;
    }

    @Override
    public Object getPrincipal() {
        return userDetails.getUsername();
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    @Override
    public String getName() {
        return userDetails.getUsername();
    }

    public String getToken() {
        return token;
    }
}
