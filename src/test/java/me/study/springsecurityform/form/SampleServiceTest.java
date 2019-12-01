package me.study.springsecurityform.form;

import me.study.springsecurityform.account.Account;
import me.study.springsecurityform.account.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleServiceTest {
    @Autowired
    private SampleService sampleService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Test
    public void dashboard() {
        Account account = new Account();
        account.setUsername("yangjs");
        account.setPassword("1234");
        account.setRole("ADMIN");
        this.accountService.createNew(account);

        UserDetails userDetails = this.accountService.loadUserByUsername("yangjs");

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, "1234");
        Authentication authentication = this.authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        this.sampleService.dashboard();
    }

    @Test
    @WithMockUser
    public void dashboard2() {
        this.sampleService.dashboard();
    }
}