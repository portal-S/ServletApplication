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
       // AccountStatus status = AccountRepositoryImpl.auth(acc);
       // if(status == AccountStatus.NOT_AUTHORIZED){
            //if(status != AccountStatus.ERROR_PASS){
                Account account = repository.create(acc);
                userService.create(account);
                writer.write("DONE");
          //  } else writer.write("ERROR: WRONG PASSWORD");
    //    } else writer.write("ERROR: ACCOUNT ALREADY EXIST");
    }

    public void update(InputStream stream, String filename, int id) {

    }

    public void delete(int id) {

    }

    public Account read(int id) {
        return repository.read(id);
    }
}
