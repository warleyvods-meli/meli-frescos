package com.mercadolibre.dambetan01.service;

import com.mercadolibre.dambetan01.model.Account;
import com.mercadolibre.dambetan01.model.CountryHouse;
import com.mercadolibre.dambetan01.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AccountService {


    @Autowired
    AccountRepository accountRepository;


    public void inserirUsuarioAdmin() {
        try{
            if (accountRepository.findByUsername("meliadmin").isEmpty()) {
                log.debug("Usuario não encontrador, criando usuario");

                Account account = new Account();

                account.setUsername("meliadmin");
                account.setPassword(new BCryptPasswordEncoder().encode("123"));
                account.setAdmin(true);
                accountRepository.save(account);
            }
        } catch (Exception e) {
            log.debug("Não Encontrado");
        }
    }

    public void inserirUsuarioUser() {
        try{
            if (accountRepository.findByUsername("meliuser").isEmpty()) {
                log.debug("Usuario não encontrador, criando usuario");
                Account account = new Account();
                account.setUsername("meliuser");
                account.setPassword(new BCryptPasswordEncoder().encode("123"));
                account.setAdmin(true);
                accountRepository.save(account);
            }
        } catch (Exception e) {
            log.debug("Não Encontrado");
        }
    }


}
