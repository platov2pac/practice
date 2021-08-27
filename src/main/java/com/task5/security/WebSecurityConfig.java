package com.task5.security;

import com.task5.security.UserDetailsServiceImpl;
import com.task5.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .maximumSessions(1);
        http
                .csrf().disable();
        http.authorizeRequests()
                .antMatchers("/", "/style/**").permitAll()
                .antMatchers("/auth.jhtml").anonymous()
                .antMatchers("/listUsers.jhtml").hasRole("ADMIN")
                .anyRequest().authenticated();
        http.formLogin()
                .loginPage("/auth.jhtml")
                .loginProcessingUrl("/login")
                .usernameParameter("login")
                .passwordParameter("pass")
                .defaultSuccessUrl("/welcome.jhtml")
                .failureUrl("/auth.jhtml?error=true");
        http.logout()
                .logoutUrl("/logout.jhtml")
                .logoutSuccessUrl("/auth.jhtml")
                .invalidateHttpSession(true);
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.POST, "/");
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(bCryptPasswordEncoder);
    }
}
