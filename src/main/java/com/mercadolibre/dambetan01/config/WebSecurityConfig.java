package com.mercadolibre.dambetan01.config;

import com.mercadolibre.dambetan01.repository.AccountRepository;
import com.mercadolibre.dambetan01.security.CustomUserDetailService;
import com.mercadolibre.dambetan01.security.JWTAuthenticationFilter;
import com.mercadolibre.dambetan01.security.JWTAuthorizationFilter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.mercadolibre.dambetan01.security.SecurityConstants.LOGIN_URL;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailService customUserDetailService;
    private final AccountRepository accountRepository;

    private static final String BUYER = "BUYER";
    private static final String AGENT = "AGENT";
    private static final String ADMIN = "ADMIN";
    private static final String SELLER = "SELLER";


    public WebSecurityConfig(CustomUserDetailService customUserDetailService, AccountRepository accountRepository) {
        this.customUserDetailService = customUserDetailService;
        this.accountRepository = accountRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests().antMatchers(HttpMethod.GET, LOGIN_URL).permitAll()

                .antMatchers("/**/admin/**").hasRole(ADMIN)
                .antMatchers("/**/seller/**").hasRole(SELLER)

                .antMatchers("/**/fresh-products/inboundorder/**").hasRole(AGENT)
                .antMatchers("/**/fresh-products/location/").hasRole(AGENT)
                .antMatchers("/**/fresh-products/due-date/**").hasRole(AGENT)

                .antMatchers("/**/fresh-products").hasRole(BUYER)
                .antMatchers("/**/fresh-products/orders").hasRole(BUYER)
                .antMatchers("/**/fresh-products/orders/find").hasRole(BUYER)
                .antMatchers("/**/fresh-products/list").hasRole(BUYER)

                .antMatchers(HttpMethod.GET, "/ping").permitAll()
                .antMatchers(HttpMethod.GET, "/v3/api-docs").permitAll()
                .antMatchers(HttpMethod.GET, "/fake").permitAll()
                .antMatchers("/*/actuator/**").permitAll()

                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), accountRepository))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), customUserDetailService));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/**");
//    }

}
