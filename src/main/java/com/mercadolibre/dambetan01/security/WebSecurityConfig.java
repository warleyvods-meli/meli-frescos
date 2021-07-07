package com.mercadolibre.dambetan01.security;

import com.mercadolibre.dambetan01.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.mercadolibre.dambetan01.security.SecurityConstants.LOGAR;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailService customUserDetailService;
    private final AccountRepository usuarioRepository;

    public WebSecurityConfig(CustomUserDetailService customUserDetailService, AccountRepository usuarioRepository) {
        this.customUserDetailService = customUserDetailService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests().antMatchers(HttpMethod.GET, LOGAR).permitAll()
                .antMatchers("/**/admin/**").hasRole("ADMIN")
                .antMatchers("/**/agent/**").hasRole("AGENT")
                .antMatchers("/**/seller/**").hasRole("SELLER")
                .antMatchers("/**/buyer/**").hasRole("BUYER")
                .antMatchers(HttpMethod.GET, "/ping").permitAll()
                .antMatchers(HttpMethod.GET, "/v3/api-docs").permitAll()
                .antMatchers(HttpMethod.GET, "/fake").permitAll()
                .antMatchers("/*/actuator/**").permitAll()
//                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), usuarioRepository))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), customUserDetailService));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }

}
