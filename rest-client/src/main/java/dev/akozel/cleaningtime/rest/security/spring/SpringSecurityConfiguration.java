package dev.akozel.cleaningtime.rest.security.spring;

import dev.akozel.cleaningtime.rest.security.spring.filter.JwtAuthenticationFilter;
import dev.akozel.cleaningtime.rest.security.jwt.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private PasswordEncoder passwordEncoder;
    private UserDetailsService userDetailsService;
    private JwtTokenService jwtTokenService;

    @Autowired
    public SpringSecurityConfiguration(PasswordEncoder passwordEncoder,
                                       UserDetailsService userDetailsService,
                                       JwtTokenService jwtTokenService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.jwtTokenService = jwtTokenService;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        disableCsrf(http);
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

    private void makeSwaggerEndpointsAvailable(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api").permitAll()
                .antMatchers("/api/webjars").permitAll()
                .antMatchers("/api/webjars/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/swagger-resources").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/swagger-ui.html").permitAll();
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
    }

}
