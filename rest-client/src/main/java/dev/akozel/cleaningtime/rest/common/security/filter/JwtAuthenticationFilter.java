package dev.akozel.cleaningtime.rest.common.security.filter;

import dev.akozel.cleaningtime.rest.auth.domain.AuthToken;
import dev.akozel.cleaningtime.rest.auth.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * JwtAuthenticationFilter. Filter that checks if provide token valid
 * <p>
 * Date: 22/02/2020
 *
 * @author Andrey Kozel
 */
public class JwtAuthenticationFilter extends GenericFilterBean {

    private JwtTokenService jwtTokenService;
    private UserDetailsService userDetailsService;

    @Autowired
    public JwtAuthenticationFilter(JwtTokenService jwtTokenService, UserDetailsService userDetailsService) {
        this.jwtTokenService = jwtTokenService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getHeader("Authorization");
        if (token != null) {
            authorize(token);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void authorize(String token) {
        String username = jwtTokenService.getUsernameByAccessToken(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        AuthToken authToken = new AuthToken(token, userDetails);
        authToken.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

}
