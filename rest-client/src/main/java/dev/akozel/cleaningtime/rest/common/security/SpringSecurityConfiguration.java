package dev.akozel.cleaningtime.rest.common.security;

import dev.akozel.cleaningtime.rest.auth.service.JwtTokenService;
import dev.akozel.cleaningtime.rest.common.security.filter.ExceptionHandlingFilter;
import dev.akozel.cleaningtime.rest.common.security.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

/**
 * SecurityConfiguration. Security configuration for the available resources
 * <p>
 * Date: 15/02/2020
 *
 * @author Andrey Kozel
 */
@EnableWebSecurity
@Configuration
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final JwtTokenService jwtTokenService;
    private final HandlerExceptionResolver resolver;

    @Autowired
    public SpringSecurityConfiguration(PasswordEncoder passwordEncoder,
                                       UserDetailsService userDetailsService,
                                       JwtTokenService jwtTokenService,
                                       @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.jwtTokenService = jwtTokenService;
        this.resolver = resolver;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        disableCsrf(http);
        enableCors(http);
        makeSwaggerEndpointsAvailable(http);
        makePublicEndpointsAvailable(http);
        restrictOtherEndpoints(http);
        makeSessionStateless(http);
        addFilters(http);
    }

    private void disableCsrf(HttpSecurity http) throws Exception {
        http.csrf()
                .disable();
    }

    private void enableCors(HttpSecurity http) throws Exception {
        http.cors();
    }

    private void makeSwaggerEndpointsAvailable(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api").permitAll()
                .antMatchers("/api/webjars").permitAll()
                .antMatchers("/api/webjars/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/swagger-resources").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/swagger-ui/*").permitAll();
    }

    private void makePublicEndpointsAvailable(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/v1/registrations").permitAll()
                .antMatchers(HttpMethod.POST, "/v1/auth").permitAll();
    }

    private void makeSessionStateless(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    private void restrictOtherEndpoints(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                .authenticated();
    }

    private void addFilters(HttpSecurity http) {
        http.addFilterAfter(new JwtAuthenticationFilter(jwtTokenService, userDetailsService),
                BasicAuthenticationFilter.class);
        http.addFilterBefore(new ExceptionHandlingFilter(resolver), CorsFilter.class);
    }

}
