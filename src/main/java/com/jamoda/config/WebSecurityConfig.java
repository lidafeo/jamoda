package com.jamoda.config;

import com.jamoda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Configuration
    @Order(1)
    public static class AdminConfigurationAdapter extends WebSecurityConfigurerAdapter {

        private UserService userService;

        public AdminConfigurationAdapter() {
            super();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/admin/**").hasAuthority("ADMIN")
                    //.antMatchers("/greeting").hasRole("ADMIN")
                    //.antMatchers("/").permitAll()
                    //.anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                    .logout()
                    .permitAll();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userService)
                    .passwordEncoder(NoOpPasswordEncoder.getInstance());
        }

        @Autowired
        public void setUserService(UserService userService) {
            this.userService = userService;
        }
    }

    @Configuration
    @Order(2)
    public static class UserConfigurationAdapter extends WebSecurityConfigurerAdapter {

        private UserService userService;

        public UserConfigurationAdapter() {
            super();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/cabinet/**").hasAuthority("USER")
                    //.antMatchers("/greeting").hasRole("ADMIN")
                    //.antMatchers("/").permitAll()
                    //.anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login1")
                    .permitAll()
                    .and()
                    .logout()
                    .permitAll();
        }
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userService)
                    .passwordEncoder(NoOpPasswordEncoder.getInstance());
        }

        @Autowired
        public void setUserService(UserService userService) {
            this.userService = userService;
        }
    }
}
