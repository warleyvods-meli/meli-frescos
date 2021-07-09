package com.mercadolibre.dambetan01.security;

import com.mercadolibre.dambetan01.model.Account;
import com.mercadolibre.dambetan01.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomUserDetailService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Autowired
    public CustomUserDetailService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) {
        Account usuario = accountRepository.findByUsername(login)
                .orElseThrow(() -> new UsernameNotFoundException("user not found!"));

        List<GrantedAuthority> authorityListAdmin = AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN", "ROLE_BUYER", "ROLE_SELLER", "ROLE_AGENT");
        List<GrantedAuthority> authorityListBuyer = AuthorityUtils.createAuthorityList("ROLE_BUYER");
        List<GrantedAuthority> authorityListSeller = AuthorityUtils.createAuthorityList("ROLE_SELLER");
        List<GrantedAuthority> authorityListAgent = AuthorityUtils.createAuthorityList("ROLE_AGENT");

        if (usuario.isAdmin()) {
            return new User(usuario.getUsername(), usuario.getPassword(), authorityListAdmin);
        } else if (usuario.isAgent()) {
            return new User(usuario.getUsername(), usuario.getPassword(), authorityListAgent);
        } else if (usuario.isBuyer()) {
            return new User(usuario.getUsername(), usuario.getPassword(), authorityListBuyer);
        } else if (usuario.isSeller()) {
            return new User(usuario.getUsername(), usuario.getPassword(), authorityListSeller);
        }

        return null;
    }
}
