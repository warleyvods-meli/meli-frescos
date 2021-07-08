package com.mercadolibre.dambetan01.config;

import com.mercadolibre.dambetan01.repository.AccountRepository;
import com.mercadolibre.dambetan01.security.CustomUserDetailService;
import com.mercadolibre.dambetan01.security.JWTAuthenticationFilter;
import com.mercadolibre.dambetan01.security.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.mercadolibre.dambetan01.security.SecurityConstants.LOGAR;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class    WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailService customUserDetailService;
    private final AccountRepository accountRepository;

    public WebSecurityConfig(CustomUserDetailService customUserDetailService, AccountRepository accountRepository) {
        this.customUserDetailService = customUserDetailService;
        this.accountRepository = accountRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests().antMatchers(HttpMethod.GET, LOGAR).permitAll()

//                .antMatchers("/**/admin/**").hasRole("ADMIN")
//                .antMatchers("/**/agent/**").hasRole("AGENT")
//                .antMatchers("/**/seller/**").hasRole("SELLER")
//                .antMatchers("/**/buyer/**").hasRole("BUYER")

                .antMatchers("/**/admin/**").permitAll()
                .antMatchers("/**/agent/**").permitAll()
                .antMatchers("/**/seller/**").permitAll()
                .antMatchers("/**/buyer/**").permitAll()
                .antMatchers(HttpMethod.GET, "/ping").permitAll()
                .antMatchers(HttpMethod.GET, "/v3/api-docs").permitAll()
                .antMatchers(HttpMethod.GET, "/fake").permitAll()
                .antMatchers("/*/actuator/**").permitAll()
                //.anyRequest().permitAll();
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), accountRepository))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), customUserDetailService));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }

}
