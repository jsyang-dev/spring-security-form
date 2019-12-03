package me.study.springsecurityform.common;

import me.study.springsecurityform.account.Account;
import me.study.springsecurityform.account.AccountService;
import me.study.springsecurityform.book.Book;
import me.study.springsecurityform.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DefaultDataGenerator implements ApplicationRunner {
    @Autowired
    private AccountService accountService;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void run(ApplicationArguments args) {
        Account yangjs = createUser("yangjs");
        Account whiteship = createUser("whiteship");
        createBook("spring", yangjs);
        createBook("hibernate", whiteship);
    }

    private Account createUser(String username) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword("1234");
        account.setRole("USER");
        return this.accountService.createNew(account);
    }

    private void createBook(String title, Account account) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(account);
        this.bookRepository.save(book);
    }
}
