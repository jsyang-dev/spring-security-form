package me.study.springsecurityform.form;

import me.study.springsecurityform.account.Account;
import me.study.springsecurityform.account.AccountContext;
import org.springframework.stereotype.Service;

@Service
public class SampleService {
    void dashboard() {
        Account account = AccountContext.getAccount();
        System.out.println("====================");
        System.out.println(account.getUsername());
    }
}
