package com.jun.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // add our users for in memory authentication
        // hard code
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser(users.username("a").password("1").roles("EMPLOYEE"))
                .withUser(users.username("b").password("1").roles("MANAGER", "EMPLOYEE"))
                .withUser(users.username("c").password("1").roles("ADMIN", "EMPLOYEE"))
                .withUser(users.username("d").password("1").roles("ADMIN", "MANAGER"))
                .withUser(users.username("e").password("1").roles("MANAGER", "ADMIN", "EMPLOYEE"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                    .formLogin()
//                    .loginPage("/showMyLoginPage")
//                    .loginProcessingUrl("/authenticateTheUser")
//                    .permitAll()
//                .and()
//                    .logout().permitAll();

        // restricting access to roles
        http.authorizeRequests()
                //restrict access
                .antMatchers("/").hasRole("EMPLOYEE")
                .antMatchers("/leaders/**").hasRole("MANAGER")
                .antMatchers("/systems/**").hasRole("ADMIN")
                .and()
                    .formLogin()
                    .loginPage("/showMyLoginPage")
                    .loginProcessingUrl("/authenticateTheUser")
                    .permitAll()
                .and()
                    .logout().permitAll()
                //access denied page
                .and()
                    .exceptionHandling().accessDeniedPage("/access-denied");
    }
}
