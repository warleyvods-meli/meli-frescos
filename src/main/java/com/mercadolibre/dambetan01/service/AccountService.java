package com.mercadolibre.dambetan01.service;

import com.mercadolibre.dambetan01.model.Account;
import com.mercadolibre.dambetan01.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AccountService {

    private static final String DEBUG_MSG = "User not found, insert...";

    @Autowired
    AccountRepository accountRepository;


    public void insertAdminUser() {
        try{
            if (accountRepository.findByUsername("meliadmin").isEmpty()) {
                log.debug(DEBUG_MSG);

                var account = new Account();
                account.setUsername("meliadmin");
                account.setPassword(new BCryptPasswordEncoder().encode("123"));
                account.setAdmin(true);
                account.setAgent(false);
                account.setBuyer(false);
                account.setSeller(false);
                accountRepository.save(account);
            }
        } catch (Exception e) {
            log.debug(e.toString());
        }
    }

    public void insertSellerUser() {
        try{
            if (accountRepository.findByUsername("meliseller").isEmpty()) {
                log.debug(DEBUG_MSG);
                var account = new Account();
                account.setUsername("meliseller");
                account.setPassword(new BCryptPasswordEncoder().encode("123"));
                account.setAdmin(false);
                account.setAgent(false);
                account.setBuyer(false);
                account.setSeller(true);
                accountRepository.save(account);
            }
        } catch (Exception e) {
            log.debug(e.toString());
        }
    }

    public void insertBuyerUser() {
        try{
            if (accountRepository.findByUsername("melibuyer").isEmpty()) {
                log.debug(DEBUG_MSG);
                var account = new Account();
                account.setUsername("melibuyer");
                account.setPassword(new BCryptPasswordEncoder().encode("123"));
                account.setAdmin(false);
                account.setAgent(false);
                account.setBuyer(true);
                account.setSeller(false);
                accountRepository.save(account);
            }
        } catch (Exception e) {
            log.debug(e.toString());
        }
    }

    public void insertAgentUser() {
        try{
            if (accountRepository.findByUsername("meliagent").isEmpty()) {
                log.debug(DEBUG_MSG);
                var account = new Account();
                account.setUsername("meliagent");
                account.setPassword(new BCryptPasswordEncoder().encode("123"));
                account.setAdmin(false);
                account.setAgent(true);
                account.setBuyer(false);
                account.setSeller(false);
                accountRepository.save(account);
            }
        } catch (Exception e) {
            log.debug(e.toString());
        }
    }


}
