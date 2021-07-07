//package com.mercadolibre.dambetan01.config;
//
//import com.mercadolibre.dambetan01.security.JWTAuthorizationFilter;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@EnableWebSecurity
//@Configuration
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and().csrf().disable()
////                .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
//                .authorizeRequests()
//                .antMatchers("/*/admin/**").hasRole("ADMIN")
//                .antMatchers("/*/protected/**").hasRole("USER")
//                .antMatchers(HttpMethod.POST, "/api/v1/sign-in").permitAll()
//                .antMatchers(HttpMethod.GET, "/ping").permitAll()
//                .antMatchers(HttpMethod.GET, "/v3/api-docs").permitAll()
//                .antMatchers(HttpMethod.GET, "/fake").permitAll()
//                //.anyRequest().permitAll();
//                .anyRequest().authenticated();
//
//   }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/**");
//    }
//}
