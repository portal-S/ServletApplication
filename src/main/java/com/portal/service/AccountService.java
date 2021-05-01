package com.portal.service;

import com.portal.model.Account;
import com.portal.model.AccountStatus;
import com.portal.model.FileBD;
import com.portal.model.User;
import com.portal.repository.AccountRepositoryImpl;
import com.portal.repository.FileRepositoryImpl;
import com.portal.repository.interfaces.AccountRepository;
import com.portal.utils.BDUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.file.Files;

public class AccountService {

    AccountRepository repository = new AccountRepositoryImpl();
    UserService userService = new UserService();

    public void readAll(HttpServletResponse response) {

    }


    public void create(String name, String pass, Writer writer) throws IOException {
        Account acc = new Account(name, pass, AccountStatus.USER);
        Account account = repository.create(acc);
        userService.create(account);
    }

    public void update(String name, int id, String pass) {
        repository.update(new Account(id, name, pass, AccountStatus.USER));

    }

    public void delete(int id) {
        repository.delete(id);
    }

    public Account read(int id) {
        return repository.read(id);
    }
}
