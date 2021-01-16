package com.upc.ittrainer.infra.security;

import com.upc.ittrainer.model.admin.Capability;
import com.upc.ittrainer.service.admin.CapabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;
    @Autowired
    CapabilityService capabilityService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private DBUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http
                .authorizeRequests()
                .antMatchers("/javax.faces.resource/**").permitAll()
                .antMatchers("/error").permitAll()
                .antMatchers("/info").permitAll()
                .antMatchers("/metadata").permitAll()
                .antMatchers("/authenticationFailure").permitAll()
                .antMatchers("/saml/**").permitAll()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/actuator").permitAll()
                .anyRequest().authenticated();

        http
                .formLogin()
                .loginPage("/login.xhtml")
                .permitAll()
                .failureUrl("/authenticationFailure.xhtml")
                .and()
                .logout()    //logout configuration
                .logoutSuccessUrl("/login.xhtml");

        for (final Capability capability : capabilityService.findAll()) {
            http.authorizeRequests()
                    .antMatchers(capability.getPattern()).hasAuthority(capability.getName());
        }

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }
}
